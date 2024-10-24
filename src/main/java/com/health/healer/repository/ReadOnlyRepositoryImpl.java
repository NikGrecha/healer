package com.health.healer.repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class ReadOnlyRepositoryImpl<T, ID> implements ReadOnlyRepository<T, ID>{
    @Override
    public List<T> findAll(Class<T> tClass, Connection connection) {
        String tableName = getTableName(tClass.getSimpleName());
        String queryResult = "SELECT * FROM ".concat(tableName.toLowerCase());
        String queryColumn = "SELECT * FROM get_columns('".concat(tableName.toLowerCase()).concat("')");

        List<T> result = new ArrayList<>();
        List<String> columnList = new ArrayList<>();
        List<Method> methodList = Arrays.stream(tClass.getMethods())
                .filter(el -> el.getName().contains("set"))
                .sorted(Comparator.comparing(Method::getName))
                .toList();

        try(Statement statement = connection.createStatement()) {
            // get column list of required entity
            ResultSet rs = statement.executeQuery(queryColumn);
            while(rs.next()){
                columnList.add(rs.getString(1));
            }

            rs.close();
            // sort
            columnList.sort(Comparator.naturalOrder());

            // get result data from required entity
            ResultSet rs2 = statement.executeQuery(queryResult);
            while(rs2.next()){
                T t = tClass.newInstance();
                List<Class<?>[]> paramTypes = new ArrayList<>();

                for (Method method : methodList) {
                    paramTypes.add(method.getParameterTypes());
                }

                for(int i = 0; i < methodList.size(); i++){
                    Class<?> aClass = paramTypes.get(i)[0];
                    if(rs2.getObject(columnList.get(i)) instanceof java.sql.Date){
                        LocalDate localDate = ((java.sql.Date) rs2.getObject(columnList.get(i))).toLocalDate();
                        methodList.get(i).invoke(t, localDate);
                    } else if(rs2.getObject(columnList.get(i)) instanceof java.sql.Timestamp){
                        LocalDateTime localDateTime = ((Timestamp) rs2.getObject(columnList.get(i))).toLocalDateTime();
                        methodList.get(i).invoke(t, localDateTime);
                    } else if(rs2.getObject(columnList.get(i)) instanceof Integer){
                        methodList.get(i).invoke(t, rs2.getInt(columnList.get(i)));
                    } else if(rs2.getObject(columnList.get(i)) instanceof Double){
                        methodList.get(i).invoke(t, rs2.getDouble(columnList.get(i)));
                    }
                    else{
                        methodList.get(i).invoke(t, (rs2.getObject(columnList.get(i))));  //, aClass
                    }
                }
                result.add(t);
            }
        }
        catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }

    public T findById(Class<T> tClass, ID id, Connection connection) {
        String tableName = getTableName(tClass.getSimpleName());
        String queryResult = "SELECT * FROM ".concat(tableName.toLowerCase()).concat(" WHERE id = ?");
        String queryColumn = "SELECT * FROM get_columns('".concat(tableName.toLowerCase()).concat("')");

        List<T> result = new ArrayList<>();
        List<String> columnList = new ArrayList<>();
        List<Method> methodList = Arrays.stream(tClass.getMethods())
                .filter(el -> el.getName().contains("set"))
                .sorted(Comparator.comparing(Method::getName))
                .toList();

        T t = null;
        try (Statement statement = connection.createStatement()) {
            // get column list of required entity
            ResultSet rs = statement.executeQuery(queryColumn);
            while (rs.next()) {
                columnList.add(rs.getString(1));
            }

            columnList.sort(Comparator.naturalOrder());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement statement = connection.prepareStatement(queryResult)) {
            statement.setInt(1, (Integer) id);
            // get result data from required entity
            ResultSet rs2 = statement.executeQuery();
            t = tClass.newInstance();
            while (rs2.next()) {
                List<Class<?>[]> paramTypes = new ArrayList<>();

                for (Method method : methodList) {
                    paramTypes.add(method.getParameterTypes());
                }

                Class<?> aClass = null;
                for (int i = 0; i < methodList.size(); i++) {
                    aClass = paramTypes.get(i)[0];
                    if(aClass.getSimpleName().equals("boolean")){
                        methodList.get(i).invoke(t, rs2.getBoolean(columnList.get(i)));
                    } else{
                        methodList.get(i).invoke(t, rs2.getObject(columnList.get(i), aClass));
                    }
//                    }
                }
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return t;
    }

    public String getTableName(String className){
        StringBuilder tableName = new StringBuilder(className);
        for(int i = 0; i < tableName.length(); i++){
            if(Character.isUpperCase(tableName.charAt(i)) && i != 0){
                tableName.insert(i, "_");
                i++;
            }
        }
        return tableName.toString();
    }
}

package com.health.healer.repository;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.time.LocalTime;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
public class JDBCCustomRepositoryImpl<T, ID> extends ReadOnlyRepositoryImpl<T, ID> implements JDBCCustomRepository<T, ID>{
    @Override
    public void save(T t, Connection connection) {
        String tableName = getTableName(t.getClass().getSimpleName());
        String queryColumn = "SELECT * FROM get_columns('".concat(tableName.toLowerCase()).concat("')");
        String queryInsert = "INSERT INTO ".concat(tableName.toLowerCase()).concat(" (");
        List<String> columnList = new ArrayList<>();

        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(queryColumn);
            while(rs.next()){
                if(!rs.getString(1).equals("id") && !rs.getString(1).equals("status")){
                    columnList.add(rs.getString(1));
                }
            }
            columnList.sort(Comparator.naturalOrder()); //1. Хранить данные не в листе, а в мапе
            for(int i = 0; i < columnList.size(); i++){
                if(i == columnList.size() - 1){
                    queryInsert = queryInsert.concat(columnList.get(i)).concat(")");
                }
                else{
                    queryInsert = queryInsert.concat(columnList.get(i)).concat(", ");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        var wrapper = new Object(){ String tempComp = ""; };
        Comparator<Method> customComparator = Comparator.
                comparing(obj -> wrapper.tempComp = ((obj.getName().indexOf("get") == 0) ? obj.getName().substring(3) :
                        (obj.getName().indexOf("is") == 0) ? obj.getName().substring(2) : ""));
        ArrayList<Method> methodList = (ArrayList<Method>) Arrays.stream(t.getClass().getMethods())
                .filter(el -> (el.getName().indexOf("get") == 0 ||
                        (el.getName().indexOf("is") == 0 && el.getReturnType() == boolean.class)) &&
                        !el.getName().equals("getId") && !el.getName().equals("getClass") && !el.getName().equals("getStatus"))
                .sorted(customComparator)
                .collect(Collectors.toList());

        queryInsert = queryInsert.concat(" VALUES(");
        for(int i = 0; i < columnList.size(); i++){
            if(i == methodList.size() - 1){
                queryInsert = queryInsert.concat("?)");
            }
            else{
                queryInsert = queryInsert.concat("?, ");
            }
        }

        try(PreparedStatement statement = connection.prepareStatement(queryInsert)) {

            for(int i = 0; i < columnList.size(); i++){
                    statement.setObject(i+1, methodList.get(i).invoke(t));
//                }
            }

            statement.executeUpdate();
            log.info("Object of {} like {} was saved", t.getClass().getSimpleName(), t);
        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Class<T> tClass, ID id, Connection connection) {
        String tableName = getTableName(tClass.getSimpleName());
        String queryDelete = "DELETE FROM ".concat(tableName.toLowerCase().concat(" WHERE id = ?"));
        try(PreparedStatement statement = connection.prepareStatement(queryDelete)) {
            statement.setInt(1, (Integer)id);
            statement.executeUpdate();
            log.info("Object of {} with id {} was deleted", tClass.getSimpleName(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Class<T> tClass, ID id, String columnName, String value, Connection connection) {
        String tableName = getTableName(tClass.getSimpleName());
        String query = "UPDATE ".concat(tableName.toLowerCase()).concat(" SET ").concat(columnName).concat(" = ? WHERE id = ?");
        System.out.println("table_name: " + tableName.toLowerCase());
        System.out.println("query: " + query);

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2, (Integer) id);
            preparedStatement.executeUpdate();
            log.info("Set value {} for column {} of table{}", value, columnName, tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
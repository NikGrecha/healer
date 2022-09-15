package com.health.healer.repository;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

@Slf4j
public class JDBCCustomRepositoryImpl<T, ID> extends ReadOnlyRepositoryImpl<T, ID> implements JDBCCustomRepository<T, ID>{
    @Override
    public void save(T t, Connection connection) {
        String tableName = getTableName(t.getClass().getSimpleName());
        String queryColumn = "SELECT * FROM get_columns('".concat(tableName).concat("')");
        String queryInsert = "INSERT INTO ?(";
        List<String> columnList = new ArrayList<>();

        try(Statement statement = connection.createStatement();) {
            ResultSet rs = statement.executeQuery(queryColumn);
            while(rs.next()){
                if(!rs.getString(1).equals("id")){
                    columnList.add(rs.getString(1));
                }
            }
            columnList.sort(Comparator.naturalOrder());
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

        List<Method> methodList = Arrays.stream(t.getClass().getMethods())
                .filter(el -> el.getName().contains("get"))
                .sorted(Comparator.comparing(Method::getName))
                .toList();
        methodList.removeIf(el -> el.getName().equals("getId"));

        queryInsert = queryInsert.concat(" VALUES(");
        for(int i = 0; i < columnList.size() - 1; i++){
            if(i == methodList.size() - 1){
                queryInsert.concat("?,");
            }
            else{
                queryInsert.concat("?)");
            }
        }

        try(PreparedStatement statement = connection.prepareStatement(queryInsert)) {
            T temp = (T) t.getClass().newInstance();

            statement.setString(1, t.getClass().getSimpleName());
            for(int i = 0; i < columnList.size(); i++){
                if(methodList.get(i).invoke(temp) instanceof java.util.Date){
                    statement.setObject(i+2,
                            new java.sql.Date(
                                    ((java.util.Date)methodList.get(i).invoke(temp)).getTime()));
                }
                else{
                    statement.setObject(i+2, methodList.get(i));
                }
            }

            statement.executeUpdate();
            log.info("Object of {} like {} was saved", t.getClass().getSimpleName(), t);
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Class<T> tClass, ID id, Connection connection) {
        String tableName = getTableName(tClass.getSimpleName());
        String queryDelete = """
                DELETE FROM ?
                WHERE id = ?
                """;
        try(PreparedStatement statement = connection.prepareStatement(queryDelete)) {
            statement.setString(1, tableName);
            statement.setInt(2, (Integer)id);
            statement.executeUpdate();
            log.info("Object of {} with id {} was deleted", tClass.getSimpleName(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Class<T> tClass, ID id, String columnName, String value, Connection connection) {
        String tableName = getTableName(tClass.getSimpleName());
        String query = "UPDATE ".concat(tableName).concat(" SET ").concat(columnName).concat(" = ? WHERE id = ?");
        System.out.println("table_name: " + tableName);
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
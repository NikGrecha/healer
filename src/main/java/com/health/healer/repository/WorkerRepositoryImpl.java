package com.health.healer.repository;

import com.health.healer.models.Worker;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class WorkerRepositoryImpl extends JDBCCustomRepositoryImpl <Worker, Integer> implements WorkerRepository{

    @Override
    public boolean isWorkerExist(Connection connection, String mobile) {
        boolean userId = false;
        String query = """
                SELECT isworkerexist(?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, mobile);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                userId = resultSet.getBoolean(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId ;
    }

    @Override
    public void changeUserPassword(Connection connection, String login, String newPassword) {
        String query = "ALTER USER " + login + " WITH ENCRYPTED PASSWORD '" + newPassword + "'";
        try (Statement Statement = connection.createStatement()) {
            Statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String loginByMobile(Connection connection, String mobile) {
       String userLogin = "";
        String query = """
                SELECT login FROM worker WHERE mobile = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, mobile);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userLogin = resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userLogin;
    }


    public int getLastId(Connection connection) {
        int id = 0;
        String query = "SELECT last_value FROM worker_id_seq";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            id = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}

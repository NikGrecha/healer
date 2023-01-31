package com.health.healer.repository;

import com.health.healer.models.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerRepositoryImpl extends JDBCCustomRepositoryImpl <Worker, Integer> implements WorkerRepository{

    @Override
    public boolean isWorkerExist(Connection connection, String mobile) {
        int userId = 0;
        String query = """
                SELECT isworkerexist(?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, mobile);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                userId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId != 0;
    }

    @Override
    public void changeUserPassword(Connection connection, String login, String password) {

    }
}

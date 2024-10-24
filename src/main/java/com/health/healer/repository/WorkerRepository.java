package com.health.healer.repository;

import java.sql.Connection;
import java.sql.SQLException;

public interface WorkerRepository {
    boolean isWorkerExist (Connection connection, String mobile);
    void changeUserPassword (Connection connection, String login, String password);

    String loginByMobile (Connection connection, String mobile);

    int getLastId(Connection connection);

    int findIdByLogin(Connection connection, String login) throws SQLException;


}

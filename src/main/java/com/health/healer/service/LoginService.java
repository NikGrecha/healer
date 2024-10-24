package com.health.healer.service;

import com.health.healer.repository.CardRepositoryImpl;
import com.health.healer.repository.WorkerRepositoryImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
//@RequiredArgsConstructor
public class LoginService {

    @Autowired
    private WorkerRepositoryImpl workerRepository;
    @Autowired
    private CardRepositoryImpl cardRepository;

    public Connection getConnection(String name, String password) throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Healer", name, password);
    }

    public String getRole(String name, @NotNull Connection connection) throws SQLException {
        String role = null;
        String roleSQL = "SELECT rolname FROM pg_roles WHERE pg_has_role( '".concat(name).concat("', oid, 'member') AND rolname != '").concat(name).concat("' ");

        Statement statement = connection.createStatement();

        ResultSet resultSQL = statement.executeQuery(roleSQL);

        while(resultSQL.next()){
            role=resultSQL.getString(1);
        }

        return role;
    }

    public void createPgUser(String login, String password, String role, Connection connection){
        String query = "CREATE USER \"" + login + "\" WITH ENCRYPTED PASSWORD '" + password + "' IN ROLE \"" + role +"\"";
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int findIdByLogin(String login, Connection connection){
        int id = 0;

        try {
            id = workerRepository.findIdByLogin(connection, login);
        } catch (SQLException e) {
            try {
                id = cardRepository.findIdByLogin(connection, login);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        return id;
    }
}

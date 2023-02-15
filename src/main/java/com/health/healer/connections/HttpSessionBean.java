package com.health.healer.connections;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
@SessionScope
@Component
public class HttpSessionBean {
    Connection connection;
    int id;

    {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Healer",
                    "guest", "guest");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
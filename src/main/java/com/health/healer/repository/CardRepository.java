package com.health.healer.repository;

import com.health.healer.models.Card;
import com.health.healer.models.Recipe;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CardRepository {
    Card findByMobile(Connection connection, String mobile);

    int findIdByLogin(Connection connection, String login) throws SQLException;
}

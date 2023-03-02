package com.health.healer.repository;

import com.health.healer.models.Card;
import com.health.healer.models.VisitView;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CardRepositoryImpl extends JDBCCustomRepositoryImpl<Card, Integer> implements CardRepository{

    @Override
    public Card findByMobile(Connection connection, String mobile) {
        Card card = new Card();
        String query = """
                SELECT * FROM card
                WHERE mobile = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, mobile);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                card.setId(resultSet.getInt("id"));
                card.setSecondName(resultSet.getString("second_name"));
                card.setFirstName(resultSet.getString("first_name"));
                card.setThirdName(resultSet.getString("third_name"));
                card.setBirthday(resultSet.getDate("birthday"));
                card.setMobile(resultSet.getString("mobile"));
                card.setAllergy(resultSet.getString("allergy"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;
    }
}

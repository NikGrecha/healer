package com.health.healer.repository;

import com.health.healer.models.Visit;
import com.health.healer.models.VisitView;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class VisitRepositoryImpl extends JDBCCustomRepositoryImpl<Visit, Integer> implements VisitRepository{

    @Override
    public List<VisitView> findVisitByCardId(int cardId, Connection connection) {
        List<VisitView> visitList = new ArrayList<>();
        String query = """
                SELECT * FROM visit_view
                WHERE card_id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cardId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                VisitView visit = new VisitView();

                visit.setId(resultSet.getInt("id"));
                visit.setDoctorName(resultSet.getString("doctor_second_name") + " " +
                        resultSet.getString("doctor_first_name") + " " +
                        resultSet.getString("doctor_third_name"));
                visit.setCardId(resultSet.getInt("card_id"));
                visit.setComplaint(resultSet.getString("complaint"));
                visit.setCheckup(resultSet.getString("checkup"));
                visit.setDiagnosis(resultSet.getString("diagnosis"));
                visit.setDate(resultSet.getDate("date"));
                visitList.add(visit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visitList;
    }
}

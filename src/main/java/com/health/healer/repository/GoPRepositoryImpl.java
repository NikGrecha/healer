package com.health.healer.repository;

import com.health.healer.models.GoL;
import com.health.healer.models.GoP;
import com.health.healer.models.GoPView;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoPRepositoryImpl implements GoPRepository {
    @Override
    public List<GoPView> takeGoPByMobile(Connection connection, String mobile){
        List<GoPView> goPList = new ArrayList<>();
        String query = """
                SELECT * FROM go_p_view
                WHERE mobile = ? AND visit_left > 0
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, mobile);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                GoPView goPresult = new GoPView();

                goPresult.setId(resultSet.getInt("id"));
                goPresult.setVisitId(resultSet.getInt("visit_id"));
                goPresult.setProcedureType(resultSet.getString("procedure_type"));
                goPresult.setDopInfo(resultSet.getString("dop_info"));
                goPresult.setVisitLeft(resultSet.getInt("visit_left"));
                goPresult.setMobile(resultSet.getString("mobile"));
                goPList.add(goPresult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goPList;
    }
    @Override
    public void reduceVisits(Connection connection, int goPId){
        String query = """
                UPDATE go_p SET visit_left = visit_left-1
                WHERE id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, goPId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


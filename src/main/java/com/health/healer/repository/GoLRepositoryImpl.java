package com.health.healer.repository;

import com.health.healer.models.GoL;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoLRepositoryImpl implements GoLRepository {

    @Override
    public List<GoL> takeGoLByMobile(Connection connection, String mobile) {
        List<GoL> goLList = new ArrayList<>();
        String query = """
                SELECT * FROM go_l
                WHERE pacient_mobile = ? AND status = 'Не выполнен'
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, mobile);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                GoL goLresult = new GoL();

                goLresult.setId(resultSet.getInt("id"));
                goLresult.setVisitId(resultSet.getInt("visit_id"));
                goLresult.setAnalysisType(resultSet.getString("analysis_type"));
                goLresult.setDopInfo(resultSet.getString("dop_info"));
                goLresult.setWorkerId(resultSet.getInt("worker_id"));
                goLresult.setDateOfTaking(resultSet.getDate("date_of_taking"));
                goLresult.setStatus(resultSet.getString("status"));
                goLresult.setResult(resultSet.getString("result"));
                goLresult.setPacientMobile(resultSet.getString("pacient_mobile"));

                goLList.add(goLresult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goLList;
    }

    @Override
    public void upLoadResult(Connection connection, int goLId, int workerId, String resultUrl) {
        String query = """
                UPDATE go_l
                SET result = ?, worker_id = ?, status = 'Выполнен'
                WHERE id = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, resultUrl);
            preparedStatement.setInt(2, workerId);
            preparedStatement.setInt(3, goLId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

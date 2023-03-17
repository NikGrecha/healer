package com.health.healer.repository;

import com.health.healer.models.GoL;
import com.health.healer.models.GoLView;
import com.health.healer.models.GoPView;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoLRepositoryImpl extends JDBCCustomRepositoryImpl <GoL, Integer> implements GoLRepository {

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
                goLresult.setDateOfTaking(resultSet.getObject("date_of_taking", LocalDateTime.class));
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
    public List<GoLView> findGoLByCardId(int cardId, Connection connection) {
        List<GoLView> goLList = new ArrayList<>();
        String query = """
                SELECT * FROM go_l_view
                WHERE card_id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cardId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                GoLView goL = new GoLView();

                goL.setId(resultSet.getInt("id"));
                goL.setVisitId(resultSet.getInt("visit_id"));
                goL.setAnalysisType(resultSet.getString("analysis_type"));
                goL.setDopInfo(resultSet.getString("dop_info"));
                goL.setWorkerId(resultSet.getInt("worker_id"));
                goL.setDateOfTaking(resultSet.getObject("date_of_taking", LocalDateTime.class));
                goL.setStatus(resultSet.getString("status"));
                goL.setResult(resultSet.getString("result"));

                goLList.add(goL);
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

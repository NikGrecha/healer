package com.health.healer.repository;

import com.health.healer.models.GoL;
import com.health.healer.models.GoLView;

import java.sql.Connection;
import java.util.List;


public interface GoLRepository {
    List<GoL> takeGoLByMobile (Connection connection, String mobile);

    void upLoadResult (Connection connection, int goLId, int workerId, String resultUrl);

    List<GoLView> findGoLByCardId(int cardId, Connection connection);
}

package com.health.healer.repository;

import com.health.healer.models.GoPView;


import java.sql.Connection;
import java.util.List;

public interface GoPRepository {
    List<GoPView> takeGoPByMobile (Connection connection, String mobile);

    void reduceVisits (Connection connection, int goPId);

    List<GoPView> findGoPByCardId(int cardId, Connection connection);
}

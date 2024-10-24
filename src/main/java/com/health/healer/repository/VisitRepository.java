package com.health.healer.repository;

import com.health.healer.models.Visit;
import com.health.healer.models.VisitView;

import java.sql.Connection;
import java.util.List;

public interface VisitRepository {
    List<VisitView> findVisitByCardId(int cardId, Connection connection);

    void addVisit (Connection connection, int workerId, int cardId, String complaint, String checkup, String diagnosis);
}

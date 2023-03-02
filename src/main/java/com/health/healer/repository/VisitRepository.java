package com.health.healer.repository;

import com.health.healer.models.Visit;
import com.health.healer.models.VisitView;

import java.sql.Connection;
import java.util.List;

public interface VisitRepository {
    List<VisitView> findByCardId(int cardId, Connection connection);
}

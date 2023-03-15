package com.health.healer.service;

import com.health.healer.models.VisitView;
import com.health.healer.repository.VisitRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class VisitService {
    @Autowired
    private VisitRepositoryImpl visitRepositoryImpl;

    public List<VisitView> findVisitByCardId (int cardId, Connection connection){
        return visitRepositoryImpl.findVisitByCardId(cardId, connection);
    }

}

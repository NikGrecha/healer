package com.health.healer.service;

import com.health.healer.models.GoP;
import com.health.healer.models.GoPView;
import com.health.healer.repository.GoPRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class GoPService {
    @Autowired
    private GoPRepositoryImpl goPRepositoryImpl;

    public List<GoPView> takeGoPByMobile(Connection connection, String mobile) {
        return goPRepositoryImpl.takeGoPByMobile(connection, mobile);
    }

    public void reduceVisits(Connection connection, int goPId){
        goPRepositoryImpl.reduceVisits(connection, goPId);
    }

    public List<GoPView> findGoPByCardId (int cardId, Connection connection){
        return goPRepositoryImpl.findGoPByCardId(cardId, connection);
    }

    public void save(Connection connection, GoP goP) {
        goPRepositoryImpl.save(goP, connection);
    }
}
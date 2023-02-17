package com.health.healer.service;

import com.health.healer.models.GoL;
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
    private GoPRepositoryImpl goPRepository;

    public List<GoPView> takeGoPByMobile(Connection connection, String mobile) {
        return goPRepository.takeGoPByMobile(connection, mobile);
    }

    public void reduceVisits(Connection connection, int goPId){
        goPRepository.reduceVisits(connection, goPId);
    }
}

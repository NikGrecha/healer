package com.health.healer.service;

import com.health.healer.repository.WorkerRepository;
import com.health.healer.repository.WorkerRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepositoryImpl workerRepository;

    public boolean changeUserPassword(Connection connection,  String newPassword, String mobile) {
        if(workerRepository.isWorkerExist(connection, mobile)){
            String login = workerRepository.loginByMobile(connection, mobile);
            workerRepository.changeUserPassword(connection, login, newPassword);
            return true;
        }
        return false;
    }
}

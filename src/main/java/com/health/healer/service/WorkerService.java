package com.health.healer.service;

import com.health.healer.models.Worker;
import com.health.healer.repository.WorkerRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class WorkerService{
    @Autowired
    private WorkerRepositoryImpl workerRepository;
    @Autowired
    private LoginService loginService;
    public int findIdByLogin(Connection connection, String login) throws SQLException {
        return workerRepository.findIdByLogin(connection, login);
    }

    public boolean changeUserPassword(Connection connection,  String newPassword, String mobile) {
        if(workerRepository.isWorkerExist(connection, mobile)){
            String login = workerRepository.loginByMobile(connection, mobile);
            workerRepository.changeUserPassword(connection, login, newPassword);
            return true;
        }
        return false;
    }

    public void save(Worker worker, String password, Connection connection){
        String department = switch (worker.getDepartment()) {
            case "Врач" -> "doctor";
            case "Лаборатория" -> "lab";
            case "Процедурная" -> "physio";
            default -> "user";
        };

        String login = department + workerRepository.getLastId(connection);
        worker.setLogin(login);
        workerRepository.save(worker, connection);

        loginService.createPgUser(login, password, department, connection);
    }
}

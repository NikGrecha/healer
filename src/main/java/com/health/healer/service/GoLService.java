package com.health.healer.service;

import com.health.healer.models.GoL;
import com.health.healer.models.GoLView;
import com.health.healer.models.GoPView;
import com.health.healer.repository.GoLRepository;
import com.health.healer.repository.GoLRepositoryImpl;
import com.health.healer.repository.WorkerRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@Service
public class GoLService {
    @Value("${filepath}")
    private String filepath;
    @Autowired
    private GoLRepositoryImpl goLRepositoryImpl;

    public List<GoL> takeGoLByMobile(Connection connection, String mobile) {
        return goLRepositoryImpl.takeGoLByMobile(connection, mobile);
    }

    public List<GoLView> findGoLByCardId (int cardId, Connection connection){
        return goLRepositoryImpl.findGoLByCardId(cardId, connection);
    }

    public void saveDocument(MultipartFile file, int goLId, int workerId, Connection connection){

        try {
            String newFileName = file.getOriginalFilename();

            File newFile = new File(filepath  + file.getOriginalFilename());

            if(!newFile.exists()){
                newFile.mkdirs();
            }

            file.transferTo(newFile);

            goLRepositoryImpl.upLoadResult(connection, goLId, workerId, file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(Connection connection, GoL goL) {
        goLRepositoryImpl.save(goL, connection);
    }
}

package com.health.healer.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class GoL {
    private int id;

    private int visitId;

    private String analysisType;

    private String dopInfo;

    private int workerId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfTaking;

    private String status;

    private String result;
}

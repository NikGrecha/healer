package com.health.healer.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class GoL {
    private Integer id;

    private Integer visitId;

    private String analysisType;

    private String dopInfo;

    private Integer workerId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfTaking;

    private String status;

    private String result;

    private String pacientMobile;
}

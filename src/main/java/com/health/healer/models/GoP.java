package com.health.healer.models;

import lombok.*;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class GoP {
    private Integer id;

    private Integer visitId;

    private String procedureType;

    private String dopInfo;

    private Integer visitLeft;
}

package com.health.healer.models;

import lombok.*;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class GoP {
    private int id;

    private int visitId;

    private String procedureType;

    private String dopInfo;

    private int visitLeft;
}

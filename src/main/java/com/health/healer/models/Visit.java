package com.health.healer.models;

import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class Visit {

    private int id;

    private int doctorId;

    private int cardId;

    private String complaint;

    private String checkup;

    private String diagnosis;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTime;
}

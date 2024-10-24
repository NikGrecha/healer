package com.health.healer.models;

import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class Visit {

    private Integer id;

    private Integer doctorId;

    private Integer cardId;

    private String complaint;

    private String checkup;

    private String diagnosis;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;


}

package com.health.healer.models;

import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Worker {

    private Integer id;

    private String secondName;

    private String firstName;

    private String thirdName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String mobile;

    private String department;

    private String speciality;

    private String login;
}

package com.health.healer.models;

import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {
    private Integer id;

    private String secondName;

    private String firstName;

    private String thirdName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTime;

    private String mobile;

    private String allergy;
}

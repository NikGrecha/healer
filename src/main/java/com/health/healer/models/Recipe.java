package com.health.healer.models;

import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Recipe {
    private int id;

    private int visitId;

    private String drugName;

    private int length;

    private String frequency;

    private String dopInfo;
}

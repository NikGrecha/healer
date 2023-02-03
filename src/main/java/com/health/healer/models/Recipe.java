package com.health.healer.models;

import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Recipe {
    private Integer id;

    private Integer visitId;

    private String drugName;

    private Integer length;

    private String frequency;

    private String dopInfo;
}

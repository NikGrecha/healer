package com.health.healer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecipeView {
    private Integer id;
    private Integer doctorId;
    private Integer cardId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Integer visitId;
    private String drugName;
    private Integer length;
    private String frequency;
    private String dopInfo;
}

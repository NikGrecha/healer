package com.health.healer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VisitView {
    private Integer id;

    private String doctorName;

    private Integer cardId;

    private String complaint;

    private String checkup;

    private String diagnosis;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}

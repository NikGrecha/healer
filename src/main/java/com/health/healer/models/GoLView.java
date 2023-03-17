package com.health.healer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoLView {
    private Integer id;
    private Integer visitId;
    private String analysisType;
    private String dopInfo;
    private Integer workerId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateOfTaking;
    private String status;
    private String result;
    private Integer cardId;
}

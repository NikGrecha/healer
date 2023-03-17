package com.health.healer.models;

import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class VisitP {
    private Integer id;

    private Integer workerId;

    private Integer goPid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateTime;
}

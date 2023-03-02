package com.health.healer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoPView {
    private Integer id;
    private Integer visitId;
    private String procedureType;
    private String dopInfo;
    private Integer visitLeft;
    private String mobile;
}

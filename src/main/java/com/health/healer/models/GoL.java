package com.health.healer.models;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
//@Data
@Entity
@Table(name="go_l")

public class GoL {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="visit_id")
    private int visitId;

    @Column(name="analysis_type")
    private String analysisType;

    @Column(name="dop_info")
    private String dopInfo;

    @Column(name="worker_id")
    private int workerId;

    @Column(name="date_of_taking")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfTaking;

    @Column(name="status")
    private String status;

    @Column(name="result")
    private String result;
}

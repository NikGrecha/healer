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
@Data
@Entity
@Table(name="visit")

public class Visit {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="doctor_id")
    private int doctorId;

    @Column(name="card_id")
    private int cardId;

    @Column(name="complaint")
    private String complaint;

    @Column(name="checkup")
    private String checkup;

    @Column(name="diagnosis")
    private String diagnosis;

    @Column(name="date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTime;
}

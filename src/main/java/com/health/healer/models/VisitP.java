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
@Table(name="visit_p")

public class VisitP {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="worker_id")
    private int workerId;

    @Column(name="go_p_id")
    private int goPid;

    @Column(name="date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTime;
}

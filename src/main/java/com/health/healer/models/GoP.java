package com.health.healer.models;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="go_p")

public class GoP {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="visit_id")
    private int visitId;

    @Column(name="procedure_type")
    private String procedureType;

    @Column(name="dop_info")
    private String dopInfo;

    @Column(name="visit_left")
    private int visitLeft;
}

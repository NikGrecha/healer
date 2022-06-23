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
@Table(name="recipe")
public class Recipe {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="visit_id")
    private int visitId;

    @Column(name="drug_name")
    private String drugName;

    @Column(name="length")
    private int length;

    @Column(name="frequency")
    private String frequency;

    @Column(name="dop_info")
    private String dopInfo;
}

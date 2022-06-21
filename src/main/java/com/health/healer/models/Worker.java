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
@Table(name="worker")
public class Worker {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="second_name")
    private String secondName;

    @Column(name="first_name")
    private String firstName;

    @Column(name="third_name")
    private String thirdName;

    @Column(name="birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTime;

    @Column(name="mobile")
    private String mobile;

    @Column(name="department")
    private String department;

    @Column(name="speciality")
    private String speciality;
}

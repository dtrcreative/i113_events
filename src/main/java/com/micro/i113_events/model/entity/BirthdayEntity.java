package com.micro.i113_events.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "birthdays")
public class BirthdayEntity implements Comparable<BirthdayEntity> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private Date date;
    private boolean notify;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Override
    public int compareTo(BirthdayEntity o) {
        if (firstName.equals(o.getFirstName()) && lastName.equals(o.getLastName())) {
            return 0;
        } else {
            return 1;
        }
    }
}

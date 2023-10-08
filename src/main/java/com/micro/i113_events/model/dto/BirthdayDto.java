package com.micro.i113_events.model.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BirthdayDto implements Comparable<BirthdayDto> {

    private int id;
    @NonNull
    private String userId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private Date date;

    private boolean notify;

    private String description;

    private String daysLeft;

    @Override
    public int compareTo(BirthdayDto o) {
        return this.daysLeft.compareTo(o.daysLeft);
    }
}

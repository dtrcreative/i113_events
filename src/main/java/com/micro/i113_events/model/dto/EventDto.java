package com.micro.i113_events.model.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EventDto implements Comparable<EventDto> {

    private int id;
    @NonNull
    private String userId;
    @NonNull
    private String eventName;
    @NonNull
    private Date date;

    private boolean notify;

    private String description;

    private String daysLeft;

    @Override
    public int compareTo(EventDto o) {
        return this.daysLeft.compareTo(o.daysLeft);
    }
}

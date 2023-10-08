package com.micro.i113_events.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {

    @NonNull
    private String regUser;
    @NonNull
    private String message;
}

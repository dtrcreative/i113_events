package com.micro.i113_events.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelegramDto {

    @NonNull
    private String userId;
    @NonNull
    private String message;

}

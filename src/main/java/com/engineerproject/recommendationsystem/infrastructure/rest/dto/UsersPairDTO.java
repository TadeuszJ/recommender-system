package com.engineerproject.recommendationsystem.infrastructure.rest.dto;

import lombok.Data;

@Data
public class UsersPairDTO {
    private final String activeUserId;
    private final String selectedUserId;
}

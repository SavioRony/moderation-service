package com.dante.moderation_service.api.model;

import lombok.Data;

@Data
public class ModerationOutput {
    private boolean approved;
    private String reason;
}

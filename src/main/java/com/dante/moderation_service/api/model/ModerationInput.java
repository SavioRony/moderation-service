package com.dante.moderation_service.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModerationInput {
    private String text;
    private String commentId;
}

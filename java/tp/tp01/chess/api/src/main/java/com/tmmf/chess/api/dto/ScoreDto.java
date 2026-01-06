package com.tmmf.chess.api.dto;

import com.tmmf.chess.api.entity.Score;

import java.time.LocalDateTime;
import java.util.UUID;

public record ScoreDto(
        UUID id,
        int value,
        LocalDateTime createdAt
) {
    public static ScoreDto to(Score score) {
        return new ScoreDto(UUID.fromString(score.getUuid()), score.getValue(), score.getCreatedAt());
    }
    public static Score from(ScoreDto scoreDto) {
        return new Score(scoreDto.id.toString(), scoreDto.value, scoreDto.createdAt);
    }
}

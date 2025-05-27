package com.tmmf.chess.api.dto;

import java.util.UUID;

public record ScoreDeclarationDto(UUID userUUId, ScoreDto score) {
}

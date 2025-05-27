package com.tmmf.chess.api.dto;

import com.tmmf.chess.api.entity.Player;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record PlayerDto(
        UUID uuid,
        String name,
        Set<ScoreDto> scores
) {
    public static PlayerDto to(Player player) {
        Set<ScoreDto> scoresDto = player.getScores()
                .stream().map(ScoreDto::to).collect(Collectors.toSet());
        return new PlayerDto(UUID.fromString(player.getUuid()), player.getName(), scoresDto);
    }
}

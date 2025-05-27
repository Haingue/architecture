package com.tmmf.chess.api.controller;

import com.tmmf.chess.api.dto.PlayerDto;
import com.tmmf.chess.api.dto.ScoreDeclarationDto;
import com.tmmf.chess.api.dto.ScoreDto;
import com.tmmf.chess.api.entity.Player;
import com.tmmf.chess.api.exception.ChessApiException;
import com.tmmf.chess.api.service.ScoringService;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scoring")
public class ScoringController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScoringController.class);

    private ScoringService scoringService;

    public ScoringController(ScoringService scoringService) {
        this.scoringService = scoringService;
    }

    @GetMapping("/top")
    public ResponseEntity<?> getTopPlayers(@PathParam("number") int number) {
        LOGGER.info("Load top player (number: {})", number);
        if (number == 0) number = 5;
        if (number > 10) number = 10;

        try {
            List<PlayerDto> topPlayers = scoringService.getTopPlayers(number)
                    .stream().map(PlayerDto::to).toList();
            return ResponseEntity.ok(topPlayers);
        } catch (ChessApiException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<?> postDeclareScore(@RequestBody ScoreDeclarationDto scoreDeclarationDto) {
        LOGGER.info("Declare new score (scoreDeclarationDto: {})", scoreDeclarationDto);
        try {
            Player player = this.scoringService.declareScore(scoreDeclarationDto.userUUId(), ScoreDto.from(scoreDeclarationDto.score()));
            return ResponseEntity.ok(PlayerDto.to(player));
        } catch (ChessApiException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

}

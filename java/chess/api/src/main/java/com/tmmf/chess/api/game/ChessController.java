package com.tmmf.chess.api.game;

import com.tmmf.chess.api.exception.GameNotFoundException;
import com.tmmf.chess.core.exceptions.MoveNotPermitException;
import com.tmmf.chess.core.game.Board;
import com.tmmf.chess.core.game.ChessGame;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chess")
public class ChessController {

    private final Logger LOGGER = LoggerFactory.getLogger(ChessController.class);

    private final ChessGame<String> chessGame;

    public ChessController(ChessGame<String> chessGame) {
        this.chessGame = chessGame;
    }

    @GetMapping
    public ResponseEntity<Board> getChessGame(@RequestParam("gameCode") String gameCode) {
        LOGGER.info("Get chess game: {}", gameCode);
        try {
            return ResponseEntity.ok(chessGame.getBoard(gameCode));
        } catch (GameNotFoundException error) {
            return ResponseEntity.notFound().build();
        } catch (Exception error) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> postNewGame() {
        try {
            String gameCode = chessGame.createBoard();
            Board boardModel = chessGame.getBoard(gameCode);
            return ResponseEntity.ok(new GameDto(gameCode, boardModel));
        } catch (Exception error) {
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @PutMapping
    public ResponseEntity<?> putMove(@RequestBody MoveDto moveDto) {
        try {
            return ResponseEntity.ok(chessGame.playNextMove(moveDto.gameCode(), moveDto.from(), moveDto.to()));
        } catch (MoveNotPermitException error) {
            return ResponseEntity.badRequest().body(error);
        } catch (Exception error) {
            return ResponseEntity.internalServerError().body(error);
        }
    }
}

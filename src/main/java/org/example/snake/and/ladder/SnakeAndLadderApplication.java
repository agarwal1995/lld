package org.example.snake.and.ladder;

import org.example.snake.and.ladder.enums.Color;
import org.example.snake.and.ladder.enums.GameStatus;
import org.example.snake.and.ladder.models.User;
import org.example.snake.and.ladder.services.impl.BoardService;
import org.example.snake.and.ladder.services.impl.GameService;
import org.example.snake.and.ladder.services.impl.NormalDice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author raag
 */
public class SnakeAndLadderApplication {

    public static void main(String[] args) throws IOException {
        GameService gameService = new GameService(new BoardService(), new NormalDice(), 2, 15);

        System.out.println(gameService.getGameStatus());

        User user1 = new User("Rahul", "jhbnm,");
        User user2 = new User("Rahul2", "jhbnm,");

        Map<Color, User> colorUserMap = new HashMap<>();
        colorUserMap.put(Color.GREEN, user1);
        colorUserMap.put(Color.BLUE, user2);

        gameService.setup(new HashMap<>(), new HashMap<>(), colorUserMap);

        System.out.println(gameService.getGameStatus());

        gameService.startGame();

        System.out.println(gameService.getCurrentPlayer());
        System.out.println(gameService.getGameStatus());

        do {
            gameService.playTurn();
        } while (!gameService.getGameStatus().equals(GameStatus.FINISHED));

    }
}

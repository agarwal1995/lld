package org.example.snake.and.ladder.models;

import org.example.snake.and.ladder.enums.GameStatus;

import java.util.List;

/**
 * @author raag
 */
public class Game {
    List<Player> players;
    Board board;
    Dice dice;
    int numberOfPlayers;
    Player currentPlayer;
    GameStatus gameStatus;
}

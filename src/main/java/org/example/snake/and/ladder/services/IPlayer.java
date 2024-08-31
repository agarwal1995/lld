package org.example.snake.and.ladder.services;

import org.example.snake.and.ladder.enums.Color;
import org.example.snake.and.ladder.models.Player;
import org.example.snake.and.ladder.models.User;

import java.util.List;
import java.util.Map;

/**
 * @author raag
 */
public interface IPlayer {

    void move(Player player, int pawnNumber, int numPosition);

    List<Player> createPlayer(Map<Color, User> colorUserMap, int pawns);

    int nextTurn(int currentPlayer, List<Player> players);

}

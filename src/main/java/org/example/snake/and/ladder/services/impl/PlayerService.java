package org.example.snake.and.ladder.services.impl;

import org.example.snake.and.ladder.enums.Color;
import org.example.snake.and.ladder.models.Pawn;
import org.example.snake.and.ladder.models.Player;
import org.example.snake.and.ladder.models.User;
import org.example.snake.and.ladder.services.IDice;
import org.example.snake.and.ladder.services.IPlayer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author raag
 */
public class PlayerService implements IPlayer {

    IDice dice;

    public PlayerService(IDice dice) {
        this.dice = dice;
    }

    @Override
    public void move(Player player, int pawnNumber, int numPositions) {
        player.getPawns().get(pawnNumber).setPos(numPositions);
    }

    public List<Player> createPlayer(Map<Color, User> colorUserMap, int pawns) {
        return colorUserMap.entrySet().stream()
                .map(colorUserEntry -> new Player(
                        colorUserEntry.getValue(),
                        IntStream
                                .range(0, pawns)
                                .mapToObj(x -> new Pawn(colorUserEntry.getKey()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public int nextTurn(int currentPlayer, List<Player> players) {
        return  (currentPlayer+1)%players.size();
    }

}

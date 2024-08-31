package org.example.snake.and.ladder.services;

import org.example.snake.and.ladder.models.Pawn;
import org.example.snake.and.ladder.models.Player;

import java.util.List;
import java.util.Map;

/**
 * @author raag
 */
public interface IRule {

    boolean hasStart(Pawn player, List<Integer> diceRolls);

    boolean validRoll(List<Integer> diceRolls);

    boolean chanceRepeated(List<Integer> diceRolls, Player player, int size);

    boolean chanceRepeated( int size, int newPos);

    boolean validMove(int size,int oldPosition, int newPostition);

    boolean isWinner(Player player, int size);

    int getNewPosition(int oldPosition, int diceRoll);
}

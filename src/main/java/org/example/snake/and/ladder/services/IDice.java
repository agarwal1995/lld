package org.example.snake.and.ladder.services;

import org.example.snake.and.ladder.models.Dice;

/**
 * @author raag
 */
public interface IDice {

    void createDice();

    int roll();

    Dice getDice();

    boolean isRepeated(int x);
}

package org.example.snake.and.ladder.services;

import org.example.snake.and.ladder.models.Board;

import java.util.Map;

/**
 * @author raag
 */
public interface IBoardService {

    void setupBoard(int size, Map<Integer, Integer> snakes, Map<Integer,Integer> ladders);

    int newPosition(int position);
}

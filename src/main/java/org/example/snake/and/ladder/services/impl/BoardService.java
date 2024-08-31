package org.example.snake.and.ladder.services.impl;

import org.example.snake.and.ladder.models.Board;
import org.example.snake.and.ladder.services.IBoardService;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author raag
 */
public class BoardService implements IBoardService  {

    Board board;

    public BoardService() {
    }

    public void setupBoard(int size, Map<Integer, Integer> snakes, Map<Integer,Integer> ladders) {
        this.board = Board.builder()
                .cells(IntStream.of(size).boxed().collect(Collectors.toList()))
                .snakes(snakes
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue
                        )))
                .ladders(ladders
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue
                        )))
                .build();
    }

    public int newPosition(int position) {
        if (board.getLadders().containsKey(position)) {
            return board.getLadders().get(position);
        }
        if (board.getSnakes().containsKey(position)) {
            return board.getLadders().get(position);
        }
        return position;
    }
}

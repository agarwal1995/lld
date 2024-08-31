package org.example.snake.and.ladder.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author raag
 */
@Data
@Builder
public class Board {
    List<Integer> cells;
    Map<Integer, Integer> snakes;
    Map<Integer, Integer> ladders;
}

package org.example.snake.and.ladder.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @author raag
 */
@Data
@AllArgsConstructor
public class Dice {
    final int numberOfFaces;
    final Map<Integer, Integer> faceToValueMap;
}

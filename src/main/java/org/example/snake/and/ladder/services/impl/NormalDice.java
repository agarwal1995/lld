package org.example.snake.and.ladder.services.impl;

import lombok.Getter;
import org.example.snake.and.ladder.models.Dice;
import org.example.snake.and.ladder.services.IDice;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author raag
 */
@Getter
public class NormalDice implements IDice {

    Dice dice;

    public NormalDice() {
        createDice();
    }

    public void createDice() {
        Map<Integer, Integer> faceValueMap = new HashMap<>() {{
            put(1,1);
            put(2,2);
            put(3,3);
            put(4,4);
            put(5,5);
            put(6,6);
        }};
        this.dice = new Dice(6, faceValueMap);
    }

    @Override
    public int roll() {
        return new Random().nextInt(6)+1;
    }

    @Override
    public boolean isRepeated(int x) {
        return x==6;
    }
}

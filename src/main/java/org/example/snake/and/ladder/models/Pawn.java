package org.example.snake.and.ladder.models;

import lombok.Data;
import lombok.ToString;
import org.example.snake.and.ladder.enums.Color;

/**
 * @author raag
 */
@Data
@ToString
public class Pawn {
    final Color color;
    int pos;

    public Pawn(Color color) {
        pos = -1;
        this.color = color;
    }

    public void changePosition(int newPosition) {
        this.pos=newPosition;
    }
}

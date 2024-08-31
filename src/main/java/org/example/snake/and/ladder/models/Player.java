package org.example.snake.and.ladder.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author raag
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Player extends User {

    List<Pawn> pawns;

    public Player(String name, String email, List<Pawn> pawns) {
        super(name, email);
        this.pawns = pawns;
    }

    public Player(User user, List<Pawn> pawns) {
        super(user.name, user.getEmail());
        this.pawns = pawns;
    }
}

package org.example.snake.and.ladder.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author raag
 */
@Data
@NoArgsConstructor
public class User {
    String name;

    String email;
    String id;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.id = UUID.randomUUID().toString();
    }
}

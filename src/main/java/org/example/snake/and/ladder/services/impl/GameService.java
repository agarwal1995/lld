package org.example.snake.and.ladder.services.impl;

import lombok.Getter;
import org.example.snake.and.ladder.enums.Color;
import org.example.snake.and.ladder.enums.GameStatus;
import org.example.snake.and.ladder.models.*;
import org.example.snake.and.ladder.services.IBoardService;
import org.example.snake.and.ladder.services.IDice;
import org.example.snake.and.ladder.services.IPlayer;
import org.example.snake.and.ladder.services.IRule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.*;

/**
 * @author raag
 */
@Getter
public class GameService {
    List<Player> players;
    int noOfPawns;
    int numberOfPlayers;

    int size;
    Player currentPlayer;
    int currentPlayerIndex;
    GameStatus gameStatus;
    IBoardService boardService;
    IDice diceService;
    IPlayer playerService;
    IRule ruleService;
    int numberOfDices = 1;

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public GameService(IBoardService boardService, IDice dice , int noOfPawns, int size) {
        this.boardService = boardService;
        this.diceService = dice;
        this.playerService = new PlayerService(dice);
        this.noOfPawns = noOfPawns;
        this.size = size;
        gameStatus = GameStatus.READY;
        this.ruleService = new RuleService(numberOfDices, size);
    }

    public void setup(Map<Integer, Integer> snakes,
                      Map<Integer, Integer> ladders,
                      Map<Color, User> colorUserMap) {
        boardService.setupBoard(size, snakes, ladders);
        players = playerService.createPlayer(colorUserMap, noOfPawns);
        this.numberOfPlayers = players.size();
    }

    public void startGame() {
        int randomStart = new Random().nextInt(numberOfPlayers);
        currentPlayerIndex = randomStart;
        currentPlayer = players.get(randomStart);
        gameStatus = GameStatus.IN_PROGRESS;
    }

    public void playTurn() throws IOException {
        if (!GameStatus.IN_PROGRESS.equals(gameStatus)) {
            throw new RemoteException("wrong state");
        }

        List<Integer> diceRolls = new LinkedList<>();
        boolean isRepeated = true;
        while (isRepeated) {
            int diceNumber = numberOfDices;
            while (diceNumber>0) {
                diceRolls.add(diceService.roll());
                diceNumber--;
            }
            if (!ruleService.validRoll(diceRolls)) {
                diceRolls.clear();
                continue;
            }
            isRepeated = ruleService.chanceRepeated(diceRolls, currentPlayer, size);
        }


        int n = diceRolls.size();
        int x = 0;
        while (n>x) {
            System.out.println("Player Pawns at");
            for (Pawn pawn:currentPlayer.getPawns()) {
                System.out.println(pawn);
            }
            System.out.println("Dices : " + diceRolls);
            System.out.println("Select Player and Move");
            int pawnNumber = Integer.parseInt(br.readLine().trim());
            int diceRoll = diceRolls.get(x);
            int oldPosition = currentPlayer.getPawns().get(pawnNumber).getPos();
            int newPosition = ruleService.getNewPosition(oldPosition, diceRoll);
            if (!ruleService.validMove(size, oldPosition, newPosition)) {
                System.out.println("Wrong move");
                break;
            }
            newPosition = boardService.newPosition(newPosition);
            playerService.move(currentPlayer, pawnNumber, newPosition);
            boolean isWinner = ruleService.isWinner(currentPlayer, size);
            if (isWinner) {
                System.out.println("Player: "+ currentPlayer.getName() + "has won");
                gameStatus = GameStatus.FINISHED;
                return;
            }
            x++;
        }
        nextTurn();
    }

    private void nextTurn() {
        currentPlayerIndex =  playerService.nextTurn(currentPlayerIndex, players);
        currentPlayer = players.get(currentPlayerIndex);
    }
}

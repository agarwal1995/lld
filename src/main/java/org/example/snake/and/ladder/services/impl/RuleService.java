package org.example.snake.and.ladder.services.impl;

import org.example.snake.and.ladder.models.Pawn;
import org.example.snake.and.ladder.models.Player;
import org.example.snake.and.ladder.services.IDice;
import org.example.snake.and.ladder.services.IRule;

import java.util.*;

/**
 * @author raag
 */
public class RuleService implements IRule {

    int numberOfDice;
    int maxConsecutiveSixes;

    RuleService(int numberOfDice, int size) {
        this.numberOfDice = numberOfDice;
        maxConsecutiveSixes = numberOfDice*2;
    }

    @Override
    public boolean hasStart(Pawn pawn, List<Integer> diceRolls) {
        int size = diceRolls.size();
        int k = size-1;
        for (int i=numberOfDice;i>0;i--) {
            if (diceRolls.get(k)!=6) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean validRoll(List<Integer> diceRolls) {
        int hasConsecutiveSixes = 0;
        for (Integer diceRollCurrent:diceRolls) {
            hasConsecutiveSixes += diceRollCurrent==6?1:0;
            if (hasConsecutiveSixes==maxConsecutiveSixes) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean chanceRepeated(List<Integer> diceRolls, Player player, int boardSize) {
        System.out.println(diceRolls);
        if (diceRolls.contains(6)) {
            System.out.println("fsd");
        }
        int size = diceRolls.size();
        int k = size-1;
        for (int i=numberOfDice;i>0;i--) {
            if (diceRolls.get(k)!=6) {
                return false;
            }
        }

        int sum = diceRolls.stream().mapToInt(Integer::intValue).sum();
        int remainingPositions = player.getPawns().stream()
                .filter(pawn -> pawn.getPos()>=0 && pawn.getPos()<boardSize)
                .mapToInt(x-> boardSize-x.getPos())
                .sum();
        if (remainingPositions==0) {
            return true;
        }
        return sum <= remainingPositions;
    }

    @Override
    public boolean chanceRepeated( int size, int newPos) {
        return newPos == size;
    }

    @Override
    public boolean validMove(int size,int oldPosition, int newPostition) {
        if (oldPosition==-1 && newPostition!=0) {
            return false;
        }
        return newPostition <= size;
    }

    @Override
    public boolean isWinner(Player player, int size) {
        return player.getPawns().stream().allMatch(x->x.getPos()==size);
    }

    @Override
    public int getNewPosition(int oldPosition, int diceRoll) {
        if (oldPosition==-1 && diceRoll==6) {
            return 0;
        }
        return oldPosition+diceRoll;
    }
}

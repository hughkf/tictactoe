package tictactoe.strategies;

import java.util.*;
import tictactoe.Board;
import tictactoe.Board.Square;

/**
 * @author hugh
 */
public class RandomStrategy extends Strategy {
    
    public RandomStrategy(Board b){
        super(b);
    }
    
    public Square getBestSquare() {
        int randomIndex = Math.abs(new Random().nextInt() ) % theBoard.getCache().size();
        try {
            Square sq = (Square) theBoard.getCache().elementAt(randomIndex);
            return (sq == null || sq.occupied()) ? null: sq;
        } catch (NoSuchElementException e) {
            return null;
        }
    }
} // class RandomStrategy

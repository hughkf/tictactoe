package tictactoe.strategies;

import java.util.*;
import tictactoe.Board;
import tictactoe.players.MachinePlayer;

/**
 *
 * @author hugh
 */
public class RandomStrategy extends Strategy {
    
    public RandomStrategy(Board b){
        super(b);
    }
    
    public Board.Square getBestSquare() {
        Board.Square sq;
        int randomIndex = Math.abs(new Random().nextInt() ) % theBoard.getCache().size();
        try {
            sq = (Board.Square) theBoard.getCache().elementAt(randomIndex);
        } catch (NoSuchElementException e) {
            return null;
        }
        return sq.occupied() ? null: sq;
    }

}

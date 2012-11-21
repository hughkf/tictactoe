package tictactoe.strategies;

import java.util.NoSuchElementException;
import tictactoe.Board;

/**
 * @author hugh
 */
public abstract class Strategy { 
    protected Board theBoard;
        
    public Strategy(Board b){
        theBoard = b;
    }
            
    public abstract Board.Square getBestSquare();    

    public Board.Square findVacantSquare() {
        Board.Square sq;
        try {
            sq = (Board.Square) theBoard.getCache().lastElement(); 
        } catch (NoSuchElementException e) {
            return null;
        }
        return sq.occupied() ? null: sq;
    }
}

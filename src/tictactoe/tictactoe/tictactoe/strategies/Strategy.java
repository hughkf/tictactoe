package tictactoe.strategies;

import java.util.NoSuchElementException;
import tictactoe.Board;
import tictactoe.players.Player;

/**
 * @author hugh
 */
public abstract class Strategy { 
    protected Board theBoard;
    protected static Player player;
        
    public Strategy(Board b){
        theBoard = b;
    }

    public Strategy(Board b, Player p){
        theBoard = b;
        player = p;
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

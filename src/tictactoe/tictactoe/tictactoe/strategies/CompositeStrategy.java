package tictactoe.strategies;

import java.util.*;
import tictactoe.Board;
import tictactoe.players.Player;
import tictactoe.strategies.Sort.Comparator;

/**
 * @author hugh
 */
public class CompositeStrategy extends Strategy {        
    
    public CompositeStrategy(Board b){
        super(b);
    }
    
    public Board.Square getBestSquare() {        
        //first block, if the human can win on the next move
        Board.Square max = getMaxPiece(theBoard.getHumanPlayer());
        if (max != null && max.getTotalScore(theBoard.getHumanPlayer()) +1 == Board.GRID_SIZE() ){
            return max;
        }
        //Otherwise, find the square with the highest machine score, to win
        return getMaxPiece(theBoard.getMachinePlayer());        
    }
        
    protected Board.Square getMaxPiece(Player p) {
        Object[] squares = this.getSortedSquares(p);  
        return (squares.length == 0)? null: (Board.Square) squares[squares.length-1];            
    }

    protected Object[] getSortedSquares(Player p){
        Vector vacancies = theBoard.getCache();
        Board.Square[] squares = new Board.Square[vacancies.size()];
        for (int i=0; i<vacancies.size(); i++)
            squares[i] = (Board.Square) vacancies.elementAt(i);
        return Sort.sort(squares, new SquareComparator(this.theBoard, p));
    }

    class SquareComparator implements Comparator {
        private Board theBoard;
        private Player player;
        
        public SquareComparator(Board b, Player p){
            theBoard = b;
            player = p;
        }
        
        public int compare(Object obj1, Object obj2) {
            if (obj1 == null || obj2 == null){
                throw new IllegalArgumentException("Arg is null!");
            }            
            int score1 = Math.abs( ((Board.Square) obj1).getTotalScore(this.player) );
            int score2 = Math.abs( ((Board.Square) obj2).getTotalScore(this.player) );            
            if (score1-score2 < 0){
                return -1;
            } else if (score1-score2 > 0){
                return 1;
            } else {
                return 0;
            }
        }        
    } // end class SquareComparator
} // end class SortStrategy

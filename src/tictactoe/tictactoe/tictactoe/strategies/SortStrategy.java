package tictactoe.strategies;

import java.util.*;
import tictactoe.Board;
import tictactoe.players.Player;
import tictactoe.strategies.Sort.Comparator;

/**
 * @author hugh
 */
public class SortStrategy extends Strategy {        
    
    public SortStrategy(Board b){
        super(b);
    }
    
    public Board.Square getBestSquare() {        
        Object[] squares = this.getSortedSquares();  
        return (squares.length == 0)? null: (Board.Square) squares[squares.length-1];
    }
        
    protected Object[] getSortedSquares(){
        Vector vacancies = theBoard.getCache();
        Board.Square[] squares = new Board.Square[vacancies.size()];
        for (int i=0; i<vacancies.size(); i++)
            squares[i] = (Board.Square) vacancies.elementAt(i);
        return Sort.sort(squares, new SquareComparator(this.theBoard));
    }

    class SquareComparator implements Comparator {
        private Board theBoard;
        
        public SquareComparator(Board b){
            theBoard = b;
        }
        
        public int compare(Object obj1, Object obj2) {
            if (obj1 == null || obj2 == null)
                throw new IllegalArgumentException("Arg is null!");
            
//            int score1 = theBoard.getHumanPlayer().updateRowSum((Board.Square) obj1, 0) + 
//                        theBoard.getHumanPlayer().updateColumnSum((Board.Square) obj1, 0) +
//                        theBoard.getHumanPlayer().updateFwdDiagSum((Board.Square) obj1, 0) +
//                        theBoard.getHumanPlayer().updateBackDiagSum((Board.Square) obj1, 0) ;
//            int score2 = theBoard.getHumanPlayer().updateRowSum((Board.Square) obj2, 0) + 
//                        theBoard.getHumanPlayer().updateColumnSum((Board.Square) obj2, 0) +
//                        theBoard.getHumanPlayer().updateFwdDiagSum((Board.Square) obj2, 0) +
//                        theBoard.getHumanPlayer().updateBackDiagSum((Board.Square) obj2, 0) ;
            int score1 = ( (Board.Square) obj1).getTotalScore(theBoard.getHumanPlayer());
            int score2 = ( (Board.Square) obj2).getTotalScore(theBoard.getHumanPlayer());
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

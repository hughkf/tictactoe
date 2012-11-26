package tictactoe.strategies;

import java.util.*;
import tictactoe.*;
import tictactoe.players.*;
import tictactoe.utils.Sort;
import tictactoe.utils.Sort.Comparator;

/**
 * @author hugh
 */
public class CompositeStrategy extends Strategy {        
    
    public CompositeStrategy(Board b){
        super(b);
    }
    
    public Board.Square getBestSquare() {        
        //choose a winning square if there is one
        if (!getWinningSquares(theBoard.getMachinePlayer()).isEmpty() ){
            return (Board.Square) getWinningSquares(theBoard.getMachinePlayer()).firstElement();
        }
        //else, block human if he/she can win on the next move
        if (!getWinningSquares(theBoard.getHumanPlayer()).isEmpty() ){
            return (Board.Square) getWinningSquares(theBoard.getHumanPlayer()).firstElement();
        }
        //else, choose the square with the highest machine score
        return (Board.Square) getMaxSquares(theBoard.getMachinePlayer()).firstElement();
    }
     
    protected Vector getWinningSquares(HumanPlayer p){
        Vector winningSquares = new Vector();
        Vector maxSquares = this.getMaxSquares(p);
        Board.Square sq;
        boolean winningSq;
        for (Enumeration e = maxSquares.elements(); e.hasMoreElements(); ){
            sq = (Board.Square) e.nextElement();
            if (sq != null && ( 
                Math.abs( p.updateRowSum(sq, 0) ) +1 >= Board.GRID_SIZE() ||
                Math.abs( p.updateColumnSum(sq, 0) ) +1 >= Board.GRID_SIZE() ||
                Math.abs( p.updateFwdDiagSum(sq, 0) ) +1 >= Board.GRID_SIZE() ||
                Math.abs( p.updateBackDiagSum(sq, 0) ) +1 >= Board.GRID_SIZE() )) 
            {
                winningSquares.addElement(sq);
            }
        }
        return winningSquares;
    }

    protected Vector getMaxSquares(HumanPlayer p) {
        Object[] squares = this.getSortedSquares(p); 
        Vector maxSquares = new Vector();
        //since squares is sorted in ascending order, the last element is largest, max
        Board.Square max = (Board.Square) squares[squares.length-1], sq;
        maxSquares.addElement(max);
        for (int i=squares.length-2; i >= 0; i--){
            sq = (Board.Square) squares[i];
            if (sq != null && sq != max && Math.abs( sq.getTotalScore(p)) == 
            Math.abs( max.getTotalScore(p))) {
                max = sq;             
                maxSquares.addElement(max);
            }
        }
        return maxSquares;    
    }

    protected Object[] getSortedSquares(HumanPlayer p){
        Vector vacancies = theBoard.getCache();
        Board.Square[] squares = new Board.Square[vacancies.size()];
        for (int i=0; i<vacancies.size(); i++)
            squares[i] = (Board.Square) vacancies.elementAt(i);
        return Sort.sort(squares, new SquareComparator(this.theBoard, p));
    }

    class SquareComparator implements Comparator {
        private Board theBoard;
        private HumanPlayer player;
        
        public SquareComparator(Board b, HumanPlayer p){
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

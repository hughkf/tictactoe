package tictactoe.strategies;

import java.util.*;
import tictactoe.*;
import tictactoe.Board.Square;
import tictactoe.players.*;
import tictactoe.utils.*;
import tictactoe.utils.Sort.Comparator;

/**
 * @author hugh
 */
public class CompositeStrategy extends Strategy {        
    
    public CompositeStrategy(Board b){
        super(b);
    }
    
    public Square getBestSquare() {        
        //choose a winning square if there is one
        if (!getWinningSquares(theBoard.getMachinePlayer()).isEmpty() ){
            return (Square) getWinningSquares(theBoard.getMachinePlayer()).firstElement();
        }
        //else, block human if he/she can win on the next move
        if (!getWinningSquares(theBoard.getHumanPlayer()).isEmpty() ){
            return (Square) getWinningSquares(theBoard.getHumanPlayer()).firstElement();
        }
        //else, choose the square with the highest machine score
        return (Square) getMaxSquares(theBoard.getMachinePlayer()).firstElement();
    }
     
    protected Vector getWinningSquares(Player p){
        Square sq;
        Vector winningSquares = new Vector();
        for (Enumeration e = this.getMaxSquares(p).elements(); e.hasMoreElements(); ){
            sq = (Square) e.nextElement();
            if (sq != null && ( 
            Math.abs( p.rowScore(sq, 0) ) +1 >= Board.GRID_SIZE() ||
            Math.abs( p.columnScore(sq, 0) ) +1  >= Board.GRID_SIZE() ||
            Math.abs( p.fwdDiagScore(sq, 0) ) +1 >= Board.GRID_SIZE() ||
            Math.abs( p.backDiagScore(sq, 0) ) +1 >= Board.GRID_SIZE() )) 
            {
                winningSquares.addElement(sq);
            }
        }
        return winningSquares;
    }

    protected Vector getMaxSquares(Player p) {
        Object[] squares = this.getSortedSquares(p); 
        Vector maxSquares = new Vector();
        //squares is sorted in ascending order, the last element is max
        Square max = (Square) squares[squares.length-1], sq;
        maxSquares.addElement(max);
        //gather any others with the same total score
        for (int i=squares.length-2; i >= 0; i--){
            sq = (Square) squares[i];
            if (sq != null && sq != max && 
            Math.abs( sq.totalScore(p)) == Math.abs( max.totalScore(p))) {
                max = sq;             
                maxSquares.addElement(max);
            }
        }
        return maxSquares;    
    }

    protected Object[] getSortedSquares(Player p){
        Vector vacancies = theBoard.getCache();
        Square[] squares = new Square[vacancies.size()];
        for (int i=0; i<vacancies.size(); i++)
            squares[i] = (Square) vacancies.elementAt(i);
        return Sort.sort(squares, new SquareComparator(p));
    }

    class SquareComparator implements Comparator {
        private Player player;
        
        public SquareComparator(Player p){
            player = p;
        }
        
        public int compare(Object obj1, Object obj2) {
            if (obj1 == null || obj2 == null){
                throw new IllegalArgumentException("Arg is null!");
            }            
            int score1 = Math.abs( ((Square) obj1).totalScore(player) );
            int score2 = Math.abs( ((Square) obj2).totalScore(player) );            
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

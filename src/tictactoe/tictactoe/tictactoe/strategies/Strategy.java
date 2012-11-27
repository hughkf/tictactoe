package tictactoe.strategies;

import tictactoe.Board;
import tictactoe.Board.Square;

/**
 * @author hugh
 */
public abstract class Strategy { 
    protected Board theBoard;
        
    public Strategy(Board b){
        theBoard = b;
    }

    public abstract Square getBestSquare();    
} //end Strategy 

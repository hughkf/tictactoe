package tictactoe.strategies;

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
} //end Strategy 

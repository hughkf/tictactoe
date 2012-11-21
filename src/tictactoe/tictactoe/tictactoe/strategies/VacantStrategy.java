package tictactoe.strategies;

import tictactoe.Board;

/**
 * @author hugh
 */
public class VacantStrategy extends Strategy {
    
    public VacantStrategy(Board b){
        super(b);
    }
    
    public Board.Square getBestSquare() {
        return findVacantSquare();
    }
} 

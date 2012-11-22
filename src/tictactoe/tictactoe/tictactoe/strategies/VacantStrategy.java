package tictactoe.strategies;

import tictactoe.Board;
import tictactoe.players.Player;

/**
 * @author hugh
 */
public class VacantStrategy extends Strategy {
    
    /**
     * @param b
     * @param p
     */
    public VacantStrategy(Board b){
        super(b);
    }
    
    public Board.Square getBestSquare() {
        return findVacantSquare();
    }
} 

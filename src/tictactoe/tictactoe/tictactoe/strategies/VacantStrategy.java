package tictactoe.strategies;

import tictactoe.Board;
import tictactoe.players.MachinePlayer;

/**
 * @author hugh
 */
public class VacantStrategy implements MachinePlayer.Strategy {
    private Board theBoard;
    
    public VacantStrategy(Board b){
        theBoard = b;
    }
    
    public Board.Square getBestSquare() {
        return this.theBoard.findVacantSquare();
    }
} 

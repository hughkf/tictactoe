package tictactoe.players;

import tictactoe.Board;
import tictactoe.Debug;
import tictactoe.strategies.*;

/**
 * @author hugh
 */
public class MachinePlayer extends Player {    
    private Strategy strategy;

    public MachinePlayer(Board b, String n, String s, int v, int difficulty){
        super(b, n, s, v);
        switch(difficulty){
            case 1:
                strategy = new RandomStrategy(b);
                break;
            case 2:
                strategy = new SortStrategy(b);
                break;                
            default: case 0:
                strategy = new VacantStrategy(b);        
                break;
        }
        Debug.printDebug("Strategy: " + strategy.getClass().getName());
    }

    public Board.Square findSquare(){
        return strategy.getBestSquare();
    }
} //end class MachinePlayer

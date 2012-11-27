package tictactoe.players;

import tictactoe.strategies.Strategy;
import tictactoe.*;
import tictactoe.Board.Square;
import tictactoe.strategies.*;

/**
 * @author hugh
 */
public class MachinePlayer extends Player {    
    private Strategy strategy;

    public MachinePlayer(Board b, String n, String s, int v, int difficulty){
        super(b, n, s, v);
        switch(difficulty){
            default: case 0:
                //select a vacant square at random
                strategy = new RandomStrategy(b);
                break;
            case 1:
                //this one uses blocking and tries to optimize the machine's score
                strategy = new CompositeStrategy(b);
                break;                
        }
    }

    public Square findSquare(){
        return strategy.getBestSquare();        
    }
} //end class MachinePlayer

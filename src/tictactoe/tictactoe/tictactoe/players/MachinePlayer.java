/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.players;

import tictactoe.Board;
import tictactoe.strategies.*;

/**
 * @author hugh
 */
public class MachinePlayer extends Player {    
    private Strategy strategy;

    public MachinePlayer(Board b, String n, String s, int v){
        super(b, n, s, v);
        strategy = new VacantStrategy(b);        
        //strategy = new SortStrategy(b);
    }

    public Board.Square findSquare(){
        return strategy.getBestSquare();
    }
    
    public interface Strategy {        
        public Board.Square getBestSquare();    
    }
} //end class MachinePlayer

package tictactoe.players;

import tictactoe.Board;

/**
 * @author hugh
 */
    
public class Player {
    String name;
    String label;
    int value;
    Board theBoard;
    
    public Player(Board b, String n, String s, int v) {
        theBoard = b;
        name = n;
        label = s;
        value = v;
    }

    public Board.Square findSquare() {
        Board.Square sq = theBoard.updatePosition(0, 0);
        return sq.occupied()? null : sq;
    }

    public boolean move() {
        Board.Square sq = this.findSquare();
        if (sq == null)
            return false;                 
        Player p = sq.occupy(this);
        return true;
    }
    
    public int getValue() {
        return this.value;
    }

    public String getName(){
        return this.name;
    }
    
    public String getLabel(){
        return this.label;
    }
    
} //end class Player

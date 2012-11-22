package tictactoe.players;

import tictactoe.Board;
import tictactoe.Debug;

/**
 * @author hugh
 */
    
public class Player {
    String name;
    String label;
    int value;
    Board theBoard;
    Integer[][] scoreBoard;
    private static final int xRowSum = 0; 
    private static final int xColSum = 1; 
    private static final int xDiagSum = 2; 
    private static final int yFwdDiagSum = 0; 
    private static final int yBackDiagSum = 1; 
    
    public Player(Board b, String n, String s, int v) {
        theBoard = b;
        name = n;
        label = s;
        value = v;
        initScoreBoard();
    }

    public boolean move() {
        Debug.printDebug("now, " + this.name + " player " + " moves ...");
        Board.Square sq = this.findSquare();
        if (sq == null)
            return false;                 
        Player p = sq.occupy(this);
        return true;
    }
    
    public Board.Square findSquare() {
        Board.Square sq = theBoard.updatePosition(0, 0);
        return sq.occupied()? null : sq;
    }
    
    public int updateRowSum(Board.Square current, int n){
        int currentValue = scoreBoard[xRowSum][current.getX()].intValue();
        scoreBoard[xRowSum][current.getX()] = new Integer(n + currentValue);
        return scoreBoard[xRowSum][current.getX()].intValue();
    }
    
    public int updateColumnSum(Board.Square current, int n){
        int currentValue = scoreBoard[xColSum][current.getY()].intValue();
        scoreBoard[xColSum][current.getY()] = new Integer(n + currentValue);
        return scoreBoard[xColSum][current.getY()].intValue();
    }
        
    public int updateFwdDiagSum(Board.Square current, int n){
        int currentValue = 0;
        if (current.getX() == current.getY()){
            currentValue = scoreBoard[xDiagSum][yFwdDiagSum].intValue();
            scoreBoard[xDiagSum][yFwdDiagSum] = new Integer(n + currentValue);            
        }
        return scoreBoard[xDiagSum][yFwdDiagSum].intValue();
    }

    public int updateBackDiagSum(Board.Square current, int n){
        int currentValue = 0;
        if (current.getX() + current.getY() + 1 == Board.GRID_SIZE()){            
            currentValue = scoreBoard[xDiagSum][yBackDiagSum].intValue();
            scoreBoard[xDiagSum][yBackDiagSum] = new Integer(n + currentValue);
        }
        return scoreBoard[xDiagSum][yBackDiagSum].intValue();
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
    
    public String toString() {
        return "name: " + this.name + ", label: " + this.label + 
                ", value: " + this.value;
    }

    private void initScoreBoard() {
        scoreBoard = new Integer[Board.GRID_SIZE()][Board.GRID_SIZE()];
        for (int r = 0; r < Board.GRID_SIZE(); r++)
            for(int c = 0; c < Board.GRID_SIZE(); c++)
                scoreBoard[r][c] = new Integer(0);                        
    }
} //end class Player

package tictactoe.players;

import tictactoe.Board;
import tictactoe.Board.Square;

/**
 * @author hugh
 */
    
public class Player {
    String name;
    String label;
    int value;
    Board theBoard;
    Integer[][] scoreBoard;
    private static final int rowSumIndex = 0; 
    private static final int colSumIndex = 1; 
    private static final int diagSumIndex = 2; 
    private static final int fwdDiagSumIndex = 0; 
    private static final int backDiagSumIndex = 1; 
    
    public Player(Board b, String n, String s, int v) {
        theBoard = b;
        name = n;
        label = s;
        value = v;
        scoreBoard = new Integer[Board.GRID_SIZE()][Board.GRID_SIZE()];
        for (int r = 0; r < Board.GRID_SIZE(); r++){
            for(int c = 0; c < Board.GRID_SIZE(); c++)
                scoreBoard[r][c] = new Integer(0);  
        }
    }

    public boolean move() {
        Square sq = this.findSquare();
        if (sq == null)
            return false;                 
        Player p = sq.occupy(this);
        return true;
    }
    
    public Square findSquare() {
        Square sq = theBoard.updatePosition(0, 0);
        return sq.occupied()? null : sq;
    }
    
    public int rowScore(Square current, int n){
        int currentValue = scoreBoard[rowSumIndex][current.getY()].intValue();
        scoreBoard[rowSumIndex][current.getY()] = new Integer(n + currentValue);
        return scoreBoard[rowSumIndex][current.getY()].intValue();
    }
    
    public int columnScore(Square current, int n){
        int currentValue = scoreBoard[colSumIndex][current.getX()].intValue();
        scoreBoard[colSumIndex][current.getX()] = new Integer(n + currentValue);
        return scoreBoard[colSumIndex][current.getX()].intValue();
    }
        
    public int fwdDiagScore(Square current, int n){
        int currentValue = 0;
        if (current.getX() == current.getY()){
            currentValue = scoreBoard[diagSumIndex][fwdDiagSumIndex].intValue();
            scoreBoard[diagSumIndex][fwdDiagSumIndex] = new Integer(n + currentValue);            
            return scoreBoard[diagSumIndex][fwdDiagSumIndex].intValue();
        }
        return 0;
    }

    public int backDiagScore(Square current, int n){
        int currentValue = 0;
        if (current.getX() + current.getY() + 1 == Board.GRID_SIZE()){            
            currentValue = scoreBoard[diagSumIndex][backDiagSumIndex].intValue();
            scoreBoard[diagSumIndex][backDiagSumIndex] = new Integer(n + currentValue);
            return scoreBoard[diagSumIndex][backDiagSumIndex].intValue();
        }
        return 0;
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
} //end class Player

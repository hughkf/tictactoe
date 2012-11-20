package tictactoe;

/* Copyright (c) 2012, Hugh Krogh-Freeman
 *
 * This source code is distributed under the terms of the 
 * GNU General Public License.
 */
import java.util.*;
import javax.microedition.lcdui.*;
import tictactoe.players.*;

public class Board {
    private static final boolean DEBUG = false;
    public static final int PLAYING = 1; 
    public static final int WON = 5; 
    private Font font;
    private Square[][] grid;
    public static int GRID_SIZE;
    private int posx;
    private int posy;
    private Square head;
    private int gameState;
    private Vector vacancyCache;
    private Player human, machine, winner, nobody;
    private Integer[][] scoreBoard;
    private static final int xRowSum = 0; 
    private static final int xColSum = 1; 
    private static final int xDiagSum = 2; 
    private static final int yFwdDiagSum = 0; 
    private static final int yBackDiagSum = 1;  
    
    public Board() {
        font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM); 
        human = new Player(this, "You", "X", 1);
        nobody = new Player(this, "No one", "?", 0);
        machine = new MachinePlayer(this, "Hal 9000", "O", -1);
        vacancyCache = new Vector();
    }
    
    public void display(Graphics g, int screenWidth, int screenHeight) {
        g.translate((screenWidth - (GRID_SIZE * head.width) +1)/2, 10);
        g.setColor(0);
        g.drawRect(-2, -2, 
            (GRID_SIZE * head.width) +2, (GRID_SIZE * head.height) +2);
        for (int j = 0; j < GRID_SIZE; j++) {
            for (int k = 0; k < GRID_SIZE; k++) {
                grid[j][k].paint(g);
            }
        }
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.setColor(0);
        g.setFont(Font.getDefaultFont());
        g.drawString(getStatus(), screenWidth/2, screenHeight -1,
            Graphics.BOTTOM | Graphics.HCENTER);
    }
    
    public void createBoard(int size) {
        gameState = PLAYING;
        winner = null;      
        vacancyCache.removeAllElements();
        int i;
        Board.GRID_SIZE = size; 
        scoreBoard = new Integer[size][size];
        for (int r = 0; r < GRID_SIZE; r++)
            for(int c = 0; c < GRID_SIZE; c++)
                scoreBoard[r][c] = new Integer(0);        
        grid = new Square[GRID_SIZE][GRID_SIZE];
        for (i = 0; i < (GRID_SIZE * GRID_SIZE); i++) {
            int x = i % GRID_SIZE;
            int y = i / GRID_SIZE;
            grid[x][y] = new Square(x, y);
            vacancyCache.addElement( grid[x][y] );
        }
        posx = GRID_SIZE-1;
        posy = GRID_SIZE-1;
        grid[posx][posy].focus = true;
        head = grid[0][0];
    }

    public Square updatePosition(int x, int y) {
        grid[posx][posy].focus = false;
        posx += x;
        posy += y;
        if(posx < 0)
            posx = 0;
        if(posy < 0)
            posy = 0;
        if(posx >= Board.GRID_SIZE)
            posx = Board.GRID_SIZE-1;
        if(posy >= Board.GRID_SIZE)
            posy = Board.GRID_SIZE-1;
        grid[posx][posy].focus = true;
        return grid[posx][posy];
    }
    
    public Square findVacantSquare() {
        Square sq;
        try {
            sq = (Square)vacancyCache.lastElement(); 
        } catch (NoSuchElementException e) {
            return null;
        }
        return sq.occupied() ? null: sq;
    }

    public int updateRowSum(Square current, int n){
        int currentValue = scoreBoard[xRowSum][current.x].intValue();
        scoreBoard[xRowSum][current.x] = new Integer(n + currentValue);
        return n + currentValue;
    }
    
    public int updateColumnSum(Square current, int n){
        int currentValue = scoreBoard[xColSum][current.y].intValue();
        scoreBoard[xColSum][current.y] = new Integer(n + currentValue);
        return n + currentValue;
    }
        
    public int updateFwdDiagSum(Square current, int n){
        int currentValue = 0;
        if (current.x == current.y){
            currentValue = scoreBoard[xDiagSum][yFwdDiagSum].intValue();
            scoreBoard[xDiagSum][yFwdDiagSum] = new Integer(n + currentValue);
        }
        return n + currentValue;
    }

    public int updateBackDiagSum(Square current, int n){
        int currentValue = 0;
        if (current.x + current.y + 1 == GRID_SIZE){
            currentValue = scoreBoard[xDiagSum][yBackDiagSum].intValue();
            scoreBoard[xDiagSum][yBackDiagSum] = new Integer(n + currentValue);
        }
        return n + currentValue;
    } 
    
    public String getStatus(){
        return (getWinner() == null)? "": getWinner().getName() +" won!";
    }
        
    public Vector getCache(){
        return this.vacancyCache;
    }
    
    public Player getWinner(){
        return this.winner;
    }
    
    public int getState() {
        return gameState;
    }
    
    public Board.Square[][] getGrid() {
        return this.grid;
    }

    public Player getHumanPlayer() {
        return this.human;
    }
    
    public Player getMachinePlayer(){
        return this.machine;
    }
    
    public static void printDebug(String msg){
        if (Board.DEBUG){
            System.out.println(msg);
        }
    }
    
    public class Square {
        boolean focus;
        String label;
        int x; // grid coordinates
        int y; // grid coordinates
        int width;
        int height;
        int value; //make this a byte?
        
        public Square(int x, int y) {
            width = font.charWidth('M') + 7;
            height = font.getHeight() + 1;
            label = "";
            this.x =  x;
            this.y =  y;
            focus = false;
        }        
        
        public Player updateScore(Player p) {
            int rowSum = Board.this.updateRowSum(this, p.getValue());
            int colSum = Board.this.updateColumnSum(this, p.getValue());
            int fwdDiagSum = Board.this.updateFwdDiagSum(this, p.getValue());
            int backDiagSum = Board.this.updateBackDiagSum(this, p.getValue());
            if (Math.abs(rowSum) >= GRID_SIZE 
            || Math.abs(colSum) >= GRID_SIZE 
            || Math.abs(fwdDiagSum) >= GRID_SIZE 
            || Math.abs(backDiagSum) >= GRID_SIZE ) {                
                gameState = WON;
                Board.this.winner = p;
            }
            else if (vacancyCache.isEmpty()) {
                gameState = WON;
                Board.this.winner = nobody;
            } 
            return Board.this.getWinner();
        }
        
        public boolean occupied() {
            return (this.label.length() > 0);
        }
        
        public Player occupy(Player p) {
            if (p == null) 
                return null;
            this.value = p.getValue();
            this.label = p.getLabel();            
            vacancyCache.removeElement(this);
            Player player = updateScore(p);
            debugInfo();
            return player;
        }
        
        void paint(Graphics g) {
            int px = x * width;
            int py = y * height;
            if(focus) {
                g.setColor(0xFF0000);
                g.fillRect(px, py, width-2, height-2);
            }           
            g.setColor(0);
            g.setFont(font);
            g.drawRect(px, py, width -2, height -2);
            g.drawString(label, px +3, py, Graphics.TOP | Graphics.LEFT);
        }
        
        public int getX() {
            return this.x;
        }
        
        public int getY(){
            return this.y;
        }    
        
        public String toString() {
            return "\tx: " + x + ", y: " + y + 
                    ",\n\t value: " + value + ", label: " + label 
                    + ",\n\t row sum: " + Board.this.updateRowSum(this, 0)
                    + ", column sum: " + Board.this.updateColumnSum(this, 0)
                    + ",\n\t fwd diagonal sum: " + Board.this.updateFwdDiagSum(this, 0)
                    + ", back diagonal sum: " + Board.this.updateBackDiagSum(this, 0) ;
        }
        
        public void debugInfo(){
            Board.printDebug(this.toString());
        }
        
    } // end class Piece
    
} // end class Board
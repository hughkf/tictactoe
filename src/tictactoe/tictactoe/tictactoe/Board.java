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
    public static final int PLAYING = 1; 
    public static final int WON = 5; 
    private Font font;
    private Square[][] grid;
    private static int GRID_SIZE;
    private int posx;
    private int posy;
    private Square head;
    private int gameState;
    private Vector vacancyCache;
    private Player human, machine, winner, nobody;
    
    public Board() {
        font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM); 
        vacancyCache = new Vector();
    }
    
    public void display(Graphics g, int screenWidth, int screenHeight) {
        int boardWidth = GRID_SIZE * head.width;
        int boardHeight = GRID_SIZE * head.height;
        g.translate( (screenWidth -boardWidth -1)/2, 10);
        g.setColor(0);
        g.drawRect(-2, -2, boardWidth +2, boardHeight +2);
        for (int j = 0; j < GRID_SIZE; j++) 
            for (int k = 0; k < GRID_SIZE; k++)
                grid[j][k].paint(g);
        g.setFont(Font.getDefaultFont());
        g.drawString(getStatus(), boardWidth/2, boardHeight +10,
            Graphics.TOP | Graphics.HCENTER);
    }
    
    public void createBoard(int size, int difficulty) {
        Board.GRID_SIZE = size; 
        gameState = PLAYING;
        winner = null;      
        vacancyCache.removeAllElements();
        int i;
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
        human = new Player(this, "You", "O", 1);
        nobody = new Player(this, "No one", "?", 0);
        machine = new MachinePlayer(this, "Hal 9000", "X", -1, difficulty);
        machine.move(); //machine makes the first move
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

    public static int GRID_SIZE(){
        return GRID_SIZE;
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
        
        public int getTotalScore(Player p){
            return  p.updateRowSum(this, 0) +
                    p.updateColumnSum(this, 0) +
                    p.updateFwdDiagSum(this, 0) +
                    p.updateBackDiagSum(this, 0);            
        }
        
        public Player updateScore(Player p) {
            int rowSum = p.updateRowSum(this, p.getValue());
            int colSum = p.updateColumnSum(this, p.getValue());
            int fwdDiagSum = p.updateFwdDiagSum(this, p.getValue());
            int backDiagSum = p.updateBackDiagSum(this, p.getValue());
            boolean gameIsWon = Math.abs(rowSum) >= GRID_SIZE || 
                                Math.abs(colSum) >= GRID_SIZE || 
                                Math.abs(fwdDiagSum) >= GRID_SIZE || 
                                Math.abs(backDiagSum) >= GRID_SIZE;            
            if (gameIsWon) {                
                gameState = WON;
                Board.this.winner = p;
            }
            else if (vacancyCache.isEmpty()) {
                gameState = WON;
                Board.this.winner = nobody;
            } 
            return Board.this.winner;
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
            return "x: "+ x + ", y: "+ y + ", value: "+ value + ", label: "+ label;
        } //toString()

    } // end class Piece
    
} // end class Board
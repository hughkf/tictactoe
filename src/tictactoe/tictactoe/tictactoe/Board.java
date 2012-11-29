package tictactoe;

import java.util.*;
import javax.microedition.lcdui.*;
import tictactoe.players.*;

public class Board {
    private Player human, machine, winner, nobody;
    public static final int PLAYING = 1, WON = 5; 
    private static int GRID_SIZE;
    private int posx, posy, gameState;
    private Vector vacancyCache;
    private Square head;
    private Square[][] grid;
    private Font font;
    
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
        for (int j = 0; j < GRID_SIZE; j++){ 
            for (int k = 0; k < GRID_SIZE; k++)
                grid[j][k].paint(g);
        }
        g.setFont(Font.getDefaultFont());
        g.drawString(getStatus(), boardWidth/2, boardHeight +10,
            Graphics.TOP | Graphics.HCENTER);
    }
    
    public void createBoard(int size, int difficulty) {
        Board.GRID_SIZE = size; 
        vacancyCache.removeAllElements();
        grid = new Square[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < (GRID_SIZE * GRID_SIZE); i++) {
            int x = i % GRID_SIZE;
            int y = i / GRID_SIZE;
            grid[x][y] = new Square(x, y);
            vacancyCache.addElement( grid[x][y] );
        }
        posx = GRID_SIZE-1;
        posy = GRID_SIZE-1;
        grid[posx][posy].focus = true;
        head = grid[0][0];
        gameState = PLAYING;
        winner = null;      
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
    
    public Player getHumanPlayer() {
        return this.human;
    }
    
    public Player getMachinePlayer(){
        return this.machine;
    }
    
    public Square[][] getGrid() {
        return this.grid;
    }
    
    public class Square {
        boolean focus;
        String label;
        int x; // grid coordinates
        int y; // grid coordinates
        int width;
        int height;
        int value; 
        
        public Square(int x, int y) {
            width = font.charWidth('M') + 7;
            height = font.getHeight() + 1;
            label = "";
            this.x =  x;
            this.y =  y;
            focus = false;
        }               
        
        public int totalScore(Player p){
            return  p.rowScore(this, 0) +
                    p.columnScore(this, 0) +
                    p.fwdDiagScore(this, 0) +
                    p.backDiagScore(this, 0);            
        }
        
        public Player updateScore(Player p) {
            /* Scoring is (1): one update to row, col, & 
             * diag sums each time a square is occupied */
            if (Math.abs( p.rowScore(this, p.getValue())) >= GRID_SIZE || 
                Math.abs( p.columnScore(this, p.getValue())) >= GRID_SIZE || 
                Math.abs( p.fwdDiagScore(this, p.getValue())) >= GRID_SIZE || 
                Math.abs( p.backDiagScore(this, p.getValue())) >= GRID_SIZE ) 
            {                
                gameState = WON;
                Board.this.winner = p;
            } else if (vacancyCache.isEmpty()) {
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
            return updateScore(p);
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
        }
    } // end class Piece    
} // end class Board
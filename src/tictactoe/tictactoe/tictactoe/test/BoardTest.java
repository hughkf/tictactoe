/*
 * BoardTest.java
 * JMUnit based test
 *
 * Created on Nov 28, 2012, 11:00:45 PM
 */
package tictactoe.test;

import java.util.Vector;
import jmunit.framework.cldc10.*;
import tictactoe.Board;
import tictactoe.Board.Square;
import tictactoe.players.MachinePlayer;
import tictactoe.players.Player;

/**
 * @author hugh
 */
public class BoardTest extends TestCase {
    Board board;
    int[] sizes;
    int[] difficulties;
    
    public BoardTest() {
        //The first parameter of inherited constructor is the number of test cases
        super(10, "BoardTest");
        sizes = new int[] {3, 4, 5, 6};
        difficulties = new int[]{0, 1};
    }    
    
    public void test(int testNumber) throws Throwable {
        switch (testNumber) {    
            case 0:
                testGetWinner();
                break;
            case 1:
                testCreateBoard();
                break;
            case 2:
                testGetMachinePlayer();
                break;
            case 3:
                testGetHumanPlayer();
                break;
            case 4:
                testUpdatePosition();
                break;
            case 5:
                testGetState();
                break;
            case 6:
                testGetCache();
                break;
            case 7:
                testGetStatus();
                break;
            case 8:
                testGRID_SIZE();
                break;
            default:
                break;
        }
    }

    public void setUp() {
        board = new Board();
        board.createBoard(3, 0);
    }

    public void tearDown(){
        board = null;
    }
    
    /**
     * Test of testGetWinner method, of class Board.
     */
    public void testGetWinner() throws AssertionFailedException {
        System.out.println("getWinner");
        Board b = new Board();
        System.out.println("testing if board is null ...");
        assertNotNull(b);
        System.out.println("... passed");        
        b.createBoard(6, 1);
        System.out.println("calling getWinner() ...");
        Player p = b.getWinner();
        System.out.println("... passed");        
        System.out.println("verifying Player returned is null ...");
        assertSame(null, p );
        System.out.println("... passed");        
    }

    /**
     * Test of testCreateBoard method, of class Board.
     */
    public void testCreateBoard() throws AssertionFailedException {
        System.out.println("createBoard");
        Board b = new Board();
        b.createBoard(6, 1);
        for (int i=0; i<this.sizes.length; i++) {
            for (int j=0; j<this.difficulties.length; j++) {
                b.createBoard(sizes[i], difficulties[j]);                    
            }
        }
    }

    /**
     * Test of testGetMachinePlayer method, of class Board.
     */
    public void testGetMachinePlayer() throws AssertionFailedException {
        System.out.println("getMachinePlayer");
        Player player = board.getMachinePlayer();
        assertNotNull(player);
        MachinePlayer machine = (MachinePlayer) player;
        assertEquals("tictactoe.players.MachinePlayer", machine.getClass().getName());
    }

    /**
     * Test of testGetHumanPlayer method, of class Board.
     */
    public void testGetHumanPlayer() throws AssertionFailedException {
        System.out.println("getHumanPlayer");
        Player human = board.getHumanPlayer();
        assertNotNull(human);
        assertEquals("tictactoe.players.Player", human.getClass().getName() );
    }

    /**
     * Test of testUpdatePosition method, of class Board.
     */
    public void testUpdatePosition() throws AssertionFailedException {
        System.out.println("updatePosition");
        Square sq = board.updatePosition(0, 0);
        assertNotNull(sq);
        sq = board.updatePosition(-1, -1);        
        assertNotNull(sq);
        sq = board.updatePosition(Integer.MAX_VALUE, -1*Integer.MAX_VALUE);
        assertNotNull(sq);
    }

    /**
     * Test of testGetState method, of class Board.
     */
    public void testGetState() throws AssertionFailedException {
        System.out.println("getState");
        Board b = new Board();
        b.createBoard(6, 1);
        assertEquals( Board.PLAYING, b.getState() );
    }

    /**
     * Test of testGetCache method, of class Board.
     */
    public void testGetCache() throws AssertionFailedException {
        System.out.println("getCache");
        Vector cache = board.getCache();
        assertNotNull(cache);
        assertEquals(board.GRID_SIZE()*board.GRID_SIZE() -1, cache.size());
    }

    /**
     * Test of testGetStatus method, of class Board.
     */
    public void testGetStatus() throws AssertionFailedException {
        System.out.println("getStatus");
        String status = board.getStatus();
        assertNotNull(status);
        assertEquals(0, status.length());
    }

    /**
     * Test of testGRID_SIZE method, of class Board.
     */
    public void testGRID_SIZE() throws AssertionFailedException {
        System.out.println("GRID_SIZE");
        for (int i=0; i<this.sizes.length; i++) {
            for (int j=0; j<this.difficulties.length; j++) {
                board.createBoard(sizes[i], difficulties[j]);                    
                assertEquals(board.GRID_SIZE(), sizes[i]);
            }
        }
    }
}
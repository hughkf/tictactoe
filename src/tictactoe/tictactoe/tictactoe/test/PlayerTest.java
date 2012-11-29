/*
 * PlayerTest.java
 * JMUnit based test
 *
 * Created on Nov 28, 2012, 11:00:46 PM
 */
package tictactoe.test;

import jmunit.framework.cldc10.*;
import tictactoe.Board;
import tictactoe.Board.Square;
import tictactoe.players.Player;

/**
 * @author hugh
 */
public class PlayerTest extends TestCase {
    Player human;
    Board theBoard;
    String label;
    String playerName;
    int value;
    
    public PlayerTest() {
        //The first parameter of inherited constructor is the number of test cases
        super(10, "PlayerTest");
        theBoard = new Board();
        theBoard.createBoard(3, 0);
        playerName = "human";
        label = "O";
        value = 1;
        human = new Player(theBoard, playerName, label, value);
    }    
    
    public void test(int testNumber) throws Throwable {
        switch (testNumber) {    
            case 0:
                testRowScore();
                break;
            case 1:
                testGetName();
                break;
            case 2:
                testGetValue();
                break;
            case 3:
                testColumnScore();
                break;
            case 4:
                testBackDiagScore();
                break;
            case 5:
                testToString();
                break;
            case 6:
                testFwdDiagScore();
                break;
            case 7:
                testFindSquare();
                break;
            case 8:
                testMove();
                break;
            case 9:
                testGetLabel();
                break;
            default:
                break;
        }
    }

    /**
     * Test of testRowScore method, of class Player.
     */
    public void testRowScore() throws AssertionFailedException {
        System.out.println("rowScore");
        Square sq = theBoard.getGrid()[0][0];
        int score = human.rowScore(sq, 0);
    }

    /**
     * Test of testGetName method, of class Player.
     */
    public void testGetName() throws AssertionFailedException {
        System.out.println("getName");
        assertEquals( human.getName(), playerName);
    }

    /**
     * Test of testGetValue method, of class Player.
     */
    public void testGetValue() throws AssertionFailedException {
        System.out.println("getValue");
        assertEquals( human.getValue(), value);
    }

    /**
     * Test of testColumnScore method, of class Player.
     */
    public void testColumnScore() throws AssertionFailedException {
        System.out.println("columnScore");
        Square sq = theBoard.getGrid()[0][0];
        int result = human.columnScore(sq, 0);
    }

    /**
     * Test of testBackDiagScore method, of class Player.
     */
    public void testBackDiagScore() throws AssertionFailedException {
        System.out.println("backDiagScore");
        Square sq = theBoard.getGrid()[0][0];
        int result = human.backDiagScore(sq, 0);
    }

    /**
     * Test of testToString method, of class Player.
     */
    public void testToString() throws AssertionFailedException {
        System.out.println("toString");        
        String playerStr = human.toString();
        System.out.print(playerStr);
    }

    /**
     * Test of testFwdDiagScore method, of class Player.
     */
    public void testFwdDiagScore() throws AssertionFailedException {
        System.out.println("fwdDiagScore");        
        Square sq = theBoard.getGrid()[0][0];
        int result = human.fwdDiagScore(sq, 0);
    }

    /**
     * Test of testFindSquare method, of class Player.
     */
    public void testFindSquare() throws AssertionFailedException {
        System.out.println("findSquare");
        Square sq = human.findSquare();
        assertNotNull(sq);
        assertTrue(!sq.occupied());        
    }

    /**
     * Test of testMove method, of class Player.
     */
    public void testMove() throws AssertionFailedException {
        System.out.println("move");
        int beforeSize = theBoard.getCache().size();
        boolean result = human.move();
        assertEquals( theBoard.getCache().size(), beforeSize-1);
    }

    /**
     * Test of testGetLabel method, of class Player.
     */
    public void testGetLabel() throws AssertionFailedException {
        System.out.println("getLabel");
        assertEquals( human.getLabel(), label);
    }
}
/*
 * MachinePlayerTest.java
 * JMUnit based test
 *
 * Created on Nov 28, 2012, 11:00:46 PM
 */
package tictactoe.test;

import jmunit.framework.cldc10.*;
import tictactoe.Board;
import tictactoe.Board.Square;
import tictactoe.players.MachinePlayer;

/**
 * @author hugh
 */
public class MachinePlayerTest extends TestCase {
    MachinePlayer machine;
    
    public MachinePlayerTest() {
        //The first parameter of inherited constructor is the number of test cases
        super(1, "MachinePlayerTest");
        Board b = new Board();
        b.createBoard(3, 0);
        machine = new MachinePlayer(b, "machine", "X", -1, 0);
    }    
    
    public void test(int testNumber) throws Throwable {
        switch (testNumber) {    
            case 0:
                testFindSquare();
                break;
            default:
                break;
        }
    }

    /**
     * Test of testFindSquare method, of class MachinePlayer.
     */
    public void testFindSquare() throws AssertionFailedException {
        System.out.println("findSquare");
        Square sq = machine.findSquare();
        assertNotNull(sq);
        assertTrue(!sq.occupied());
    }
}
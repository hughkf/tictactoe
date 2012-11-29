/*
 * RandomStrategyTest.java
 * JMUnit based test
 *
 * Created on Nov 28, 2012, 11:00:45 PM
 */
package tictactoe.test;

import jmunit.framework.cldc10.*;
import tictactoe.Board;
import tictactoe.Board.Square;
import tictactoe.strategies.RandomStrategy;
import tictactoe.strategies.Strategy;

/**
 * @author hugh
 */
public class RandomStrategyTest extends TestCase {
    Strategy randomStrategy;
    Board theBoard;
    
    public RandomStrategyTest() {
        //The first parameter of inherited constructor is the number of test cases
        super(1, "RandomStrategyTest");
        theBoard = new Board();
        theBoard.createBoard(3, 0);
        randomStrategy = new RandomStrategy(theBoard);
    }    
    
    public void test(int testNumber) throws Throwable {
        switch (testNumber) {    
            case 0:
                testGetBestSquare();
                break;
            default:
                break;
        }
    }

    /**
     * Test of testGetBestSquare method, of class RandomStrategy.
     */
    public void testGetBestSquare() throws AssertionFailedException {
        System.out.println("getBestSquare");
        Square sq = randomStrategy.getBestSquare();
        assertNotNull(sq);
        assertTrue(!sq.occupied());
    }
}
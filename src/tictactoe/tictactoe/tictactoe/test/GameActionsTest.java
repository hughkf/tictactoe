/*
 * GameActionsTest.java
 * JMUnit based test
 *
 * Created on Nov 28, 2012, 11:00:44 PM
 */
package tictactoe.test;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import jmunit.framework.cldc10.*;
import tictactoe.Board;
import tictactoe.GameActions;

/**
 * @author hugh
 */
public class GameActionsTest extends TestCase {
    GameActions gameAction;
    int[] actions;
    
    public GameActionsTest() {
        //The first parameter of inherited constructor is the number of test cases
        super(4, "GameActionsTest");
        actions = new int[] {Canvas.FIRE, Canvas.DOWN, Canvas.UP, 
            Canvas.LEFT, Canvas.RIGHT};
        gameAction = new GameActions(null, Display.getDisplay(null));        
    }    
    
    public void test(int testNumber) throws Throwable {
        switch (testNumber) {    
            case 0:
                testGameCycle();
                break;
            case 1:
                testProcessGameAction();
                break;
            case 2:
                testCommandAction();
                break;
            case 3:
                testTheBoard();
                break;
            default:
                break;
        }
    }

    /**
     * Test of testGameCycle method, of class GameActions.
     */
    public void testGameCycle() throws AssertionFailedException {
        System.out.println("gameCycle");
        for (int i=0; i< this.actions.length; i++){
            gameAction.gameCycle( actions[i] );
        }
    }

    /**
     * Test of testProcessGameAction method, of class GameActions.
     */
    public void testProcessGameAction() throws AssertionFailedException {
        System.out.println("processGameAction");
        int result;
        for (int i=0; i < actions.length; i++) {
            result = gameAction.processGameAction( actions[i] );                
        }
    }

    /**
     * Test of testCommandAction method, of class GameActions.
     */
    public void testCommandAction() throws AssertionFailedException {
        System.out.println("commandAction");
        Command c = null;
        Displayable d = null;
        gameAction.commandAction(c, d);
    }

    /**
     * Test of testTheBoard method, of class GameActions.
     */
    public void testTheBoard() throws AssertionFailedException {
        System.out.println("theBoard");
        Board result = gameAction.theBoard();
        assertNotNull(result);
    }
}
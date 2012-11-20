package tictactoe;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author Hugh
 */
public class GameActions implements CommandListener {
    private MIDlet midlet;
    private Command exit, cancel, ok; 
    private Options opt;
    private Screen theScreen;
    private Board theBoard;
    private Display display;
    
    public GameActions(MIDlet midlet, Display display) {
        this.midlet = midlet;
        this.display = display;
        ok = new Command("Start", Command.OK, 0);
        exit = new Command("Exit", Command.EXIT, 6);
        cancel = new Command("Exit", Command.CANCEL, 1);
        opt = new Options(this, ok, exit);
        theBoard = new Board();
        theScreen = new Screen(this);
        display.setCurrent(opt);
    }
    
    public void commandAction(Command c, Displayable d) {
        if (c == ok) {
            theBoard.createBoard(opt.getGridSize());            
            theScreen.addCommand(cancel);            
            display.setCurrent(theScreen);    
        } else if (c == cancel) {
            display.setCurrent(this.opt);
        } else if (c == exit) {
            this.midlet.notifyDestroyed();
        }   
    }
        
    public void displayBoard(Graphics g) {
        this.theBoard.display(g, theScreen.getWidth(), theScreen.getHeight());
    }
    
    public boolean gameCycle(int code){
        if (theBoard.getHumanPlayer().move() && 
                theBoard.getState() == theBoard.PLAYING)
        { //enter if human found valid sq
            theScreen.repaint();            
            theBoard.getMachinePlayer().move(); //machine always finds valid sq
        }
//        theScreen.repaint();        
        return (null != theBoard.getWinner());
    }

    public int processGameAction(int code) {
        if (theBoard.getState() != theBoard.PLAYING)
            return code;
        switch (code) {
            case Canvas.FIRE:
                this.gameCycle(code);
                break;  

            case Canvas.UP:
                theBoard.updatePosition(0, -1);
                break;

            case Canvas.DOWN:
                theBoard.updatePosition(0, 1);
                break;

            case Canvas.LEFT:
                theBoard.updatePosition(-1, 0);
                break;

            case Canvas.RIGHT:
                theBoard.updatePosition(1, 0);
                break;           
        }
        theScreen.repaint();
        return code;
    }
    
    public class Options extends Form {
        ChoiceGroup cg1;

        Options(GameActions c, Command ok, Command exit) {
            super("Options");
            append(new StringItem("", 
                "ConnectSquares.\n(c) 2008, Hugh Krogh-Freeman") );
            cg1 = new ChoiceGroup("Grid Size:", Choice.EXCLUSIVE);
            cg1.append("3 by 3 grid", null);
            cg1.append("4 by 4 grid", null);
            cg1.append("5 by 5 grid", null);
            cg1.append("6 by 6 grid", null);
            append(cg1);
            cg1.setSelectedIndex(0, true);
            addCommand( ok );
            addCommand( exit );
            setCommandListener(c);
        }

        public int getGridSize() {
            return cg1.getSelectedIndex() + 3;
        }            
    }
}
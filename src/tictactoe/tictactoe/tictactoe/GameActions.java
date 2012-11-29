package tictactoe;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

/**
 * @author Hugh
 */
public class GameActions implements CommandListener {
    private MIDlet midlet;
    private Command exit, settings, start, reset; 
    private Options opt;
    private Screen theScreen;
    private Board theBoard;
    private Display display;
    
    public GameActions(MIDlet midlet, Display display) {
        this.midlet = midlet;
        this.display = display;
        start = new Command("Play", Command.OK, 0);
        reset = new Command("Play again", Command.OK, 0);
        settings = new Command("Settings", Command.CANCEL, 1);
        exit = new Command("Exit", Command.EXIT, 6);
        opt = new Options(this, start, exit);
        theScreen = new Screen(this);
        this.display.setCurrent(opt);
    }
    
    public void commandAction(Command c, Displayable d) {
        if (c == start || c == reset) {
            theBoard = new Board();
            theBoard.createBoard(opt.getGridSize(), opt.getDifficulty());            
            theScreen.addCommand(settings);               
            theScreen.removeCommand(reset);
            display.setCurrent(theScreen);    
            theScreen.repaint();
        } else if (c == settings) {
            display.setCurrent(this.opt);
        } else if (c == exit) {
            this.midlet.notifyDestroyed();
        }   
    }
    
    public void gameCycle(int code){
        if (theBoard.getHumanPlayer().move() && theBoard.getState() == theBoard.PLAYING) 
        {   //we're here because human found valid square
            theScreen.repaint();            
            theBoard.getMachinePlayer().move(); 
            //machine always finds valid square
        }
        if (theBoard.getWinner() != null){            
            theScreen.addCommand(reset);
        }
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
    
    public Board theBoard() {
        return this.theBoard;
    }
    
    public class Options extends Form {
        ChoiceGroup cg1, cg2;

        Options(GameActions c, Command ok, Command exit) {
            super("Options");
            append(new StringItem("", 
                "\nConnectSquares.\n(c) 2008, 2012, Hugh Krogh-Freeman") );
            cg1 = new ChoiceGroup("\nGrid Size:", Choice.EXCLUSIVE);
            cg1.append("3 by 3 grid", null);
            cg1.append("4 by 4 grid", null);
            cg1.append("5 by 5 grid", null);
            cg1.append("6 by 6 grid", null);
            cg2 = new ChoiceGroup("\nDifficulty:", Choice.EXCLUSIVE);
            cg2.append("trivial", null);
            cg2.append("moderate", null);
            append(cg1);
            cg1.setSelectedIndex(0, true);
            append(cg2);
            cg2.setSelectedIndex(0, true);

            addCommand( ok );
            addCommand( exit );
            setCommandListener(c);
        }

        public int getGridSize() {
            return cg1.getSelectedIndex() + 3;
        }            
        
        public int getDifficulty() {
            return cg2.getSelectedIndex();
        }
    }
}
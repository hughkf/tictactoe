package tictactoe;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Hugh
 */
public class Screen extends Canvas {
    private GameActions control;
    private Font font;

    public Screen(GameActions c) {
        control = c;
        setCommandListener(c);
        font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
    }
    
    public void keyPressed(int code) {
        int action = getGameAction(code);
        control.processGameAction(action);
        repaint();
    }
        
    protected void paint(Graphics g) {
        g.setColor(0xFFFFFF);
        g.fillRect(0, 0, getWidth(), getHeight());
        control.displayBoard(g);
    }
}
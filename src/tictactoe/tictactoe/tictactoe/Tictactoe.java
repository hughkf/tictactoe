package tictactoe;
/*
 * Copyright (c) 2012, Hugh Krogh-Freeman
 * This source code is distributed under the terms of the GNU General Public License.
 */

/*
 * Changelog:
 * - mvn/ant build
 * - Change to support Android: Move the status string 
 *      up just below the board & paint it just like the text for 
 *      the options screen.
 * - Write JUnit tests
 * - Make Piece & Board composites from the same base class
 * - Provide machine learning strategy
 * - (done) Add "exit" command to Board Screen
 * - (done) Refactor & Simplify Screen & Board classes
 * - (done) Simplify current GameEngine strategy
 */

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class Tictactoe extends MIDlet {

    public void startApp() {
        GameActions control = new GameActions(this, Display.getDisplay(this));
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unc) {
    }
}

package tictactoe;
/*
 * Copyright (c) 2012, Hugh Krogh-Freeman
 * This source code is distributed under the terms of the GNU General Public 
 * License.

 * Changelog:
 * - TODO: j2meunit tests
 * - TODO: Machine learning strategy
 * 
 * - (done) Rearranged buttons so you can "play again" rather than needing to
 *      exit to the settings screen first
 * - (done) Add Player parameter to score-keeping methods, i.e. 
 *      updateRowSum(), etc
 * - (done) added a choice group for selecting the strategy
 * - (done) Make Strategy abstract
 * - (done) mvn/ant build
 * - (done) Change to support Android: Move the status string 
 *      up just below the board & paint it just like the text for 
 *      the options screen.
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

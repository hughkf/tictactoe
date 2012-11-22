Tic-Tac-Joe
===========
A J2ME Midlet implementation of tic-tac-toe


System requirements
===================
* Java SE SDK v.6
* Java ME SDK
* ant 
* a web server, e.g. Apache

Build instructions
==================
* With Netbeans:
	1a. The easiest way is to install Netbeans. This way, you do not need ant. Use the root directory to create a J2ME project in Netbean. Then, just build the project.


* Without Netbeans:
	1b. In the top level directory, run the command: "ant".
	2. If you need to re-compile, run: "ant clean".
	3. This will create two new directories: build and dist.

Execution instructions
======================
* With Netbeans: 
	1a. Run the project you created and built under "Build instructions". 


* Without Netbeans:
	1b. Set up the web server to serve dist/Tictactoe.jad and dist/Tictactoe.jar. 
	2. Locate the J2ME emulator. Suppose the path is <Wireless tool kit>/bin/emulator
	3. First, install the MIDlet (you only need to do this once): 
	<wireless tool kit>/bin/emulator -Xjam:install=<url to Tictactoe.jad> 
	4. Then, run the MIDlet:
	<wireless tool kit>/bin/emulator -Xjam:run=#Vendor_#Tictactoe_
	5. If you see the first screen of the game, which includes a copyright statement and four radio buttons to choose either "3 by 3 grid", "4 by 4 grid", "5 by 5 grid", or "6 by 6 grid", then it's working. 
	6. Play the game!

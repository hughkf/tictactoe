package tictactoe;

/**
 * @author hugh
 */
public class Debug {
    public static final boolean DEBUG = false;    

    public static void printArray(Object[] array){
        for (int i=0; i < array.length; i++) {
            System.out.println("i: " + i + ", " + array[i] );
        }
    }

    public static void printDebug(String msg) {
        if (DEBUG) {
            System.out.println(msg);
        }
    }
}

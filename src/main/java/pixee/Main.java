package pixee;

import java.util.logging.Logger;

public class Main {

    private static int myIntegerValue;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("Hello world!");
        System.out.println("Hello world!");


        myIntegerValue = Integer.valueOf("3").intValue();
    }

    public void log(){
        logger.info(String.format("Processing AutoDelegate annotations %d", myIntegerValue));

    }

    private static final Logger logger = Logger.getLogger(Main.class.getName());
}

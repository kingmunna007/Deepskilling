public class Logger {
    //private Logger instance
    private static Logger logger = new Logger();

    private Logger(){
        //private default constructor
    }

    public static Logger getInstance() {
        //singelton pattern get instance
        System.out.println("the logger instance was accessed");
        return logger;
    }
}

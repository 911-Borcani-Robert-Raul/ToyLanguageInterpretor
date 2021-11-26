package model;

public class MyException extends Exception{
    public MyException(String message) {
        super(message);
    }
    public MyException(String message, Throwable error) {
        super(message, error);
    }
}

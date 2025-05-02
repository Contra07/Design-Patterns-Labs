package ru.ssau.transport.exception;

public class NoSuchModelNameException extends Exception
{
    public NoSuchModelNameException() {
        super();
    }
    public NoSuchModelNameException(String name)
    {
        super("Model with name " + name +" does not exists");
    }
}

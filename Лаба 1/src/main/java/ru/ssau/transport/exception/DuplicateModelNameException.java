package ru.ssau.transport.exception;

public class DuplicateModelNameException extends Exception 
{
    public DuplicateModelNameException()
    {
        super();
    }
    public DuplicateModelNameException(String name)
    {
        super("Model whith name " + name + " already exists");
    };
}

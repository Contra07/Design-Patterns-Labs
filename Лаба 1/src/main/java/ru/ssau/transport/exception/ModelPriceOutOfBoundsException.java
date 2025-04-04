package ru.ssau.transport.exception;

public class ModelPriceOutOfBoundsException extends RuntimeException
{
    public ModelPriceOutOfBoundsException()
    {
        super();
    }
    public ModelPriceOutOfBoundsException(double price)
    {
        super("Price cant be negative number. Your price: " + Double.toString(price));
    }
}
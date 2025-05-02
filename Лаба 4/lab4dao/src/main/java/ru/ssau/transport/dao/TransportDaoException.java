package ru.ssau.transport.dao;

public class TransportDaoException extends Exception
{
    public TransportDaoException()
    {
        super();
    }
    public TransportDaoException(String message)
    {
        super(message);
    }
    public TransportDaoException(Throwable ex)
    {
        super(ex);
    }
    public TransportDaoException(String message, Throwable ex)
    {
        super(message, ex);
    }
}

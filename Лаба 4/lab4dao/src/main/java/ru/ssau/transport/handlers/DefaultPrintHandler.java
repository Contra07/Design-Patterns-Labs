package ru.ssau.transport.handlers;

import java.io.IOException;
import java.io.Writer;

import ru.ssau.transport.TransportVehicle;

public class DefaultPrintHandler implements TransportHandler, AutoCloseable
{
    private TransportHandler handler;
    protected final Writer writer;

    public DefaultPrintHandler(Writer writer) 
    {
        this.writer = writer;
        handler = null;
    }

    public DefaultPrintHandler(TransportHandler handler, Writer writer) 
    {
        this.writer = writer;
        this.handler = handler;
    }

    @Override
    public void handle(TransportVehicle transport)
    {
        if(handler != null)
        {
            handler.handle(transport);
        }
    }

    @Override
    public void setNext(TransportHandler handler) 
    {
        this.handler = handler;
    }

    @Override
    public void close() throws IOException 
    {
        if(writer != null)
            writer.close();
    }
}

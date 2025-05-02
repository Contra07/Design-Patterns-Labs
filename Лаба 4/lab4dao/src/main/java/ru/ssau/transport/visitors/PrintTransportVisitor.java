package ru.ssau.transport.visitors;

import java.io.IOException;
import java.io.Writer;

import ru.ssau.transport.Auto;
import ru.ssau.transport.Motorcycle;
import ru.ssau.transport.TransportVehicleUtils;

public class PrintTransportVisitor implements TransportVisitor, AutoCloseable
{
    private final Writer writer;

    public PrintTransportVisitor(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void visit(Auto auto) 
    {
        if(auto != null)
            TransportVehicleUtils.printHorizontal(auto, writer);
    }

    @Override
    public void visit(Motorcycle motorcycle) 
    {
        if(motorcycle != null)
            TransportVehicleUtils.printVertical(motorcycle, writer);
    }

    @Override
    public void close() throws IOException 
    {
        if(writer != null)
            writer.close();
    }
}

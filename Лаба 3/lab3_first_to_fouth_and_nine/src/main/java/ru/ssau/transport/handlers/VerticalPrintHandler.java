package ru.ssau.transport.handlers;

import java.io.Writer;

import ru.ssau.transport.TransportVehicle;
import ru.ssau.transport.TransportVehicleUtils;

public class VerticalPrintHandler extends DefaultPrintHandler
{
    private int min;
    
    public VerticalPrintHandler(Writer writer, int min) 
    {
        super(writer);
        this.min = min;
    }

    public VerticalPrintHandler(TransportHandler handler, Writer writer, int min) 
    {
        super(handler, writer);
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public void handle(TransportVehicle transport)
    {
        if(transport != null && writer != null && transport.getModelsNumber() > min)
        {
            TransportVehicleUtils.printVertical(transport, writer);
        }
        else
        {
            super.handle(transport);
        }
    }
}

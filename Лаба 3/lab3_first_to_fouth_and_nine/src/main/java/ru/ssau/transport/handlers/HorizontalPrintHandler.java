package ru.ssau.transport.handlers;

import java.io.Writer;

import ru.ssau.transport.TransportVehicle;
import ru.ssau.transport.TransportVehicleUtils;

public class HorizontalPrintHandler extends DefaultPrintHandler
{
    private int max;
    
    public HorizontalPrintHandler(Writer writer, int max) 
    {
        super(writer);
        this.max = max;
    }

    public HorizontalPrintHandler(TransportHandler handler, Writer writer, int max) 
    {
        super(handler,writer);
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public void handle(TransportVehicle transport)
    {
        if(transport != null && writer != null && transport.getModelsNumber() <= max)
        {
            TransportVehicleUtils.printHorizontal(transport, writer);
        }
        else
        {
            super.handle(transport);
        }
    }
}

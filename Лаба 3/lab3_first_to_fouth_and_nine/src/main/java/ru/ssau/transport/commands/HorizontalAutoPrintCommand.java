package ru.ssau.transport.commands;

import java.io.Writer;

import ru.ssau.transport.Auto;
import ru.ssau.transport.TransportVehicleUtils;

public class HorizontalAutoPrintCommand implements AutoPrintCommand
{
    @Override
    public void execute(Auto auto, Writer writer) 
    {
        if(auto != null && writer != null)
        {
            TransportVehicleUtils.printHorizontal(auto, writer);
        }
    }
}
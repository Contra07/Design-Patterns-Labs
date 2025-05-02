package ru.ssau.transport.commands;

import java.io.Writer;

import ru.ssau.transport.Auto;

public interface AutoPrintCommand 
{
    void execute(Auto transport, Writer writer);
}

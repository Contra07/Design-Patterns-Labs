package ru.ssau;

import java.io.Closeable;
import java.io.IOException;

public interface StringsReader extends Closeable 
{
    String[] read() throws IOException;
}
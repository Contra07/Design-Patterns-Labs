package ru.ssau;

import java.io.Closeable;
import java.io.IOException;

public interface StringsWriter extends Closeable 
{
    void write(String...lines) throws IOException;
}

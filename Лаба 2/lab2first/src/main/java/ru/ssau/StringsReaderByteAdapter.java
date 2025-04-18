package ru.ssau;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringsReaderByteAdapter implements StringsReader
{
    private final BufferedReader reader;

    public StringsReaderByteAdapter(InputStream in)
    {
        this.reader = new BufferedReader(new InputStreamReader(in));
    }

    @Override
    public String[] read() throws IOException
    {
        return reader.lines().toArray(String[]::new);
    }

    @Override
    public void close() throws IOException 
    {
        reader.close();
    }
}

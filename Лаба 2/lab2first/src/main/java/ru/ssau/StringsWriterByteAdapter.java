package ru.ssau;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class StringsWriterByteAdapter implements StringsWriter
{
    private final BufferedWriter writer;

    public StringsWriterByteAdapter(OutputStream out)
    {
        this.writer = new BufferedWriter(new OutputStreamWriter(out));
    }

    @Override
    public void write(String... lines) throws IOException
    {
        for (String line : lines) 
        {
            writer.write(line);
            writer.newLine();
        }
        writer.flush();
    }

    @Override
    public void close() throws IOException 
    {
        writer.close();
    }
}

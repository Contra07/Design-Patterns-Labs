package ru.ssau;

import java.io.*;
import java.net.*;

public class SumClient implements SumService, Closeable 
{
    private final Socket socket;

    public SumClient(String serverAddress, int serverPort) throws IOException 
    {
        socket = new Socket(serverAddress, serverPort);
    }

    @Override
    public int Sum(int number1, int number2) throws IOException
    {
        try 
        (
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream())
        ) 
        {
            out.writeInt(number1);
            out.writeInt(number2);
            out.flush();
            return in.readInt();
        }
    }

    @Override
    public void close() throws IOException 
    {
        socket.close();
    }
}
package ru.ssau;

import java.io.IOException;

public class Main 
{
    public static void main(String[] args) 
    {
        String address = "localhost";
        int port = 5000;
        int number1 = 1;
        int number2 = 10;
        var service = new SumProxy(address, port);
        try 
        {
            service.Sum(number1, number2);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
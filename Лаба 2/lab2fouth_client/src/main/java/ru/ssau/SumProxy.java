package ru.ssau;

import java.io.IOException;

public class SumProxy implements SumService
{
    private String serverAddress;
    private int serverPort;

    public SumProxy(String serverAddress, int serverPort) 
    {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    @Override
    public int Sum(int number1, int number2) throws IOException 
    {
        System.out.println("Выполняется действие: " + number1 + " + " + number2);
        try (var client = new SumClient(serverAddress, serverPort))
        {
            var result = client.Sum(number1, number2);
            System.out.println("Результат действия: " + result);
        } 
        catch (IOException e) 
        {
            System.out.println("Ошибка действия: " + e.getMessage());
            throw e;
        }
        return 0;
    }
}

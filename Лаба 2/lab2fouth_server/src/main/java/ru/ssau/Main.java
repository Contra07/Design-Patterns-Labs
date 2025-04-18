package ru.ssau;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main 
{

    public static void main(String[] args) 
    {
        final int PORT = 5000;
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) 
        {
            System.out.println("Сервер запущен и ожидает подключения на порту " + PORT);
            
            try {
                while (true) 
                {
                    Socket clientSocket = serverSocket.accept();
                    try 
                    (
                        DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())
                    ) 
                    {
                        
                        System.out.println("Клиент подключился: " + clientSocket.getInetAddress());
                        
                        int number1 = input.readInt();
                        int number2 = input.readInt();
                        
                        System.out.println("Получены числа: " + number1 + " и " + number2);
                        
                        int sum = number1 + number2;
                        System.out.println("Сумма: " + sum);
                        
                        output.writeInt(sum);
                        System.out.println("Сумма отправлена клиенту.");
                        
                    } 
                    catch (IOException e)
                    {
                        System.err.println("Ошибка общения с клиентом");
                    }
                }
            } 
            catch (IOException e) {
                System.err.println("Ошибка ожидания клиента");
            }
            
        }
        catch (IOException e) 
        {
            System.err.println("Ошибка при создании сервера");
        }
    }
}

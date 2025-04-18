package ru.ssau;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main 
{
    public static void main(String[] args) 
    {
        var textFilePath = "lab2first\\src\\main\\resources\\text.txt";
        var byteFilePath = "lab2first\\src\\main\\resources\\text.obj";
        var text = readTextFile(textFilePath);

        try 
        (
            var out = new FileOutputStream(byteFilePath);
            var strOut = new StringsWriterByteAdapter(out);
            
            var in = new FileInputStream(byteFilePath);
            var in2 = new FileInputStream(byteFilePath);
            var strIn = new StringsReaderByteAdapter(in2);
            var dIn = new DataInputStream(in);
        ) 
        {
            System.out.println("Записываем в файл: " + byteFilePath);
            strOut.write(text);
            System.out.println("Файл записан");
            
            System.out.println();

            System.out.println("Считываем файл: " + byteFilePath);
            

            byte d = 0;
            while(d != -1)
            {
                d = (byte)dIn.read();
                System.out.print(d);
            }
            
            System.out.println();
            System.out.println("Файл считан");

            System.out.println();
            System.out.println("Адаптируем файл: " + byteFilePath);
            var newText = strIn.read();
            printText(newText);
            System.out.println();
            System.out.println("Файл адаптировал");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    private static String[] readTextFile(String filepath)
    {
        try 
        {
            //x -> {return new String[x];}
            return Files.readAllLines(Path.of(filepath)).toArray(String[]::new);
        } 
        catch (IOException e) 
        {
            System.out.println("Ошибка чтения файла " + e.getMessage());
            return null;
        }
    }

    private static void printText(String[] text)
    {
        for (String line : text) 
        {
            System.out.println(line);
        }
    }
}
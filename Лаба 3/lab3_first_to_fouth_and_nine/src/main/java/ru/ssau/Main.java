package ru.ssau;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import ru.ssau.transport.Auto;
import ru.ssau.transport.Motorcycle;
import ru.ssau.transport.MotorcycleFactory;
import ru.ssau.transport.TransportVehicleUtils;
import ru.ssau.transport.commands.AutoPrintCommand;
import ru.ssau.transport.commands.HorizontalAutoPrintCommand;
import ru.ssau.transport.commands.VerticalAutoPrintCommand;
import ru.ssau.transport.exception.NoSuchModelNameException;
import ru.ssau.transport.handlers.HorizontalPrintHandler;
import ru.ssau.transport.handlers.VerticalPrintHandler;
import ru.ssau.transport.visitors.PrintTransportVisitor;

public class Main 
{
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchModelNameException
    {
        //---Задание 1---
        System.out.println("---Задание 1---");
        handlerTask();

        //---Задание 2---
        System.out.println("---Задание 2---");
        commandTask();

        //---Задание 3---
        System.out.println("---Задание 3---");
        iteratorTask();

        //---Задание 4---
        System.out.println("---Задание 4---");
        mementoTask();

        //---Задание 9---
        System.out.println("---Задание 9---");
        visitorTask();
    }

    private static void handlerTask() throws IOException
    {
        var threshold = 3;
        var filepath1 = "lab3_first_to_fouth_and_nine\\src\\main\\resources\\задание CoR.txt";
        
        var transport = TransportVehicleUtils.createInstance("Лада", 2);
        var transport2 = TransportVehicleUtils.createInstance("ГАЗ", 6);

        try 
        (
            var writer = Files.newBufferedWriter(
                Paths.get(filepath1), 
                StandardCharsets.UTF_8, 
                StandardOpenOption.CREATE, StandardOpenOption.APPEND
            );
            var handler1 = new VerticalPrintHandler(writer, threshold);
            var handler2 = new HorizontalPrintHandler(writer, threshold);
        ) 
        {
            handler1.setNext(handler2);
            System.out.println("Записываем транспорт с " + transport.getModelsNumber() + " моделями в " + filepath1);
            handler1.handle(transport);
            System.out.println("Записываем транспорт с " + transport2.getModelsNumber() + " моделями в " + filepath1);
            handler1.handle(transport2);
        } 
    }

    private static void commandTask() throws IOException
    {
        //Кто Invoker? Кто Receiver?

        var filepath = "lab3_first_to_fouth_and_nine\\src\\main\\resources\\задание Command.txt";

        var auto1 = new Auto("Ауди", 4);
        
        AutoPrintCommand command1 = new HorizontalAutoPrintCommand();
        AutoPrintCommand command2 = new VerticalAutoPrintCommand();

        try 
        (
            var writer = Files.newBufferedWriter(
                Paths.get(filepath), 
                StandardCharsets.UTF_8, 
                StandardOpenOption.CREATE, StandardOpenOption.APPEND
            )
        ) 
        {
            System.out.println("Устанавливаем горизонтальную команду");
            auto1.setPrintCommand(command1);
            System.out.println("Выполняем команду в " + filepath);
            auto1.print(writer);
            System.out.println("Устанавливаем вертикальную команду");
            auto1.setPrintCommand(command2);
            System.out.println("Выполняем команду в " + filepath);
            auto1.print(writer);
        } 
    }

    private static void iteratorTask()
    {
        var auto = new Auto("Тойота", 6);

        var iterator1 = auto.iterator();
        var iterator2 = auto.iterator();

        for (var model = iterator2.next(); iterator2.hasNext();model = iterator2.next()) 
        {
            System.out.println(model);
        }

        System.out.println("foreach цикл:");
        for (var model : auto) 
        {
            System.out.println(model);
        }
    }

    public static void mementoTask() throws NoSuchModelNameException, IOException, ClassNotFoundException
    {
        var auto = new Auto("Рено", 5);
        
        System.out.println("Текущее авто:");
        TransportVehicleUtils.printModelsAndPrices(auto);

        System.out.println("Сохраним мементо.");
        var memento = auto.getMemento();

        System.out.println("Удалим Модель автомобиля 2:");
        auto.deleteModel("Модель автомобиля 2");
        TransportVehicleUtils.printModelsAndPrices(auto);

        System.out.println("Восстановим мементо:");
        auto = auto.setMemento(memento);
        TransportVehicleUtils.printModelsAndPrices(auto);
    }

    public static void visitorTask() throws IOException
    {
        var auto = new Auto("Шкода", 3);
        var motorcycle = new Motorcycle("Ямаха", 4);
        var visitor = new PrintTransportVisitor(new OutputStreamWriter(System.out));
        System.out.println("Посещаем авто:");
        auto.acceptVisitor(visitor);
        System.out.println();
        System.out.println("Посещаем мотоцикл:");
        motorcycle.acceptVisitor(visitor);
    }
}
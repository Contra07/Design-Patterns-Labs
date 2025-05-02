package ru.ssau;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

import ru.ssau.transport.TransportVehicleUtils;
import ru.ssau.transport.dao.ObjectFileTransportDao;
import ru.ssau.transport.dao.TextFileTransportDao;
import ru.ssau.transport.dao.TransportDao;
import ru.ssau.transport.dao.TransportDaoException;
import ru.ssau.transport.exception.NoSuchModelNameException;

public class Main 
{
    public static void main(String[] args) throws TransportDaoException, NoSuchModelNameException, IOException 
    {
        var textFilename = "lab4dao\\src\\main\\resources\\transport.txt";
        var objectFilename = "lab4dao\\src\\main\\resources\\transport.bin";

        TransportDao dao1 = new TextFileTransportDao(textFilename, TransportVehicleUtils.getTransportFactory());
        TransportDao dao2 = new ObjectFileTransportDao(objectFilename);

        // var transport1 = TransportVehicleUtils.getTransportFactory().createInstance("Текстовый транспорт", 3);
        // var transport2 = TransportVehicleUtils.getTransportFactory().createInstance("Сериализованный транспорт", 2);
        // dao1.write(transport1);
        // dao2.write(transport2);
        
        var transport1 = dao1.read();
        System.out.println("Прочитано транспортное средство из файла " + textFilename);
        System.out.println();
        TransportVehicleUtils.printVertical(transport1, new OutputStreamWriter(System.out));
        System.out.println();
        System.out.println("Удаляем Модель автомобиля 1");
        System.out.println();
        transport1.deleteModel("Модель автомобиля 1");
        TransportVehicleUtils.printVertical(transport1, new OutputStreamWriter(System.out));
        System.out.println();
        System.out.println("Сохраняем транспортное");
        dao1.write(transport1);
        System.out.println("Содержимое файла: ");
        System.out.println();
        for (String line : Files.readAllLines(Path.of(textFilename))) 
        {
            System.out.println(line);
        }
        
        var transport2 = dao2.read();
        System.out.println();
        System.out.println("Прочитано транспортное средство из файла " + objectFilename);
        System.out.println();
        TransportVehicleUtils.printVertical(transport2, new OutputStreamWriter(System.out));
        System.out.println();
        System.out.println("Удаляем Модель автомобиля 1");
        System.out.println();
        transport2.deleteModel("Модель автомобиля 1");
        TransportVehicleUtils.printVertical(transport2, new OutputStreamWriter(System.out));
        System.out.println();
        System.out.println("Сохраняем транспортное");
        dao1.write(transport2);
        System.out.println("Загружаем файл ещё раз: ");
        System.out.println();
        transport2 = dao2.read();
        TransportVehicleUtils.printVertical(transport2, new OutputStreamWriter(System.out));
    }
}
package ru.ssau;

import java.io.*;
import ru.ssau.properties.*;
import ru.ssau.transport.*;
import ru.ssau.transport.exception.*;

public class Main 
{
    public static void main(String[] args) throws FileNotFoundException, IOException, CloneNotSupportedException, DuplicateModelNameException, NoSuchModelNameException 
    {
        System.out.println("-------------- Задание 1 ----------------------");
        // var properties = ConfigurationProperties.loadProperties(".\\src\\main\\resources\\config.properties");
        // properties = ConfigurationProperties.getProperties();
        // properties = ConfigurationProperties.loadProperties(".\\src\\main\\resources\\config.properties");
        // properties = ConfigurationProperties.getProperties();
        // PropertiesTools.printProperties(properties);
        var configuration = ConfigurationProperties2.getConfiguration(".\\src\\main\\resources\\config.properties");
        System.out.println("Конфигурация: ");
        PropertiesTools.printProperties(configuration.getProperties());
        System.out.println();
        configuration = ConfigurationProperties2.getConfiguration();
        configuration = ConfigurationProperties2.getConfiguration(".\\src\\main\\resources\\config2.properties");
        configuration = ConfigurationProperties2.getConfiguration();
        configuration = ConfigurationProperties2.getConfiguration();
        System.out.println("Конфигурация: ");
        PropertiesTools.printProperties(configuration.getProperties());
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println();
        System.out.println("-------------- Задание 2 ----------------------");
        var transportProperties = ConfigurationProperties2.getConfiguration().getProperties();
        
        System.out.println("Стандартная фабрика");
        var defaultFactory = TransportVehicleUtils.getTransportFactory();
        var motocycleFactory = new MotocycleFactory();

        var transport1 = TransportVehicleUtils.createInstance(
            transportProperties.get("transport1.brand").toString(),
            Integer.parseInt(transportProperties.get("transport1.modelsNumber").toString())
        );
        System.out.println(transport1.getClass().getName());
        TransportVehicleUtils.printModelsAndPrices(transport1);
        System.out.println();

        System.out.println("Устанавливаем фабрику мотоциклов");
        TransportVehicleUtils.setTransportFactory(motocycleFactory);
        var transport2 = TransportVehicleUtils.createInstance(
            transportProperties.get("transport2.brand").toString(),
            Integer.parseInt(transportProperties.get("transport2.modelsNumber").toString())
        );
        System.out.println(transport2.getClass().getName());

        TransportVehicleUtils.printModelsAndPrices(transport2);
        System.out.println();

        System.out.println("Возвращаем стандартную фабрику");
        TransportVehicleUtils.setTransportFactory(defaultFactory);
        var transport3 = TransportVehicleUtils.createInstance(
            transportProperties.get("transport3.brand").toString(),
            Integer.parseInt(transportProperties.get("transport3.modelsNumber").toString())
        );
        System.out.println(transport3.getClass().getName());

        TransportVehicleUtils.printModelsAndPrices(transport3);
        System.out.println("-----------------------------------------------");
        System.out.println();
        System.out.println("-------------- Задание 3 ----------------------");
        var originalTransport = transport2;
        System.out.println("Будем клонировать этот объект: ");
        TransportVehicleUtils.printModelsAndPrices(originalTransport);
        System.out.println();

        var clonedTransport = originalTransport.clone();
        
        System.out.println("Замена имён моделей");
        for (var model : clonedTransport.getModelNames()) {
            clonedTransport.setModelNameByModelName(model, model + " клонированная");
        }
        
        System.out.println();
        System.out.println("Оригинал ");
        TransportVehicleUtils.printModelsAndPrices(originalTransport);

        System.out.println();
        System.out.println("Клонированный: ");
        TransportVehicleUtils.printModelsAndPrices(clonedTransport);
        System.out.println();

        System.out.println("-----------------------------------------------");
    }
}
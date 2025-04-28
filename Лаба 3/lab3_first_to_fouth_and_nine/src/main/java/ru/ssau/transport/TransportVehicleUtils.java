package ru.ssau.transport;

import java.io.PrintWriter;
import java.io.Writer;

public class TransportVehicleUtils 
{
    private static TransportFactory transportFactory = new AutoFactory();

    public static TransportFactory getTransportFactory() {
        return transportFactory;
    }

    public static void setTransportFactory(TransportFactory factory) {
        transportFactory = factory;
    }

    public static TransportVehicle createInstance(String brand, int modelsNumber)
    {
        return transportFactory.createInstance(brand, modelsNumber);
    }

    public static double arithmeticMean(TransportVehicle... vehicles)
    {
        double summ = 0;
        int count = 0;
        double[] prices;
        for(TransportVehicle veh: vehicles)
        {
            prices = veh.getModelPrices();
            count += veh.getModelsNumber();
            for(double  i: prices)
            {
                summ += + i;
            }
        }
        return (summ / count);
    }
    
    public static void printModelsAndPrices(TransportVehicle vehicle)
    {
        if(vehicle != null) 
        {
            System.out.println("Brand: " + vehicle.getBrand());
            String[] names = vehicle.getModelNames();
            double [] prices = vehicle.getModelPrices();
            System.out.println("Models      Prices");
            for(int i = 0; i < vehicle.getModelsNumber();i++)
            {
                System.out.print(names[i] + "   ");
                System.out.println(prices[i]);
            }
        }
        else
        {
            System.out.println("TransportVehicle is null");
        }
    }
    
    public static void printModels(TransportVehicle vehicle)
    {
        System.out.println("Brand: " + vehicle.getBrand());
        double [] prices = vehicle.getModelPrices();
        System.out.println("Prices: ");
        for(int i = 0; i < vehicle.getModelsNumber();i++)
        {
            System.out.println(prices[i]);
        }
    }
    
    public static void printPrices(TransportVehicle vehicle)
    {
        System.out.println("Brand: " + vehicle.getBrand());
        String[] names = vehicle.getModelNames();
        System.out.println("Models: ");
        for(int i = 0; i < vehicle.getModelsNumber();i++)
        {
            System.out.println(names[i]);
        }
    }

    public static void printVertical(TransportVehicle transport, Writer writer)
    {
        var printWriter = new PrintWriter(writer);
        printWriter.print("Brand: ");
        printWriter.println(transport.getBrand());
        printWriter.print("Number of models: ");
        printWriter.println(transport.getModelsNumber());
        String[] names = transport.getModelNames();
        double[] prices = transport.getModelPrices();
        printWriter.println("Models:Prices");
        for(int i = 0; i < transport.getModelsNumber();i++)
        {
            printWriter.println(names[i]+":"+prices[i]);
        }
        printWriter.flush();
    }

    public static void printHorizontal(TransportVehicle transport, Writer writer)
    {
        var printWriter = new PrintWriter(writer);
        printWriter.print("Brand: ");
        printWriter.print(transport.getBrand() + " ");
        printWriter.print("Number of models: ");
        printWriter.print(transport.getModelsNumber() + " ");
        String[] names = transport.getModelNames();
        double[] prices = transport.getModelPrices();
        printWriter.print("Models:Prices ");
        for(int i = 0; i < transport.getModelsNumber();i++)
        {
            printWriter.print(names[i]+":"+prices[i] + " ");
        }
        printWriter.flush();
    }
}


package ru.ssau.transport;

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
}


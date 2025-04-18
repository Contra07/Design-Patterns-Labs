package ru.ssau;

import ru.ssau.transport.*;

public class Main 
{
    public static void main(String[] args) throws NoSuchMethodException, SecurityException 
    {
        var factory = TransportVehicleUtils.getTransportFactory();
        var transport = factory.createInstance("Транспорт", 1);
        var syncTransport = TransportVehicleUtils2.synchronizedTransport(transport);
        System.out.println("Транспорт: " + transport.getClass().getName());
        System.out.println("Синхр. транспорт: " + syncTransport.getClass().getName());
    }
}
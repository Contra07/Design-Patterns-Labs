package ru.ssau.transport;

import ru.ssau.transport.exception.*;

public class SynchronizedTransportDecorator implements TransportVehicle
{
    private final TransportVehicle transport;

    public SynchronizedTransportDecorator(TransportVehicle transport)
    {
        this.transport = transport;
    }

    @Override
    public synchronized void addModel(String name, double price) throws DuplicateModelNameException 
    {
        transport.addModel(name, price);
    }

    @Override
    public synchronized void deleteModel(String name) throws NoSuchModelNameException 
    {
        transport.deleteModel(name);
    }

    @Override
    public synchronized String getBrand() 
    {
        return transport.getBrand();
    }

    @Override
    public synchronized String[] getModelNames() 
    {
        return transport.getModelNames();
    }

    @Override
    public synchronized double[] getModelPrices() 
    {
        return transport.getModelPrices();
    }

    @Override
    public synchronized int getModelsNumber() 
    {
        return transport.getModelsNumber();
    }

    @Override
    public synchronized double getPriceByModelName(String name) throws NoSuchModelNameException 
    {
        return transport.getPriceByModelName(name);
    }

    @Override
    public synchronized void setBrand(String brand) 
    {
        transport.setBrand(brand);
    }

    @Override
    public synchronized void setModelNameByModelName(String name, String newname) throws DuplicateModelNameException, NoSuchModelNameException 
    {
        transport.setModelNameByModelName(name, newname);
    }

    @Override
    public synchronized void setPriceByName(String name, double price) throws NoSuchModelNameException 
    {
        transport.setPriceByName(name, price);
    }

    @Override
    public synchronized SynchronizedTransportDecorator clone() throws CloneNotSupportedException 
    {
        return new SynchronizedTransportDecorator(transport.clone());
    }
}

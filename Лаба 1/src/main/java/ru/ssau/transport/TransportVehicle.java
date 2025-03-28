package ru.ssau.transport;

import java.io.Serializable;

import ru.ssau.transport.exception.*;

public interface TransportVehicle extends Serializable, Cloneable{
    String getBrand();
    void setBrand(String newBrand);
    int getModelsNumber();
    void setModelNameByModelName(String name, String newName) throws DuplicateModelNameException, NoSuchModelNameException;
    String[] getModelNames();
    double[] getModelPrices();
    double getPriceByModelName(String name) throws NoSuchModelNameException;
    void setPriceByName (String name, double price) throws NoSuchModelNameException;
    void addModel(String name, double price) throws DuplicateModelNameException;
    void deleteModel(String name) throws NoSuchModelNameException;
    TransportVehicle clone() throws CloneNotSupportedException;
}

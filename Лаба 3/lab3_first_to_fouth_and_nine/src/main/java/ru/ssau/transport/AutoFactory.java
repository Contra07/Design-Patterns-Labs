package ru.ssau.transport;

public class AutoFactory implements TransportFactory{

    @Override
    public TransportVehicle createInstance(String brand, int modelsNumber) {
        return new Auto(brand, modelsNumber);
    }

}

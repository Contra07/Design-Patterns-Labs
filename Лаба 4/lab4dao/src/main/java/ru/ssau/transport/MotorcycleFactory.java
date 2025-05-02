package ru.ssau.transport;

public class MotorcycleFactory implements TransportFactory{

    @Override
    public TransportVehicle createInstance(String brand, int modelsNumber) {
        return new Motorcycle(brand, modelsNumber);
    }

}

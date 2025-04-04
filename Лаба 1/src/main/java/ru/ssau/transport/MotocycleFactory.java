package ru.ssau.transport;

public class MotocycleFactory implements TransportFactory{

    @Override
    public TransportVehicle createInstance(String brand, int modelsNumber) {
        return new Motocycle(brand, modelsNumber);
    }

}

package ru.ssau.transport;

public interface TransportFactory {
    TransportVehicle createInstance(String brand, int modelsNumber);
}

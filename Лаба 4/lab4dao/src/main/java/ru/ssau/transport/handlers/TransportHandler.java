package ru.ssau.transport.handlers;

import ru.ssau.transport.TransportVehicle;

public interface TransportHandler
{
    void handle(TransportVehicle transport);
    void setNext(TransportHandler handler);
}
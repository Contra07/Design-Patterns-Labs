package ru.ssau.transport;

//ru.ssau.transport.TransportVehicleUtils
public class TransportVehicleUtils2
{
    public static TransportVehicle synchronizedTransport(TransportVehicle transport)
    {
        return new SynchronizedTransportDecorator(transport);
    }
}

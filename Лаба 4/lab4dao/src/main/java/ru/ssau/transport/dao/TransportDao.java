package ru.ssau.transport.dao;

import ru.ssau.transport.TransportVehicle;

//https://www.oracle.com/java/technologies/dataaccessobject.html
public interface TransportDao
{
    TransportVehicle read() throws TransportDaoException;
    void write(TransportVehicle vehicle) throws TransportDaoException;
}

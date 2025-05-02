package ru.ssau.transport.dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import ru.ssau.transport.TransportVehicle;

public class ObjectFileTransportDao implements TransportDao
{
    private final Path filepath;

    public ObjectFileTransportDao(String filepath) 
    {
        this.filepath = Path.of(filepath);
    }

    @Override
    public TransportVehicle read() throws TransportDaoException 
    {
        try (var in = new ObjectInputStream(Files.newInputStream(filepath))) 
        {
            return (TransportVehicle)in.readObject();
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            throw new TransportDaoException("Ошибка десериализации транспортного средства из файла " + filepath, e);
        }
    }

    @Override
    public void write(TransportVehicle vehicle) throws TransportDaoException 
    {
        try (var out = new ObjectOutputStream(Files.newOutputStream(filepath)))
        {
            out.writeObject(vehicle);
        }
        catch (IOException e)
        {
            throw new TransportDaoException("Ошибка сериализации транспортного средства в файл " + filepath, e);
        }
    }
}

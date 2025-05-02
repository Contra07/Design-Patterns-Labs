package ru.ssau.transport.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import ru.ssau.transport.TransportFactory;
import ru.ssau.transport.TransportVehicle;
import ru.ssau.transport.exception.DuplicateModelNameException;

public class TextFileTransportDao implements TransportDao
{
    private final Path filepath;
    private final TransportFactory transportFactory;

    public TextFileTransportDao(String filepath, TransportFactory transportFactory) 
    {
        this.filepath = Path.of(filepath);
        this.transportFactory = transportFactory;
    }

    @Override
    public TransportVehicle read() throws TransportDaoException 
    {
        try (var in = Files.newBufferedReader(filepath))
        {
            
            var strBuf = in.readLine();
            var result = transportFactory.createInstance(strBuf, 0);
            int n = Integer.parseInt(in.readLine());
            for(int i=0; i < n; i++)
            {
                strBuf = in.readLine();
                var dobleTemp = Double.parseDouble(in.readLine());
                result.addModel(strBuf, dobleTemp);
            }
            return result;
        }
        catch (IOException | DuplicateModelNameException e) 
        {
            throw new TransportDaoException("Ошибка чтения транспортного средства из файла " + filepath, e);
        }
    }

    @Override
    public void write(TransportVehicle vehicle) throws TransportDaoException 
    {
        try (var out = new PrintWriter(Files.newBufferedWriter(filepath)))
        {
            out.println(vehicle.getBrand());
            out.println(vehicle.getModelsNumber());
            String[] tempNames = vehicle.getModelNames();
            double[] tempPrices = vehicle.getModelPrices();
            for(int i = 0; i < vehicle.getModelsNumber(); i++)
            {
                out.println(tempNames[i]);
                out.println(tempPrices[i]);
            }
            out.flush();
        }
        catch (IOException e)
        {
            throw new TransportDaoException("Ошибка записи транспортного средства в файл " + filepath, e);
        }
    }
}

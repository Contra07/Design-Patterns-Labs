package ru.ssau.properties;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ConfigurationProperties 
{
    private static Path configPath = null;
    private static Properties properties = null;

    private ConfigurationProperties(){}

    public static Properties getProperties() throws IOException
    {
        if(properties == null)
        {
            reloadProperties();
        }
        return properties;
    }
    
    public static Properties loadProperties(String path) throws IOException
    {
        var realPath = Path.of(path).toRealPath();
        if(configPath == null || !Files.isSameFile(configPath, realPath))
        {
            configPath = realPath;
            properties = null;
        }
        if(properties == null)
        {
            synchronized (ConfigurationProperties.class)
            {
                properties = loadFromFile(configPath);
            }
        }
        return properties;
    }

    public static Properties reloadProperties() throws IOException
    {
        if(configPath == null)
            properties = new Properties();
        else
        {
            synchronized (ConfigurationProperties.class)
            {
                properties = loadFromFile(configPath);
            }
        }
        return properties;
    }

    private static Properties loadFromFile(Path path) throws IOException
    {
        try(var in = Files.newInputStream(path))
        {
            try(var reader = new InputStreamReader(Files.newInputStream(path)))
            {
                var properties = new Properties();
                properties.load(reader);
                System.out.println("Загружена конфигурация из: " + path);
                return properties;
            }
        }
    }
}
package ru.ssau.properties;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ConfigurationProperties2
{
    private static ConfigurationProperties2 configuration = null;
    
    private Properties properties = null;
    private Path configPath = null;

    private ConfigurationProperties2(){}

    public Properties getProperties() throws IOException
    {
        return properties;
    }
    
    public static ConfigurationProperties2 getConfiguration(String path) throws IOException
    {
        if(configuration == null)
        {
            synchronized (ConfigurationProperties.class)
            {
                configuration = new ConfigurationProperties2();
                configuration.properties = loadFromFile(Path.of(path));
                configuration.configPath = Path.of(path);
            }
        }
        else
        {
            System.out.println("Конфигурация уже загружена из: " + configuration.configPath);
        }
        return configuration;
    }

    public static ConfigurationProperties2 getConfiguration()
    {
        if(configuration == null)
        {
            synchronized (ConfigurationProperties.class)
            {
                configuration = new ConfigurationProperties2();
            }
            System.out.println("Загружена пустая конфигурация");
        }
        return configuration;
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
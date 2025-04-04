package ru.ssau.properties;

import java.util.Properties;

public class PropertiesTools 
{
    public static void printProperties(Properties properties)
    {
        for (var property : properties.entrySet()) 
        {
            System.out.println(property.getKey() + " : " + property.getValue());
        }
    }
}

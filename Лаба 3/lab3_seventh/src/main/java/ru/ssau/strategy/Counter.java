package ru.ssau.strategy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Counter 
{
    private CountStrategy countStrategy;
    
    public Counter(CountStrategy countStrategy) 
    {
        this.countStrategy = countStrategy;
    }

    public void setCountStrategy(CountStrategy countStrategy) 
    {
        this.countStrategy = countStrategy;
    }

    public Map<Integer,Integer> countFromFile(String filepath) throws IOException, ClassNotFoundException
    {
        try (var in = new ObjectInputStream(Files.newInputStream(Paths.get(filepath)))) 
        {
            var array = (int[])in.readObject();
            if(array != null)
                return countStrategy.count(array);
            return null;
        } 
    }
}

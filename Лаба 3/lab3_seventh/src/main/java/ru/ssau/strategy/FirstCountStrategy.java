package ru.ssau.strategy;

import java.util.HashMap;
import java.util.Map;

public class FirstCountStrategy implements CountStrategy
{

    @Override
    public Map<Integer, Integer> count(int[] array) 
    {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int number : array) 
        {
            if(frequencyMap.containsKey(number))
            {
                frequencyMap.put(number, frequencyMap.get(number) + 1);
            }
            else
            {
                frequencyMap.put(number, 1);
            }
        }
        return frequencyMap;
    }

}

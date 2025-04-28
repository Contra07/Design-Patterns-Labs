package ru.ssau.strategy;

import java.util.HashMap;
import java.util.Map;

public class SecondFirstCountStrategy implements CountStrategy
{
    @Override
    public Map<Integer, Integer> count(int[] array) 
    {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < array.length; i++) 
        {
            int element = array[i];
            int counter = 0;
            for (int j = 0; j < array.length; j++) 
            {
                if(element == array[j])
                    counter++;
            }
            if(!frequencyMap.containsKey(element))
                frequencyMap.put(element, counter);
        }
        return frequencyMap;
    }

}

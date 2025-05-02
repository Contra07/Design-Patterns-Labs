package ru.ssau.mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.DoubleFunction;
import java.util.function.IntFunction;


public class FunctionModel implements Iterable<Entry<Double, Double>>
{
    private final Map<Double,Double> values;
    private final DoubleFunction<Double> function;
    private final PropertyChangeSupport pcs;

    public FunctionModel(DoubleFunction<Double> function)
    {
        this.function = function;
        this.pcs = new PropertyChangeSupport(this);
        this.values = new HashMap<>();
    }

    public class SortedFunctionIterator implements Iterator<Entry<Double, Double>>
    {
        private int i = 0;
        private final List<Entry<Double, Double>> sortedValues;
        
        public SortedFunctionIterator() 
        {
            this.sortedValues = values.entrySet()
                .stream()
                .sorted((e1, e2) -> e1.getKey() >= e2.getKey() ? 1 : -1)
                .toList();
        }

        @Override
        public boolean hasNext() 
        {
            return i < sortedValues.size();
        }

        @Override
        public Entry<Double, Double> next() 
        {
            return sortedValues.get(i++);
        }
    }

    @Override
    public Iterator<Entry<Double, Double>> iterator() 
    {
        return new SortedFunctionIterator();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public Map<Double, Double> getPoints() 
    {
        return values;
    }

    public Set<Double> getArguments()
    {
        return values.keySet();
    }

    public Collection<Double> getValues()
    {
        return values.values();
    }

    public Double getValue(double x)
    {
        return values.containsKey(x) ? values.get(x) : null;
    }

    public void addValue(double x)
    {
        double y = function.apply(x);
        values.put(x, y);
        this.pcs.firePropertyChange("values", null, null);
    }

    public void editValue(double oldX, double newX)
    {
        if(values.containsKey(oldX) && !values.containsKey(newX))
        {
            values.remove(oldX);
            addValue(newX);
        }
    }

    public void deleteValue(double x)
    {
        if(values.remove(x) != null)
            this.pcs.firePropertyChange("values", null, null);
    }
}

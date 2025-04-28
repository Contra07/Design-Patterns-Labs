package ru.ssau.transport;

import ru.ssau.transport.commands.AutoPrintCommand;
import ru.ssau.transport.exception.*;
import ru.ssau.transport.visitors.TransportVisitor;

import java.io.*;
import java.util.*;

public class Auto implements TransportVehicle, Iterable<ru.ssau.transport.Auto.Model>
{
    private String brand;
    private Model[] models;
    private AutoPrintCommand printCommand;

    public static class Model implements Serializable, Cloneable
    {
        String name;
        double price = Double.NaN;
        
        Model(){}
        
        Model(String name, double price)
        {
            setName(name);
            setPrice(price);
        }
        
        public String getName()
        {
            return name;
        }
            
        private void setName(String newName)
        {
            name = newName;
        }
        
        public double getPrice()
        {
            return price;
        }
        
        private void setPrice(double newPrice)
        {
            if (newPrice  < 0)
            {
                throw new ModelPriceOutOfBoundsException(newPrice);
            }
            else
            {
                price = newPrice;
            }
        }
        
        @Override
        public String toString() 
        {
            return name + ":" + price;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException
        {
            return super.clone();
        }
    }
    
    public static class AutoMemento
    {
        private byte[] state;
        
        private AutoMemento(){}

        //Почему здесь, а не в getMemento?
        private void setAuto(Auto auto) throws IOException 
        {
            try
            (
                var byteOut = new ByteArrayOutputStream();
                var objectOut = new ObjectOutputStream(byteOut);
            )
            {
                objectOut.writeObject(auto);
                state = byteOut.toByteArray();
            }
        }

        private Auto getAuto() throws IOException, ClassNotFoundException
        {
            if(state != null)
            {
                try (var in = new ObjectInputStream(new ByteArrayInputStream(state))) 
                {
                    return (Auto)in.readObject();
                }
            }
            else
            {
                return null;
            }
        }
    }
    
    private class AutoIterator implements Iterator<Model>
    {
        private int i = 0;

        @Override
        public boolean hasNext() 
        {
            return models != null && i < models.length;
        }

        @Override
        public Model next() 
        {
            return hasNext() ? models[i++] : null;
        }
    }
    
    public Auto()
    {
        models = new Model[0];
    }
    
    public Auto(String Brand, int modelsNumber)
    {
        brand = Brand;
        models = new Model[modelsNumber];
        for(int i= 0; i < modelsNumber; i++)
        {
            models[i] = new Model("Модель автомобиля " + (i+1), 1000 * (i%10+1));
        }
    }  
    
    @Override
    public String getBrand()
    {
        return brand;
    }
    
    @Override
    public void setBrand(String newBrand)
    {
        brand = newBrand;
    }
    
    @Override
    public int getModelsNumber()
    {
        return models.length;
    }
    
    @Override
    public void setModelNameByModelName(String name, String newName) throws DuplicateModelNameException, NoSuchModelNameException
    {
        int i = 0;
        while(i < models.length && newName.compareTo(models[i].getName()) != 0)
        {
            i++;
        }
        if (i != models.length)
        {
            throw new DuplicateModelNameException(newName); 
        }
        
        i = 0;
        while(i < models.length && name.compareTo(models[i].getName()) != 0)
        {
            i++;
        }
        if (i == models.length)
        {
            throw new NoSuchModelNameException(name); 
        }
        else
        {
            models[i].setName(newName);
        }
    }
    
    
    @Override
    public String[] getModelNames()
    {
        String[] names = new String[models.length];
        for(int i= 0; i < models.length; i++)
        {
            names[i] = models[i].getName();
        }
        return names;
    }
    
    @Override
    public double[] getModelPrices()
    {
        double[] prices = new double[models.length];
        for(int i = 0; i < models.length; i++)
        {
            prices[i] = models[i].getPrice();
        }
        return prices;
    }
    
    @Override
    public double getPriceByModelName(String name) throws NoSuchModelNameException
    {
        int i = 0;
        while(i < models.length && name.compareTo(models[i].getName()) != 0)
        {
            i++;
        }
        if (i == models.length)
        {
            throw new NoSuchModelNameException(name); 
        }
        else
        {
            return models[i].getPrice();
        }
    }
    
    @Override
    public void setPriceByName(String name, double price) throws NoSuchModelNameException
    {
        int i = 0;
        while(i < models.length && name.compareTo(models[i].getName()) != 0)
        {
            i++;
        }
        if (i == models.length)
        {
            throw new NoSuchModelNameException(name);
        }
        else
        {
            models[i].setPrice(price);
            
        }
    }
    
    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException
    {
        int i = 0;
        while(i < models.length && name.compareTo(models[i].getName()) != 0)
        {
            i++;
        }
        if (i == models.length)
        {
            models = Arrays.copyOf(models, models.length+1);
            models[models.length-1] = new Model(name,price);
        }
        else
        {
            throw new DuplicateModelNameException(name);
        }
        
    }
    
    @Override
    public void deleteModel(String name) throws NoSuchModelNameException
    {
        int i = 0;
        while(i < models.length && name.compareTo(models[i].getName()) != 0)
        {
            i++;
        }
        if (i == models.length)
        {
            throw new NoSuchModelNameException(name);
        }
        else
        {
            System.arraycopy(models, i + 1, models, i, models.length - i - 1);
            models = Arrays.copyOf(models, models.length-1);
        }
    }

    public AutoPrintCommand getPrintCommand() 
    {
        return printCommand;
    }

    public void setPrintCommand(AutoPrintCommand printCommand) 
    {
        this.printCommand = printCommand;
    }

    public void print(Writer writer)
    {
        printCommand.execute(this, writer);
    }

    public Iterator<Model> iterator()
    {
        return new AutoIterator();
    }

    public AutoMemento getMemento() throws IOException
    {
        var memento = new AutoMemento();
        memento.setAuto(this);
        return memento;
    }

    public Auto setMemento(AutoMemento memento) throws ClassNotFoundException, IOException
    {
        var auto = memento.getAuto();
        brand = auto.brand;
        models = auto.models;
        printCommand = auto.printCommand;
        return auto;
    }

    public void acceptVisitor(TransportVisitor visitor)
    {
        visitor.visit(this);
    }
    
    public String toString()
    {
        StringBuffer result = new StringBuffer();
        result.append("Class: ");
        result.append(getClass().getName());
        result.append("\n");
        result.append("Hashcode: ");
        result.append(hashCode());
        result.append("\n");
        result.append("Brand: ");
        result.append(getBrand());
        result.append("\n");
        result.append("Number of models: ");
        result.append(getModelsNumber());
        result.append("\n");
        String[] names = getModelNames();
        double [] prices = getModelPrices();
        result.append("Models:Prices\n");
        for(int i = 0; i < getModelsNumber();i++)
        {
            result.append(names[i]);
            result.append(":");
            result.append( prices[i]);
            result.append("\n");
        }
        return result.toString();
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
        {
            return false;
        }
        if(obj == this)
        {
            return true;
        }
        if(obj instanceof TransportVehicle)
        {
            TransportVehicle objNew = (TransportVehicle)obj;
            if(objNew.getBrand().equals(getBrand()) && objNew.getModelsNumber() == getModelsNumber())
            {
                int n = getModelsNumber();
                String[] names = getModelNames();
                double[] prices = getModelPrices();
                String[] objNames = objNew.getModelNames();
                double[] objPrices = objNew.getModelPrices();
                int i = 0;
                while(i < n && names[i].equals(objNames[i]) && objPrices[i] == prices[i])
                {
                    i++;
                }
                if(i == n)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() //Алгоритм взят из книги Джошуа Блоха «Effective Java»
    {
        int result = 1;
        int hBrand = getBrand().hashCode();
        result = 5* result + hBrand;
        int hNumber = Integer.hashCode(getModelsNumber());
        result = 5* result + hNumber;
        if(getModelsNumber() > 0 )
        {
            int[] hPrices = new int[getModelsNumber()];
            int[] hNames = new int[getModelsNumber()];
            double[] prices = getModelPrices();
            String[] names = getModelNames();
            for(int i = 0; i < getModelsNumber(); i++)
            {
                hPrices[i] = Double.hashCode(prices[i]);
                result = 5* result + hPrices[i];
                hNames[i] = names[i].hashCode();
                result = 5* result + hNames[i];
            }
        }
        return result;
    }
    
    @Override
    public TransportVehicle clone() throws CloneNotSupportedException
    {
       Auto result = (Auto)super.clone();
       result.models = models.clone();
       for(int i = 0; i < models.length; i++)
       {
           result.models[i] = (Model)models[i].clone();
       }
       return result;
    }
    
}

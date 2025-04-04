package ru.ssau.transport;

import ru.ssau.transport.exception.*;

public class Motocycle implements TransportVehicle
{
    private String brand;
    private int size = 0;
    private Model head;

    private class Model implements java.io.Serializable, Cloneable
    {
        private String name;
        private double price = Double.NaN;
        private Model prev;
        private Model next;
        
        Model(){}

        Model(String name, double price)
        {
            setName(name);
            setPrice(price);
        }

        Model(String name, double price, Model next, Model prev)
        {
            this(name, price);
            setPrev(prev);
            setNext(next);
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

        public Model getNext()
        {
            return next;
        }

        private void setNext(Model Next)
        {
            next = Next;
        }

        public Model getPrev()
        {
            return prev;
        }

        private void setPrev(Model Prev)
        {
            prev = Prev;
        }
        
        public Object clone() throws CloneNotSupportedException
        {
            return super.clone();
        }
    }

    public Motocycle ()
    {
       head = new Model();
       head.setNext(head);
       head.setPrev(head);
    }

    public Motocycle (String Brand, int modelsNumber)
    {
        brand = Brand;
        head = new Model();
        head.setNext(head);
        head.setPrev(head);
        Model model;
        for(int i = 0; i < modelsNumber; i++)
        {
            model = new Model("Модель мотоцикла " + (i+1), 1000 * (i%10+1), head, head.getPrev());
            head.getPrev().setNext(model);
            head.setPrev(model);
        }
        size = modelsNumber;
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
        return size;
    }

    @Override
    public void setModelNameByModelName(String name, String newName) throws DuplicateModelNameException, NoSuchModelNameException
    {
        Model model  = head.getNext();
        while(model != head && newName.compareTo(model.getName()) != 0)
        {
            model = model.getNext();
        }
        if (model != head)
        {
            throw new DuplicateModelNameException(newName);
        }

        model  = head.getNext();
        while(model != head && name.compareTo(model.getName()) != 0)
        {
            model = model.getNext();
        }
        if (model == head)
        {
           throw new NoSuchModelNameException(name);
        }
        else
        {
            model.setName(newName);
        }
    }

    @Override
    public String[] getModelNames()
    {
        String[] names = new String[size];
        Model model  = head.getNext();
        for(int i= 0; i < size; i++)
        {
            names[i] = model.getName();
            model = model.getNext();
        }
        return names;
    }

    @Override
    public double[] getModelPrices()
    {
        double[] prices = new double[size];
        Model model  = head.getNext();
        for(int i= 0; i < size; i++)
        {
            prices[i] = model.getPrice();
            model = model.getNext();
        }
        return prices;
    }

    @Override
    public double getPriceByModelName(String name) throws NoSuchModelNameException
    {
        double price = Double.NaN;
        Model model  = head.getNext();
        while(model != head && name.compareTo(model.getName()) != 0)
        {
            model = model.getNext();
        }
        if (model == head)
        {
            throw new NoSuchModelNameException(name);
        }
        else
        {
            price = model.getPrice();
        }

        return price;
    }

    @Override
    public void setPriceByName (String name, double price) throws NoSuchModelNameException
    {
        Model model  = head.getNext();
        while(model != head && name.compareTo(model.getName()) != 0)
        {
            model = model.getNext();
        }
        if (model == head)
        {
            throw new NoSuchModelNameException(name);
        }
        else
        {
            model.setPrice(price);
        }
    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException
    {
        Model model  = head.getNext();
        while(model != head && name.compareTo(model.getName()) != 0)
        {
            model = model.getNext();
        }
        if (model != head)
        {
            throw new DuplicateModelNameException(name);
        }

        model = new Model(name,price, head, head.getNext());
        head.getPrev().setNext(model);
        head.setPrev(model);
        size++;
    }

    @Override
    public void deleteModel(String name) throws NoSuchModelNameException
    {
        Model model  = head.getNext();
        while(model != head && name.compareTo(model.getName()) != 0)
        {
            model = model.getNext();
        }
        if (model == head)
        {
            throw new NoSuchModelNameException(name);
        }
        else
        {
            model.getNext().setPrev(model.getPrev());
            model.getPrev().setNext(model.getNext());
            size--;
        }
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
        if(obj == this)
        {
            return true;
        }
        if(obj != null && obj instanceof TransportVehicle)
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
    public int hashCode() //Агоритм взят из книги Джошуа Блоха «Effective Java»
    {
        int result = 1;
        int b = 5;
        
        result = b* result + getBrand().hashCode();
        result = b* result + Integer.hashCode(getModelsNumber());
        if(getModelsNumber() > 0 )
        {
            double[] prices = getModelPrices();
            String[] names = getModelNames();
            for(int i = 0; i < getModelsNumber(); i++)
            {
                result = b* result + Double.hashCode(prices[i]);
                result = b* result + names[i].hashCode();
            }
        }
        return result;
    }
    
    @Override
    public TransportVehicle clone() throws CloneNotSupportedException
    {
       Motocycle result = (Motocycle)super.clone();
       result.head = (Model)head.clone();  
       Model point = result.head; 
       while(point.getNext() != head)
       {
           point.setNext((Model)point.getNext().clone());
           point.getNext().setPrev(point);
           point = point.getNext();
       }
       point.setNext(result.head);
       result.head.setPrev(point);
       
       return result;
    }

}

package ru.ssau.crossroad;

import java.awt.*;

class Car extends Component
{
    private final boolean inHarry;
    private final int speed;
    private final String name;
    private final Image icon;

    public Car(String name, int speed, boolean inHarry, Image icon) 
    {
        this.inHarry = inHarry;
        this.name = name;
        this.speed = speed;
        this.icon = icon;
    }
    
    @Override
    public void paintAll(Graphics g) 
    {
        super.paintAll(g);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString(name, getLocation().x, getLocation().y);
        g.drawImage(icon, getLocation().x, getLocation().y, this); 
    }

    public int getSpeed()
    {
        return speed;
    }

    public String getName()
    {
        return name;
    }

    public boolean canGo(TrafficLight.State lightState, int trafficLightPlace)
    {
        var trafficLight = lightState == TrafficLight.State.Red || lightState == TrafficLight.State.Yellow && !inHarry;
        return !(trafficLight && getLocation().x + speed + 100 > trafficLightPlace) 
            || getLocation().x > trafficLightPlace;
    }

    public void moveHorizontal()
    {
        setLocation(getLocation().x+getSpeed(), getLocation().y);
    }
}
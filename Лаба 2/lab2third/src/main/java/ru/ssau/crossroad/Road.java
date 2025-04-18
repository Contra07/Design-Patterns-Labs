package ru.ssau.crossroad;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

class Road extends JPanel
{
    private Car car;
    private TrafficLight trafficLight;
    private Image backgroundImage;

    public Road(int width, int height)
    {
        super();
        setSize(width, height);
        car = createCar();
        trafficLight = createTrafficLight();
        backgroundImage  = RoadUtils.getBackgroundImage();
        var timer = new Timer(5, e -> tick(e));
        timer.start();
    }

    @Override
    public void paintChildren(Graphics g) 
    {
        super.paintChildren(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        trafficLight.paintAll(g);
        car.paintAll(g);
    }

    private void tick(ActionEvent event)
    {
        trafficLight.setLocation(getSize().width/2, getSize().height/4);

        if(car.canGo(trafficLight.getState(), trafficLight.getLocation().x))
            car.moveHorizontal();
        if(car.getLocation().x > getWidth())
            car = createCar();

        repaint();
    }

    private Car createCar()
    {
        var car = RoadUtils.createRandomCar();
        car.setLocation(0,getSize().height/2);
        return car;
    }

    private TrafficLight createTrafficLight()
    {
        var trafficLight = RoadUtils.createDefaultTrafficLight();
        trafficLight.setLocation(getSize().width/2, getSize().height/4);
        return trafficLight;
    }
}

package ru.ssau.mvc.view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class Graph extends JPanel
{
    private List<Point2D.Double> points = new LinkedList<Point2D.Double>();
    private double xScale = 4;
    private double yScale = 4;

    public double getXScale() {
        return xScale;
    }

    public void setXScale(double xScale) {
        this.xScale = xScale;
        repaint();
    }

    public double getYScale() {
        return yScale;
    }

    public void setYScale(double yScale) {
        this.yScale = yScale;
        repaint();
    }

    public void addPoint(double x, double y)
    {
        points.add(new Point2D.Double(x,y));
    }

    public void clearPoints()
    {
        points = new LinkedList<Point2D.Double>();
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        paintBack(g);
        paintAxis(g);

        var bounds = getBounds();

        if(points.size() >= 2)
        {
            g.setColor(Color.blue);
            for (int i = 0; i < points.size() - 1; i++) 
            {
                Point2D.Double firstPoint = points.get(i);
                Point2D.Double secondPoint = points.get(i+1);
                ((Graphics2D)g).draw(
                    new Line2D.Double(
                        firstPoint.x*xScale+bounds.width/2, 
                        -firstPoint.y*yScale+bounds.height/2, 
                        secondPoint.x*xScale+bounds.width/2, 
                        -secondPoint.y*yScale+bounds.height/2
                    )
                );
            }
        }
    }

    private void paintBack(Graphics g)
    {
        var bounds = getBounds();
        ((Graphics2D)g).setStroke(new BasicStroke(3f));
        g.setColor(Color.white);
        g.fillRect(0, 0, bounds.width, bounds.height);
    }

    private void paintAxis(Graphics g)
    {
        var bounds = getBounds();
        g.setColor(Color.black);
        g.drawLine(0, bounds.height/2, bounds.width, bounds.height/2);
        g.drawLine(bounds.width/2, 0, bounds.width/2, bounds.height);

        for (int i = 0; i < bounds.width/xScale; i+=10) 
        {
            ((Graphics2D)g).draw(
                new Line2D.Double(
                    i*xScale+bounds.width/2, 
                    bounds.height/2-5, 
                    i*xScale+bounds.width/2, 
                    bounds.height/2+5
                )
            );
            ((Graphics2D)g).draw(
                new Line2D.Double(
                    -i*xScale+bounds.width/2, 
                    bounds.height/2-5, 
                    -i*xScale+bounds.width/2, 
                    bounds.height/2+5
                )
            );
        }

        for (int i = 0; i < bounds.height/yScale; i+=10) 
        {
            ((Graphics2D)g).draw(
                new Line2D.Double(
                    bounds.width/2-5, 
                    i*yScale+bounds.height/2,
                    bounds.width/2+5,
                    i*yScale+bounds.height/2
                )
            );
            ((Graphics2D)g).draw(
                new Line2D.Double(
                    bounds.width/2-5, 
                    -i*yScale+bounds.height/2,
                    bounds.width/2+5,
                    -i*yScale+bounds.height/2
                )
            );
        }
    }
}
package template.figure;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public abstract class TemplateFigure extends JComponent
{
    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected int xSpeed;
    protected int ySpeed;
    protected int borderHeight;
    protected int borderWidth;
    protected Color myColor;

    public TemplateFigure(int x, int y, int width, int height, int xSpeed, int ySpeed, int borderWidth, int borderHeight, Color color) 
    {
        setBounds(0, 0, borderWidth, borderHeight);
        setPreferredSize(new Dimension(borderWidth, borderHeight));
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.borderHeight = borderHeight;
        this.borderWidth = borderWidth;
    }

    public void update()
    {
        tick();
        repaint();
    }

    public void tick()
    {
        if(x + xSpeed > borderWidth || x + xSpeed < 0)
        {
            xSpeed = -xSpeed;
        }
        if(y + ySpeed  > borderHeight || y + ySpeed < 0)
        {
            ySpeed = -ySpeed;
        }
        x += xSpeed;
        y += ySpeed;
    }

    @Override
    public abstract void paintComponent(Graphics g);
}

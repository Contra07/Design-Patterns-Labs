package template.figure;

import java.awt.Color;
import java.awt.Graphics;

public class RectangleFigure extends TemplateFigure
{
    public RectangleFigure(Color color, int x, int y, int height, int width, int xSpeed, int ySpeed, int borderWidth, int borderHeight) 
    {
        super(x, y, width, height, xSpeed, ySpeed, borderWidth, borderHeight, color);
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        g.setColor(myColor);
        g.fillRect(x, y, width, height);
    }
}

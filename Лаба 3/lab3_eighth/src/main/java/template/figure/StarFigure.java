package template.figure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class StarFigure extends TemplateFigure
{

    public StarFigure(Color color, int x, int y, int width, int height, int xSpeed, int ySpeed, int borderWidth,int borderHeight) 
    {
        super(x, y, width, height, xSpeed, ySpeed, borderWidth, borderHeight, color);
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        g.setColor(myColor);
        g.fillPolygon(createStar(x+width/2, y+height/2, width, width/2, 5));
    }

    private Polygon createStar(int centerX, int centerY, int outerRadius, int innerRadius, int numPoints) {
        Polygon polygon = new Polygon();
        double angleStep = Math.PI / numPoints; 
        double angle = -Math.PI / 2;
        
        for (int i = 0; i < numPoints * 2; i++) {
            int radius = (i % 2 == 0) ? outerRadius : innerRadius;
            int x = centerX + (int)Math.round(Math.cos(angle) * radius);
            int y = centerY + (int)Math.round(Math.sin(angle) * radius);
            polygon.addPoint(x, y);
            angle += angleStep;
        }
        return polygon;
    }
}

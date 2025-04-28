package template.factory;

import java.awt.Color;

import template.figure.StarFigure;
import template.figure.TemplateFigure;

public class StarFigureFactory implements FigureFactory
{
    public TemplateFigure getFigure(int x, int y, int height, int width, int xSpeed, int ySpeed, int borderWidth, int borderHeight)
    {
        return new StarFigure(Color.YELLOW, x, y, height, width, xSpeed, ySpeed, borderWidth, borderHeight);
    }
}
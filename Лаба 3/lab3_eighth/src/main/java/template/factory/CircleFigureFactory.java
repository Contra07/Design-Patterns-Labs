package template.factory;

import java.awt.Color;

import template.figure.CircleFigure;
import template.figure.TemplateFigure;

public class CircleFigureFactory implements FigureFactory
{
    public TemplateFigure getFigure(int x, int y, int height, int width, int xSpeed, int ySpeed, int borderWidth, int borderHeight)
    {
        return new CircleFigure(Color.green, x, y, height, width, xSpeed, ySpeed, borderWidth, borderHeight);
    }
}
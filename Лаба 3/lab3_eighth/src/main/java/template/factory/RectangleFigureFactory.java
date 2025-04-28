package template.factory;

import java.awt.Color;

import template.figure.RectangleFigure;
import template.figure.TemplateFigure;

public class RectangleFigureFactory implements FigureFactory
{
    public TemplateFigure getFigure(int x, int y, int height, int width, int xSpeed, int ySpeed, int borderWidth, int borderHeight)
    {
        return new RectangleFigure(Color.RED, x, y, height, width, xSpeed, ySpeed, borderWidth, borderHeight);
    }
}
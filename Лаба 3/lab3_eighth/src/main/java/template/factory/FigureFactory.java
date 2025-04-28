package template.factory;

import template.figure.TemplateFigure;

public interface FigureFactory  
{
    TemplateFigure getFigure(int x, int y, int height, int width, int xSpeed, int ySpeed, int borderWidth, int borderHeight);
}
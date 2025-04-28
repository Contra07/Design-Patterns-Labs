package ru.ssau;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageUtils 
{
    public static ImageIcon scale(ImageIcon icon, int scale)
    {
        int originalWidth = icon.getIconWidth();
        int originalHeight = icon.getIconHeight();
        int scaledWidth = originalWidth * scale;
        int scaledHeight = originalHeight * scale;
        Image originalImage = icon.getImage();
        Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}

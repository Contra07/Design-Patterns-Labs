package ru.ssau.crossroad;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class RoadFrame
{
    private JFrame frame;

    public RoadFrame(int width, int height)
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(width, height));
        var panel = new Road(width, height);
        frame.add(panel);
        frame.pack();
    }

    public void show()
    {
        if(frame.isDisplayable())
        {
            SwingUtilities.invokeLater(() -> {
                    frame.setVisible(true);
                }
            );
        }
    }

    public void close()
    {
        frame.dispose();
    }
}

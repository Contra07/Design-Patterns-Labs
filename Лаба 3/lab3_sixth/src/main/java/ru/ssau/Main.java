package ru.ssau;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) 
    {
        var frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
        var panel = new StudentPanel();
        frame.add(panel);
        frame.pack();
        if(frame.isDisplayable())
        {
            SwingUtilities.invokeLater(() -> {
                    frame.setVisible(true);
                }
            );
        }
    }
}
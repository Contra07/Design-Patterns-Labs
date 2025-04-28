package ru.ssau;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import template.MyPanel;

public class Main {
    public static void main(String[] args) 
    {
        var frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1400, 720));
        var panel = new MyPanel(1400, 720);
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
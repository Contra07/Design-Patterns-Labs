package ru.ssau;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import ru.ssau.mvc.Controller;
import ru.ssau.mvc.FunctionModel;
import ru.ssau.mvc.view.GraphPanel;
import ru.ssau.mvc.view.TablePanel;

public class Main {
    public static void main(String[] args) 
    {
        try 
        {
            var func = new FunctionModel(x -> 10*Math.sin(x/10));
            var tv = new TablePanel();
            var gv = new GraphPanel();
            var c = new Controller(tv, gv.getGraph(), func);
            
            for (int i = -30; i <= 30; i+=5) {
                func.addValue(i);
            }

            var frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(1280, 720));

            var p = new JPanel();
            p.setLayout(new GridLayout(1,2, 5,5));
            p.add(tv);
            p.add(gv);

            frame.add(p);
            frame.pack();
            if(frame.isDisplayable())
            {
                SwingUtilities.invokeLater(() -> {
                        frame.setVisible(true);
                    }
                );
            }
        } 
        catch (Throwable e) 
        {
            e.printStackTrace();
            throw e;
        }
    }
}
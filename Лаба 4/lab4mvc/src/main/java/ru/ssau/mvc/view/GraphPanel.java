package ru.ssau.mvc.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphPanel extends JPanel
{
    private final JButton xPlus = new JButton("X+");
    private final JButton xMinus = new JButton("X-");
    private final JButton yPlus = new JButton("Y+");
    private final JButton yMinus = new JButton("Y-");
    private final Graph graph = new Graph();

    public GraphPanel() 
    {
        xPlus.addActionListener(e -> graph.setXScale(graph.getXScale()*2));
        xMinus.addActionListener(e -> graph.setXScale(graph.getXScale()/2));
        yPlus.addActionListener(e -> graph.setYScale(graph.getYScale()*2));
        yMinus.addActionListener(e -> graph.setYScale(graph.getYScale()/2));
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        var f = new JLabel("y = 10sin(x)");
        f.setFont(f.getFont().deriveFont(f.getFont().getStyle(), 30));
        var p = new JPanel(new GridLayout(1,4, 5,5));
        p.add(xPlus);
        p.add(xMinus);
        p.add(yPlus);
        p.add(yMinus);
        var g = new JPanel(new BorderLayout(5,5));
        graph.setLayout(new BorderLayout(5,5));
        g.add(graph);
        add(f);
        add(g);
        add(p);
    }

    public Graph getGraph()
    {
        return graph;
    }
}

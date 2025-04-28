package template;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import template.factory.CircleFigureFactory;
import template.factory.FigureFactory;
import template.factory.RectangleFigureFactory;
import template.factory.StarFigureFactory;
import template.figure.TemplateFigure;

public class MyPanel extends JPanel
{
    List<Thread> threads = new ArrayList<>();
    private JPanel canvas = new JPanel();
    private JButton button = new JButton("Пуск");
    private FigureFactory factory = new CircleFigureFactory();

    public MyPanel(int width, int height) 
    {
        super();
        setPreferredSize(new Dimension(width, height));
        canvas.setPreferredSize(new Dimension(1280, 720));
        canvas.setLayout(null);
        add(canvas);

        button.addActionListener(e -> startFigureThread(factory.getFigure(1280, 720, 150, 150, 3, 3, 1280, 720)));
        
        JRadioButton option1 = new JRadioButton("Квадрат");
        JRadioButton option2 = new JRadioButton("Звезда");
        JRadioButton option3 = new JRadioButton("Мяч");
        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        
        
        option1.addActionListener(e -> factory = new RectangleFigureFactory());
        option2.addActionListener(e -> factory = new StarFigureFactory());
        option3.addActionListener(e -> factory = new CircleFigureFactory());
        option1.setEnabled(true);

        option3.setSelected(true);
        var p = new JPanel();
        p.setLayout(new GridLayout(4,1));
        p.add(button);
        p.add(option1);
        p.add(option2);
        p.add(option3);
        p.setForeground(Color.black);
        p.setBorder(getBorder());
        add(p);


        option1.setFont(option1.getFont().deriveFont(option1.getFont().getSize() + 20f));
        option2.setFont(option2.getFont().deriveFont(option2.getFont().getSize() + 20f));
        option3.setFont(option3.getFont().deriveFont(option3.getFont().getSize() + 20f));
        button.setFont(button.getFont().deriveFont(button.getFont().getSize() + 20f));
    }

    private void startFigureThread(TemplateFigure figure)
    {
        canvas.add(figure);
        var thread = new Thread(
            () -> {
                while (true) 
                {
                    figure.update();
                    try 
                    {
                        Thread.sleep(10);
                    } 
                    catch (InterruptedException e1) 
                    {
                        e1.printStackTrace();
                    }
                }
            }
        );
        threads.add(thread);
        thread.start();
    }
}
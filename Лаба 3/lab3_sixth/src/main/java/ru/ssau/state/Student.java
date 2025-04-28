package ru.ssau.state;
import java.awt.Graphics;
import javax.swing.JLabel;

public class Student extends JLabel
{
    private StudentState state;

    public Student(StudentState initState) 
    {
        changeState(initState);
        state.paintPose(this);
    }

    public void changeState(StudentState state) 
    {
        this.state = state;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        state.paintPose(this);
        super.paintComponent(g);
    }
}

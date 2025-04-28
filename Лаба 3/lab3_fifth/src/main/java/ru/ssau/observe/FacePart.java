package ru.ssau.observe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

//без JButton
public class FacePart extends JLabel 
{
    @Override
    protected void processMouseEvent(MouseEvent e) 
    {
        super.processMouseEvent(e);
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) 
    {
        super.addMouseListener(l);
    }

    @Override
    public synchronized void removeMouseListener(MouseListener l) 
    {
        super.removeMouseListener(l);
    }
    
}

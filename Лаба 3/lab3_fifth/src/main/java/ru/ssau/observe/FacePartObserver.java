package ru.ssau.observe;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.ImageIcon;

public class FacePartObserver extends MouseAdapter implements MouseListener
{
    enum Switch { open, close }
    private FacePart facePart;
    private Map<Switch, ImageIcon> imageStates;
    private Switch state;

    public FacePartObserver(FacePart facePart, Switch initState,  Map<Switch, ImageIcon> imageStates)
    {
        this.facePart = facePart;
        this.imageStates = imageStates;
        this.state = initState;
        facePart.setIcon(imageStates.get(state));
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        state = state == Switch.open ? Switch.close : Switch.open;
        if(imageStates.containsKey(state))
            facePart.setIcon(imageStates.get(state));
    }
}

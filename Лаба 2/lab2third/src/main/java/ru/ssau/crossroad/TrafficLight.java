package ru.ssau.crossroad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.Timer;

class TrafficLight extends Component
{
    enum State 
    {
        Green,
        Yellow,
        Red
    }

    private final Map<State, Image> icons;
    private final Timer timer;
    private State state;

    public TrafficLight(int period, Map<State, Image> icons) 
    {
        this(State.Green, period, icons);
    }

    public TrafficLight(State state, int period, Map<State, Image> icons) 
    {
        this.state = state;
        this.icons = icons;
        this.timer = new Timer(period, e -> tick(e));
        timer.start();
    }

    public void paintAll(Graphics g) 
    {
        super.paintAll(g);
        if(icons.containsKey(state))
        {
            g.drawImage(icons.get(state), getLocation().x, getLocation().y, this); 
        }
    }

    public State getState()
    {
        return state;
    }

    public State nextState()
    {
        switch (state) {
            case Red:
                state = State.Green;
                break;
            case Yellow:
                state = State.Red;
                break;
            case Green:
                state = State.Yellow;
                break;
            default:
                break;
        }
        return state;
    }

    private void tick(ActionEvent event)
    {
        nextState();
    }
}

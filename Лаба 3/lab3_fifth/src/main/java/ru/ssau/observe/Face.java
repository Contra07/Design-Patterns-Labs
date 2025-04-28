package ru.ssau.observe;

import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ru.ssau.ImageUtils;

//Component - Observable
//(addAL)
//(removeAL)
//(AL.actionPerformed)
//  JButton
//      FacePart
//ActionListener
//(actionPerformed)
//  FacePartObserver

public class Face extends JPanel
{
    private final static int scale = 9;
    public Face() 
    {
        super();

        var face = new JLabel(ImageUtils.scale(new ImageIcon("lab3_fifth\\src\\main\\resources\\face.png"), scale));
        

        var rightEye = new FacePart();
        var rightEyeImages = new HashMap<FacePartObserver.Switch, ImageIcon>();
        rightEyeImages.put(FacePartObserver.Switch.open, ImageUtils.scale(new ImageIcon("lab3_fifth\\src\\main\\resources\\right eye open.png"), scale));
        rightEyeImages.put(FacePartObserver.Switch.close, ImageUtils.scale(new ImageIcon("lab3_fifth\\src\\main\\resources\\right eye close.png"), scale));
        
        rightEye.addMouseListener(
            new FacePartObserver(rightEye, FacePartObserver.Switch.open,  rightEyeImages)
        );

        var leftEye = new FacePart();
        var leftEyeImages = new HashMap<FacePartObserver.Switch, ImageIcon>();
        leftEyeImages.put(FacePartObserver.Switch.open, ImageUtils.scale(new ImageIcon("lab3_fifth\\src\\main\\resources\\left eye open.png"), scale));
        leftEyeImages.put(FacePartObserver.Switch.close, ImageUtils.scale(new ImageIcon("lab3_fifth\\src\\main\\resources\\left eye close.png"), scale));
        
        leftEye.addMouseListener(
            new FacePartObserver(leftEye, FacePartObserver.Switch.open,  leftEyeImages)
        );

        var mouth = new FacePart();
        var mouthImages = new HashMap<FacePartObserver.Switch, ImageIcon>();
        mouthImages.put(FacePartObserver.Switch.open, ImageUtils.scale(new ImageIcon("lab3_fifth\\src\\main\\resources\\mouth normal.png"), scale));
        mouthImages.put(FacePartObserver.Switch.close, ImageUtils.scale(new ImageIcon("lab3_fifth\\src\\main\\resources\\mouth happy.png"), scale));
        
        mouth.addMouseListener(
            new FacePartObserver(mouth, FacePartObserver.Switch.open,  mouthImages)
        );

        var nose = new FacePart();
        var noseImages = new HashMap<FacePartObserver.Switch, ImageIcon>();
        noseImages.put(FacePartObserver.Switch.open, ImageUtils.scale(new ImageIcon("lab3_fifth\\src\\main\\resources\\nose original.png"), scale));
        noseImages.put(FacePartObserver.Switch.close, ImageUtils.scale(new ImageIcon("lab3_fifth\\src\\main\\resources\\nose pink.png"), scale));
        
        nose.addMouseListener(
            new FacePartObserver(nose, FacePartObserver.Switch.open,  noseImages)
        );

        setLayout(null);
        mouth.setBounds(215, 415, 100, 100);
        add(mouth);
        nose.setBounds(235, 340, 100, 100);
        add(nose);
        rightEye.setBounds(155, 310, 88, 64);
        add(rightEye);
        leftEye.setBounds(285, 310, 88, 64);
        add(leftEye);
        face.setBounds(100, 100, 330, 500);
        add(face);  
    }
}
package ru.ssau.state;

import javax.swing.ImageIcon;

//Нужен ли отдельный класс под состояние?
public class IconStudentState implements StudentState
{
    private ImageIcon icon;

    public IconStudentState(ImageIcon icon) {
        this.icon = icon;
    }

    @Override
    public void paintPose(Student student) 
    {
        student.setIcon(icon);
    }
}
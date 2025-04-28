package ru.ssau;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import ru.ssau.state.IconStudentState;
import ru.ssau.state.Student;

public class StudentPanel extends JPanel
{
    public StudentPanel() 
    {
        super();
        var termState = new IconStudentState(new ImageIcon("lab3_sixth\\src\\main\\resources\\term.png"));
        var midtermState = new IconStudentState(new ImageIcon("lab3_sixth\\src\\main\\resources\\midterm.png")); 
        var holidaysState = new IconStudentState(new ImageIcon("lab3_sixth\\src\\main\\resources\\holydays.png")); 
        
        var student = new Student(termState);

        var termButton = new JButton("Семестр");
        termButton.setFont(termButton.getFont().deriveFont(termButton.getFont().getSize() + 20f));
        var midtermButton = new JButton("Сессия");
        midtermButton.setFont(midtermButton.getFont().deriveFont(midtermButton.getFont().getSize() + 20f));
        var holidaysButton = new JButton("Каникулы");
        holidaysButton.setFont(holidaysButton.getFont().deriveFont(holidaysButton.getFont().getSize() + 20f));

        termButton.addActionListener(e -> student.changeState(termState));
        midtermButton.addActionListener(e -> student.changeState(midtermState));
        holidaysButton.addActionListener(e -> student.changeState(holidaysState));

        add(student);
        add(termButton);
        add(midtermButton);
        add(holidaysButton);
    }
}

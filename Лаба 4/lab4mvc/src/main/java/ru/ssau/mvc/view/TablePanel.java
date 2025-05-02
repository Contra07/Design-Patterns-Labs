package ru.ssau.mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.*;

public class TablePanel extends JPanel
{
    private final TwoColumnsTable valesTable = new TwoColumnsTable();
    private final JTextField newArgumentField = new JTextField();
    private final JButton addButton = new JButton("Добавить");
    private final JButton editButton = new JButton("Изменить");
    private final JButton deleteButton = new JButton("Удалить");

    private ActionListener addActionListener;
    private ActionListener editActionListener;
    private ActionListener deleteActionListener;

    private Double newValue = null;

    public TablePanel() 
    {
        super();
        this.addButton.addActionListener(e -> 
            {
                readNewValue();
                if(getNewValue() != null)
                {
                    addActionListener.actionPerformed(e);
                }
            }
        );
        this.editButton.addActionListener(e -> 
            {
                readNewValue();
                if(getOldValue() != null && getNewValue() != null)
                {
                    editActionListener.actionPerformed(e);
                }
            }
        );
        this.deleteButton.addActionListener(e -> 
            {
                if(getOldValue() != null)
                {
                    deleteActionListener.actionPerformed(e);
                }
            }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        var b = new JPanel(new BorderLayout(5,5));
        b.add(new JScrollPane(valesTable));
        this.add(b);
        var tf = new JPanel(new GridLayout(1,1, 5,5));
        tf.add(newArgumentField);
        this.add(tf);

        var buttonPanel = new JPanel(new GridLayout(1,3, 5,5));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        this.add(buttonPanel);
    }
    
    public void setAddActionListener(ActionListener addActionListener) 
    {
        this.addActionListener = addActionListener;
    }
    
    public void setEditActionListener(ActionListener editActionListener) 
    {
        this.editActionListener = editActionListener;
    }

    public void setDeleteActionListener(ActionListener deleteActionListener) 
    {
        this.deleteActionListener = deleteActionListener;
    }

    public void setValues(List<Entry<Double, Double>> values)
    {
        valesTable.setTableValues(values);
    }

    public Double getNewValue()
    {
        return newValue;
    }

    public Double getOldValue()
    {
        return valesTable.getSelectedValue();
    }

    private void readNewValue()
    {
        try
        {
            var text = newArgumentField.getText();
            newValue = Double.parseDouble(text);
        } 
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "Введите пожалуйста число", "Ошибка", JOptionPane.ERROR_MESSAGE);
            newValue = null;
        }
    }
}

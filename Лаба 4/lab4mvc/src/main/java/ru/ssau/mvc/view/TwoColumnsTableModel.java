package ru.ssau.mvc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

class TwoColumnsTableModel extends AbstractTableModel
{
    private List<Entry<Double, Double>> values;

    public TwoColumnsTableModel() 
    {
        super();
        this.values = new ArrayList<>();
    }

    public TwoColumnsTableModel(List<Entry<Double, Double>> values) 
    {
        super();
        this.values = values;
    }

    public List<Entry<Double, Double>> getValues() 
    {
        return values;
    }

    public void setValues(List<Entry<Double, Double>> values) 
    {
        this.values = values;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() 
    {
        return values.size();
    }

    @Override
    public int getColumnCount() 
    {
        return 2;
    }

    @Override
    public String getColumnName(int column) 
    {
        switch (column) {
            case 0:
                return "X";
            case 1:
                return "Y";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        switch (columnIndex) {
            case 0:
                return values.get(rowIndex).getKey();
            case 1:
            return values.get(rowIndex).getValue();
            default:
                return null;
        }
    }
}
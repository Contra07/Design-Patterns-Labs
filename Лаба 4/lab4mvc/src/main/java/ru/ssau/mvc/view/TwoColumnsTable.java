package ru.ssau.mvc.view;

import java.util.List;
import java.util.Map.Entry;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

public class TwoColumnsTable extends JTable
{
    private int sRow = -1;
    private boolean selected = false;
    private Double selectedValue = null;

    public TwoColumnsTable() 
    {
        setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        setCellSelectionEnabled(false);
        setRowSelectionAllowed(true);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if(getSelectionModel() != null)
        {
            getSelectionModel().addListSelectionListener(e -> cellSelected(e));
        }
        setModel(new TwoColumnsTableModel());
    }

    public Double getSelectedValue()
    {
        return selectedValue;
    }

    public void setTableValues(List<Entry<Double, Double>> values)
    {
        ((TwoColumnsTableModel)getModel()).setValues(values);
    }

    private void cellSelected(ListSelectionEvent event) 
    {
        if (!event.getValueIsAdjusting()) 
        {
            var selectedRow = getSelectedRow();
            var selectedAnchorRow = getSelectionModel().getAnchorSelectionIndex();
            var selectedAnchorColumn = getColumnModel().getSelectionModel().getAnchorSelectionIndex();
            if (selectedRow != -1 && sRow != selectedRow) 
            {
                if(sRow != selectedRow)
                {       
                    sRow = selectedRow;
                    selected = false;
                    selectedValue = null;
                }
                if(!selected)
                {
                    getSelectionModel().removeSelectionInterval
                    (
                        getSelectionModel().getAnchorSelectionIndex(), 
                        getSelectionModel().getLeadSelectionIndex()
                    ); 
                    getColumnModel().getSelectionModel().removeSelectionInterval
                    (
                        getColumnModel().getSelectionModel().getAnchorSelectionIndex(), 
                        getColumnModel().getSelectionModel().getLeadSelectionIndex()
                    );
                    getSelectionModel().setSelectionInterval(selectedAnchorRow, selectedAnchorRow);
                    getColumnModel().getSelectionModel().setAnchorSelectionIndex(selectedAnchorColumn);
                    getColumnModel().getSelectionModel().setLeadSelectionIndex(selectedAnchorColumn);
                    selected = true;
                    selectedValue = (Double)((TwoColumnsTableModel)getModel()).getValueAt(selectedRow, 0);
                }
            }
        }
    }
}

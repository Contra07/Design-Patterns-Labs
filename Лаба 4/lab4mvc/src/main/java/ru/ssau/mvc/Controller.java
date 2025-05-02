package ru.ssau.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import ru.ssau.mvc.view.Graph;
import ru.ssau.mvc.view.TablePanel;

public class Controller 
{
    private final TablePanel tableView;
    private final Graph graphView;
    private final FunctionModel functionModel;

    public Controller(TablePanel tableView, Graph graphView, FunctionModel functionModel) 
    {
        this.tableView = tableView;
        this.graphView = graphView;
        this.functionModel = functionModel;

        this.functionModel.addPropertyChangeListener(e -> 
            {
                List<Entry<Double, Double>> tmp = new ArrayList<>();
                for (var entry : functionModel) 
                {
                    tmp.add(entry);
                }
                tableView.setValues(tmp);
            }
        );

        this.functionModel.addPropertyChangeListener(e -> 
            {
                graphView.clearPoints();
                for (var entry : functionModel) 
                {
                    graphView.addPoint(entry.getKey(), entry.getValue());
                }
                graphView.repaint();
            }
        );

        tableView.setAddActionListener(
            e -> {
                functionModel.addValue(tableView.getNewValue());
            }
        );
        tableView.setEditActionListener(
            e -> {
                functionModel.editValue(tableView.getOldValue(), tableView.getNewValue());
            }
        );
        tableView.setDeleteActionListener(
            e -> {
                functionModel.deleteValue(tableView.getOldValue());
            }
        );
    }

    public void addArgument(double value)
    {
        functionModel.addValue(value);
    }

    public void changeArgument(double oldValue, double newValue)
    {
        functionModel.editValue(oldValue, newValue);
    }

    public void deleteArgument(double value)
    {
        functionModel.deleteValue(value);
    }
}

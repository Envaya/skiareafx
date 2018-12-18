package ch.fhnw.oop2.skiareasfx.view;

import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class SkiAreaHeader extends GridPane implements ViewMixin {
    private RootPM model;
    private Label regionName;
    private Label skiAreaName;

    public SkiAreaHeader(RootPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        regionName = new Label("Area name here");
        skiAreaName = new Label("region name here");
    }

    @Override
    public void layoutControls() {
        ColumnConstraints colConstr = new ColumnConstraints();
        colConstr.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(colConstr, colConstr);

        RowConstraints rowConstr = new RowConstraints();
        rowConstr.setVgrow(Priority.ALWAYS);
        getRowConstraints().addAll(rowConstr, rowConstr, rowConstr, rowConstr);
        add(skiAreaName, 0,0);
        add(regionName, 0,1);
    }
}

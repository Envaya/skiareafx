package ch.fhnw.oop2.skiareasfx.view;

import ch.fhnw.oop2.skiareasfx.Control.OpenLiftsGraph;
import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import ch.fhnw.oop2.skiareasfx.presentationmodel.Skiarea;
import ch.fhnw.oop2.skiareasfx.skiregion_cc.SkiregionControl;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.converter.NumberStringConverter;

import java.util.Locale;

public class SkiAreaEditor extends GridPane implements ViewMixin {
    private RootPM model;
    private Label nameLabel;
    private TextField nameField;
    private Label regionLabel;
    private TextField regionField;
    private OpenLiftsGraph openLifts;
    private Label labelOpenLifts;
    private TextField textFieldOpenLifts;
    private Label labelTotalLifts;
    private TextField textFieldTotalLifts;
    private SkiregionControl skiRegionControl;
    private Label communesInAreaLabel;
    private Label metersAboveSeaMinLabel;
    private Label metersAboveSeaMaxLabel;
    private Label runsKMLabel;
    private Label dragLiftsLabel;

    public SkiAreaEditor(RootPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeSelf() {
//        getStyleClass().add("skiarea-header-container");
    }

    @Override
    public void initializeControls() {
        nameLabel = new Label("Name");
        nameField = new TextField();
        regionLabel = new Label("Gebiet");
        regionField = new TextField();
        openLifts = new OpenLiftsGraph();
        labelOpenLifts = new Label("geöffnet:");
        textFieldOpenLifts = new TextField();
        labelTotalLifts = new Label("Lifte:");
        textFieldTotalLifts = new TextField();
        skiRegionControl = new SkiregionControl();
    }

    @Override
    public void layoutControls() {
        ColumnConstraints colConstrGrow = new ColumnConstraints();
        ColumnConstraints containCol = new ColumnConstraints();
        colConstrGrow.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(containCol,colConstrGrow,containCol,colConstrGrow);

        RowConstraints rowConstr = new RowConstraints();
        rowConstr.setVgrow(Priority.ALWAYS);
        getRowConstraints().addAll(rowConstr, rowConstr);
        add(nameLabel, 0,0);
        add(nameField, 1,0);
        add(regionLabel, 2,0);
        add(regionField, 3,0);
        add(openLifts, 1,1);
        add(skiRegionControl, 1,2);
    }

    @Override
    public void setupBindings() {
//        create the proxy within our local instance of RootPM and bind
//         the values of our UI class to the proxy
        Skiarea proxy = model.getSkiAreaProxy();
        nameField.textProperty().bindBidirectional(proxy.skiareaNameProperty());
        regionField.textProperty().bindBidirectional(proxy.regionProperty());
        textFieldOpenLifts.textProperty().bindBidirectional(proxy.OPEN_LIFTSProperty(), new NumberStringConverter());
        textFieldTotalLifts.textProperty().bindBidirectional(proxy.LIFTS_TOTALProperty(), new NumberStringConverter());
        openLifts.valueProperty().bindBidirectional(proxy.OPEN_LIFTSProperty());
        openLifts.maxValueProperty().bindBidirectional(proxy.LIFTS_TOTALProperty());
        skiRegionControl.skiregionProperty().bindBidirectional(proxy.regionProperty());
    }
}

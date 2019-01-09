package ch.fhnw.oop2.skiareasfx.view;

import ch.fhnw.oop2.skiareasfx.Control.OpenLiftsGraph;
import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import ch.fhnw.oop2.skiareasfx.presentationmodel.Skiarea;
import ch.fhnw.oop2.skiareasfx.skiregion_cc.SkiregionControl;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.layout.ColumnConstraints;

import java.util.Locale;

public class SkiAreaEditor extends GridPane implements ViewMixin {
    private RootPM model;

    private Label nameLabel;
    private TextField nameField;

    private Label regionLabel;
    private TextField regionField;

    private Label communesInAreaLabel;
    private TextField communesInAreaField;

    private Label metersAboveSeaMinLabel;
    private TextField metersAboveSeaMinField;

    private Label metersAboveSeaMaxLabel;
    private TextField metersAboveSeaMaxField;

    private Label runsKMLabel;
    private TextField runsKMField;

    private Label dragLiftsLabel;
    private TextField dragLiftsField;

    private Label chairLiftsLabel;
    private TextField chairLiftsField;

    private Label cableCarsLabel;
    private TextField cableCarsField;

    private OpenLiftsGraph openLifts;

    private Label snowDepthLabel;
    private TextField snowDepthField;

    private Label visitorsTodayLabel;
    private TextField visitorsTodayField;

    private Label carFreeLabel;
    private CheckBox carFree;

    private Label funparkOpenLabel;
    private CheckBox funparkOpenField;

    private Label imageURLLabel;
    private TextField imageURLField;

    private TextField textFieldOpenLifts;

    private SkiregionControl skiRegionControl;

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
        communesInAreaLabel = new Label("Orte im Gebiet");
        metersAboveSeaMinLabel = new Label("Talstation (m.ü.M)");
        metersAboveSeaMaxLabel = new Label("Bergstation (m.ü.M.)");
        runsKMLabel = new Label("Pistenlänge (km)");
        dragLiftsLabel = new Label("Schlepplifte");
        chairLiftsLabel = new Label("Sessellifte");
        cableCarsLabel = new Label("Gondeln");
        snowDepthLabel = new Label("Schneehöhe (cm)");
        visitorsTodayLabel = new Label("Besucher");
        carFreeLabel = new Label("autofrei");
        funparkOpenLabel = new Label("Funpark geöffnet");
        imageURLLabel = new Label("Bild URL");

        openLifts = new OpenLiftsGraph();
        textFieldOpenLifts = new TextField();
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
        openLifts.valueProperty().bindBidirectional(proxy.OPEN_LIFTSProperty());
        openLifts.maxValueProperty().bindBidirectional(proxy.LIFTS_TOTALProperty());
        skiRegionControl.skiregionProperty().bindBidirectional(proxy.regionProperty());
    }
    public void setUpValueChangeListener() {
        dragLiftsField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                newValue = "0";
            }
            model.getSkiAreaProxy().LIFTS_TOTALProperty().setValue(model.getSkiAreaProxy().getCHAIR_LIFTS() + Integer.parseInt(newValue) + model.getSkiAreaProxy().getCABLE_CARS());
        }));

        chairLiftsField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                newValue = "0";
            }
            model.getSkiAreaProxy().LIFTS_TOTALProperty().setValue(model.getSkiAreaProxy().getDRAG_LIFTS() + Integer.parseInt(newValue) + model.getSkiAreaProxy().getCABLE_CARS());
        }));

        cableCarsField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                newValue = "0";
            }
            model.getSkiAreaProxy().LIFTS_TOTALProperty().setValue(model.getSkiAreaProxy().getCHAIR_LIFTS() + Integer.parseInt(newValue) + model.getSkiAreaProxy().getDRAG_LIFTS());
        }));
    }
}

package ch.fhnw.oop2.skiareasfx.view;

import ch.fhnw.oop2.skiareasfx.Control.OpenLiftsGraph;
import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import ch.fhnw.oop2.skiareasfx.presentationmodel.Skiarea;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.layout.ColumnConstraints;

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

    private Label snowDepthLabel;
    private TextField snowDepthField;

    private Label visitorsTodayLabel;
    private TextField visitorsTodayField;

    private Label carFreeLabel;
    private CheckBox carFreeBox;

    private Label funparkAvailableLabel;
    private CheckBox funparkAvailableBox;

    private Label imageURLLabel;
    private TextField imageURLField;

    private Label openLiftsLabel;
    private TextField openLiftsField;

    private static final double MAXIMUM_SIZE = 100;

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
        communesInAreaField = new TextField();
        metersAboveSeaMinLabel = new Label("Talstation (m.ü.M)");
        metersAboveSeaMinField = new TextField();
        metersAboveSeaMaxLabel = new Label("Bergstation (m.ü.M.)");
        metersAboveSeaMaxField = new TextField();
        runsKMLabel = new Label("Pistenlänge (km)");
        runsKMField = new TextField();
        dragLiftsLabel = new Label("Schlepplifte");
        dragLiftsField = new TextField();
        chairLiftsLabel = new Label("Sessellifte");
        chairLiftsField = new TextField();
        cableCarsLabel = new Label("Gondeln");
        cableCarsField = new TextField();
        snowDepthLabel = new Label("Schneehöhe (cm)");
        snowDepthField = new TextField();
        visitorsTodayLabel = new Label("Besucher");
        visitorsTodayField = new TextField();
        carFreeLabel = new Label("autofrei");
        carFreeBox = new CheckBox();
        funparkAvailableLabel = new Label("Funpark geöffnet");
        funparkAvailableBox = new CheckBox();
        imageURLLabel = new Label("Bild URL");
        imageURLField = new TextField();

        openLiftsLabel = new Label("Lifte offen");
        openLiftsField= new TextField();
    }

    @Override
    public void layoutControls() {
        ColumnConstraints colConstrGrow = new ColumnConstraints();
        ColumnConstraints containCol = new ColumnConstraints();
//        colConstrGrow.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(containCol,colConstrGrow,containCol,colConstrGrow);
        colConstrGrow.setHalignment(HPos.LEFT);
        regionField.setPrefSize(140,20);
        setAlignment(Pos.CENTER);

        RowConstraints rowConstr = new RowConstraints();
        rowConstr.setVgrow(Priority.ALWAYS);
        getRowConstraints().addAll(rowConstr, rowConstr, rowConstr, rowConstr, rowConstr, rowConstr, rowConstr, rowConstr, rowConstr, rowConstr, rowConstr, rowConstr);
        setHgap(12);
        setVgap(10);


        add(nameLabel, 0,0);
        add(nameField, 1,0);
        add(regionLabel, 2,0);
        add(regionField, 3,0);

        add(communesInAreaLabel, 0,1);
        add(communesInAreaField, 1,1,3,1);

        add(metersAboveSeaMinLabel,0,2);
        add(metersAboveSeaMinField,1,2);
        add(metersAboveSeaMaxLabel,2,2);
        add(metersAboveSeaMaxField,3,2);

        add(runsKMLabel,0,3);
        add(runsKMField,1,3);
        add(dragLiftsLabel,2,3);
        add(dragLiftsField,3,3);

        add(chairLiftsLabel,0,4);
        add(chairLiftsField,1,4);
        add(cableCarsLabel,2,4);
        add(cableCarsField,3,4);

        add(snowDepthLabel,0,5);
        add(snowDepthField,1,5);
        add(visitorsTodayLabel,2,5);
        add(visitorsTodayField,3,5);

        add(carFreeLabel,0,6);
        add(carFreeBox,1,6);
        add(funparkAvailableLabel,2,6);
        add(funparkAvailableBox,3,6);

        add(imageURLLabel,0,7);
        add(imageURLField,1,7,3,1);

        add(openLiftsLabel,0,8);
        add(openLiftsField,1,8);
    }

    @Override
    public void setupBindings() {
//        create the proxy within our local instance of RootPM and bind
//         the values of our UI class to the proxy
        Skiarea proxy = model.getSkiAreaProxy();
        nameField.textProperty().bindBidirectional(proxy.skiareaNameProperty());
        regionField.textProperty().bindBidirectional(proxy.regionProperty());
        communesInAreaField.textProperty().bindBidirectional(proxy.COMMUNES_IN_AREAProperty());
        metersAboveSeaMinField.textProperty().bindBidirectional(proxy.METERS_ABOVE_SEA_MINProperty(), new NumberStringConverter());
        metersAboveSeaMaxField.textProperty().bindBidirectional(proxy.METERS_ABOVE_SEA_MINProperty(), new NumberStringConverter());
        runsKMField.textProperty().bindBidirectional(proxy.SKI_RUNS_KMProperty(), new NumberStringConverter());
        dragLiftsField.textProperty().bindBidirectional(proxy.DRAG_LIFTSProperty(), new NumberStringConverter());
        chairLiftsField.textProperty().bindBidirectional(proxy.CHAIR_LIFTSProperty(), new NumberStringConverter());
        cableCarsField.textProperty().bindBidirectional(proxy.CABLE_CARSProperty(), new NumberStringConverter());
        snowDepthField.textProperty().bindBidirectional(proxy.SNOW_DEPTH_CMProperty(), new NumberStringConverter());
        visitorsTodayField.textProperty().bindBidirectional(proxy.VISITORS_TODAYProperty(), new NumberStringConverter());
        carFreeBox.selectedProperty().bindBidirectional(proxy.CAR_FREEProperty());
        funparkAvailableBox.selectedProperty().bindBidirectional(proxy.FUNPARK_AVAILABLEProperty());
        imageURLField.textProperty().bindBidirectional(proxy.IMAGE_URLProperty());

        openLiftsField.textProperty().bindBidirectional(proxy.OPEN_LIFTSProperty(), new NumberStringConverter());
    }

    @Override
    public void setupValueChangedListeners() {
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

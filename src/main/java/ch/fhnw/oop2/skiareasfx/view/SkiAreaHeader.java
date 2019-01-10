package ch.fhnw.oop2.skiareasfx.view;

import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import ch.fhnw.oop2.skiareasfx.presentationmodel.Skiarea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.converter.NumberStringConverter;

import java.awt.*;
import java.util.Locale;

public class SkiAreaHeader extends GridPane implements ViewMixin {
    private RootPM model;
    private Label regionName;
    private Label skiAreaName;
    private Label snowHeight;
    private Image skiAreaImage;

    public SkiAreaHeader(RootPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeSelf() {
//        getStyleClass().add("skiarea-header-container");
    }

    @Override
    public void initializeControls() {
        regionName = new Label();
        skiAreaName = new Label();
        snowHeight = new Label();
        skiAreaName.getStyleClass().add("skiarea-header-title");
//        skiAreaImage = new Image(model.getSkiAreaProxy().IMAGE_URLProperty().toString());

    }

    @Override
    public void layoutControls() {
        ColumnConstraints colConstr50 = new ColumnConstraints();
        colConstr50.setPercentWidth(50);
//        colConstr.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(colConstr50);

        RowConstraints rowConstr = new RowConstraints();
        rowConstr.setVgrow(Priority.ALWAYS);
        getRowConstraints().addAll(rowConstr, rowConstr, rowConstr, rowConstr);
        add(skiAreaName, 0,0);
        add(regionName, 0,1);
        add(snowHeight,0,2);
//        add(new ImageView(skiAreaImage),3,0,2,2);
    }

    @Override
    public void setupBindings() {
        //create the proxy within our local instance of RootPM and bind
        // the values of our UI class to the proxy
        Skiarea proxy = model.getSkiAreaProxy();
        skiAreaName.textProperty().bind(proxy.skiareaNameProperty());
        regionName.textProperty().bind(proxy.regionProperty());
        snowHeight.textProperty().bind(model.getSkiAreaProxy().SNOW_DEPTH_CMProperty().asString("%d cm Schnee"));
    }
}

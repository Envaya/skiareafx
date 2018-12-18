package ch.fhnw.oop2.skiareasfx.view;

import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ContentRight extends VBox implements ViewMixin {
    private RootPM model;
    private SkiAreaHeader skiAreaHeader;

    public ContentRight(RootPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        skiAreaHeader = new SkiAreaHeader(model);
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(skiAreaHeader);
    }
}

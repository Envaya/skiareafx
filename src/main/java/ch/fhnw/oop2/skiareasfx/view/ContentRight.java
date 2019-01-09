package ch.fhnw.oop2.skiareasfx.view;

import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ContentRight extends VBox implements ViewMixin {
    private RootPM model;
    private SkiAreaHeader skiAreaHeader;
    private SkiAreaEditor skiAreaEditor;

    public ContentRight(RootPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        skiAreaHeader = new SkiAreaHeader(model);
        skiAreaEditor = new SkiAreaEditor(model);
//        skiAreaEditor.setHgap(5);
        //horizontally center the content in the editor
//        skiAreaEditor.setAlignment(Pos.CENTER);
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(skiAreaHeader, skiAreaEditor);
    }
}

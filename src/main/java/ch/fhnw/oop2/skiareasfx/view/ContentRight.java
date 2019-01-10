package ch.fhnw.oop2.skiareasfx.view;

import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ch.fhnw.oop2.skiareasfx.skiregion_cc.SkiregionControl;
import ch.fhnw.oop2.skiareasfx.Control.OpenLiftsGraph;

public class ContentRight extends VBox implements ViewMixin {
    private RootPM model;
    private SkiAreaHeader skiAreaHeader;
    private SkiAreaEditor skiAreaEditor;
    private SkiregionControl skiRegionControl;
    private OpenLiftsGraph openLifts;


    public ContentRight(RootPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        skiAreaHeader = new SkiAreaHeader(model);
        skiAreaEditor = new SkiAreaEditor(model);
        skiRegionControl = new SkiregionControl();
        skiRegionControl.setMaxWidth(200);
        skiAreaHeader.minHeight(200);
        openLifts = new OpenLiftsGraph();

        //horizontally center the content in the editor
        skiAreaEditor.setAlignment(Pos.CENTER);
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("content");
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(skiAreaHeader, skiAreaEditor, new HBox(skiRegionControl, openLifts));
    }

    @Override
    public void setupBindings() {
        skiRegionControl.skiregionProperty().bindBidirectional(model.getSkiAreaProxy().regionProperty());
        openLifts.valueProperty().bindBidirectional(model.getSkiAreaProxy().OPEN_LIFTSProperty());
        openLifts.maxValueProperty().bindBidirectional(model.getSkiAreaProxy().LIFTS_TOTALProperty());
    }

}

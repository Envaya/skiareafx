package ch.fhnw.oop2.skiareasfx.view;

import ch.fhnw.oop2.skiareasfx.presentationmodel.Skiarea;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;

public class ApplicationUI extends BorderPane implements ViewMixin {
    private final RootPM model;
    private ToolbarHeader toolbar;
    private Button button;
    private SplitPane splitContentArea;
    private ContentLeft skiAreaTable;
    private SkiAreaDetails skiAreaDetails;

    public ApplicationUI(RootPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeSelf() {
        addStylesheetFiles("style.css");
    }

    @Override
    public void initializeControls() {
        button = new Button("Hello");
        toolbar = new ToolbarHeader();
        skiAreaTable = new ContentLeft(model);
        skiAreaDetails = new SkiAreaDetails();

        splitContentArea = new SplitPane(skiAreaTable, skiAreaDetails);
        //add Split Pane here with the two sides (GridPane) as attributes)
    }

    @Override
    public void layoutControls() {
        setMargin(button   , new Insets(5));
        setTop(toolbar);
        setCenter(splitContentArea);
    }

    @Override
    public void setupBindings() {
        button.textProperty().bind(model.greetingProperty());
    }
}

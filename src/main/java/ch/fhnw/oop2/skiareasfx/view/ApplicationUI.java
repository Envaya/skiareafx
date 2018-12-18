package ch.fhnw.oop2.skiareasfx.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;

public class ApplicationUI extends BorderPane implements ViewMixin {
    private final RootPM rootPM;
    private ToolbarHeader toolbar;
    private Button button;
    private Content contentArea;

    public ApplicationUI(RootPM model) {
        this.rootPM = model;
        init();
    }

    @Override
    public void initializeSelf() {
        addStylesheetFiles("style.css");
    }

    @Override
    public void initializeControls() {
        button = new Button();
        toolbar = new ToolbarHeader();
        // Stackpane for the content in the center
        contentArea = new Content();
        //add Split Pane here with the two sides (GridPane) as attributes)
    }

    @Override
    public void layoutControls() {
        setMargin(button   , new Insets(5));
        setTop(toolbar);
        setCenter(contentArea);

    }

    @Override
    public void setupBindings() {
        button.textProperty().bind(rootPM.greetingProperty());
    }
}

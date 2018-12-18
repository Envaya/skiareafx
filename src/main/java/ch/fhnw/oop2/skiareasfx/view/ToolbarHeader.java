package ch.fhnw.oop2.skiareasfx.view;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.text.View;

public class ToolbarHeader extends HBox implements ViewMixin {

    private TextField searchBar;
    private Button saveButton;

    public ToolbarHeader() {
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("toolbarHeader");
    }

    @Override
    public void initializeControls() {
        saveButton = new Button("Save");
        searchBar = new TextField();
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(searchBar, saveButton);
    }
}

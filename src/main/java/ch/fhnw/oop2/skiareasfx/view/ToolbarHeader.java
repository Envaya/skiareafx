package ch.fhnw.oop2.skiareasfx.view;

import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.text.View;

public class ToolbarHeader extends HBox implements ViewMixin {

    private TextField searchBar;
    private Button saveButton;
    private Button addButton;
    private Button deleteButton;
    private RootPM model;

    public ToolbarHeader(RootPM m) {
        this.model = m;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("toolbarHeader");
    }

    @Override
    public void initializeControls() {
        saveButton = new Button("Speichern");
        addButton = new Button("Neu");
        deleteButton = new Button("Löschen");
        searchBar = new TextField();
    }

    @Override
    public void setupEventHandlers() {
        saveButton.setOnAction(e -> model.save());
        addButton.setOnAction(event -> model.addSkiArea());
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(searchBar, saveButton, addButton, deleteButton);
    }
}

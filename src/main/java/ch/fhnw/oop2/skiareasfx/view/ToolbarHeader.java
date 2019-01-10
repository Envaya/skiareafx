package ch.fhnw.oop2.skiareasfx.view;

import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.text.View;

public class ToolbarHeader extends HBox implements ViewMixin {

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
        saveButton = new Button();
        addButton = new Button();
        deleteButton = new Button();
        Image saveIcon = new Image(getClass().getResourceAsStream("/icons/save.png"), 16,24, true, true);
        Image deleteIcon = new Image(getClass().getResourceAsStream("/icons/delete.png"), 16,24, true, true);
        Image addIcon = new Image(getClass().getResourceAsStream("/icons/add.png"), 16,24, true, true);
        saveButton.setGraphic(new ImageView(saveIcon));
        deleteButton.setGraphic(new ImageView(deleteIcon));
        addButton.setGraphic(new ImageView(addIcon));

        setSpacing(10);
    }

    @Override
    public void setupEventHandlers() {
        saveButton.setOnAction(e -> model.save());
        addButton.setOnAction(ee -> model.addSkiArea());
        deleteButton.setOnAction(eee -> model.deleteSkiArea());
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(saveButton, addButton, deleteButton);
    }
}

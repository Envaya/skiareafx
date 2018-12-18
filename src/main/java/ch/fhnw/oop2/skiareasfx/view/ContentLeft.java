package ch.fhnw.oop2.skiareasfx.view;

import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import ch.fhnw.oop2.skiareasfx.presentationmodel.Skiarea;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class ContentLeft extends VBox implements ViewMixin {
    private RootPM model;
    private TableView<Skiarea> skiareaTable;

    public ContentLeft(RootPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        skiareaTable = new TableView<>(model.getSkiareas());
        TableColumn<Skiarea, String> nameColumn = new TableColumn<>("Name");
        skiareaTable.getColumns().addAll(nameColumn);
        nameColumn.setCellValueFactory(cell -> cell.getValue().skiareaNameProperty());
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(skiareaTable);
    }
}

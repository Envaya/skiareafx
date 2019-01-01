package ch.fhnw.oop2.skiareasfx.view;

import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import ch.fhnw.oop2.skiareasfx.presentationmodel.Skiarea;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class ContentLeft extends VBox implements ViewMixin {
    private RootPM model;
    private TableView<Skiarea> skiareaTable;

    public ContentLeft(RootPM model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        skiareaTable = new TableView<>(model.getAllSkiAreas());

        TableColumn<Skiarea, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cell -> cell.getValue().skiareaNameProperty());

        TableColumn<Skiarea, String> regionColumn = new TableColumn<>("Region");
        regionColumn.setCellValueFactory(cell -> cell.getValue().regionProperty());

        TableColumn<Skiarea, Number> skiRunsLengthColumn = new TableColumn<>("Pistenlänge");
        skiRunsLengthColumn.setCellValueFactory(cell -> cell.getValue().SKI_RUNS_KMProperty());

        TableColumn<Skiarea, Number> snowDepthColumn = new TableColumn<>("Schneetiefe");
        snowDepthColumn.setCellValueFactory(cell -> cell.getValue().SNOW_DEPTH_CMProperty());

        skiareaTable.getColumns().addAll(nameColumn, regionColumn, skiRunsLengthColumn, snowDepthColumn);

        skiareaTable.setItems(model.getAllSkiAreas());
        skiareaTable.getSortOrder().setAll(nameColumn);
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(skiareaTable);
        setVgrow(skiareaTable, Priority.ALWAYS);
    }
    @Override
    public void setupValueChangedListeners() {
        skiareaTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    model.selectedSkiAreaIdProperty().setValue(newValue.getId());
                });

    }
}

package ch.fhnw.oop2.skiareasfx.presentationmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RootPM {
    private final StringProperty applicationTitle = new SimpleStringProperty("Swiss Ski Areas");
    private final IntegerProperty selectedSkiAreaId = new SimpleIntegerProperty();

    private static final String FILE_NAME = "SKIAREA.csv";
    private static final String DELIMITER = ";";

    private final ObservableList<Skiarea> allSkiAreas = FXCollections.observableArrayList();
    private final Skiarea skiAreaProxy = new Skiarea();

    public RootPM() {
        //create our root pm instance, read file and add all the collected skiareas into
        //a observable list.
        //add a change listener to that list
        allSkiAreas.addAll(readFromFile());
        //listen to when selected skiarea changes
        selectedSkiAreaIdProperty().addListener((observable, oldValue, newValue) -> {
                    Skiarea previouslySelectedSkiArea = getSkiarea(oldValue.intValue());
                    Skiarea newlySelectedSkiArea = getSkiarea(newValue.intValue());

                    //change the proxy skiarea attribute to the newly selected skiarea
                    if (previouslySelectedSkiArea != null) {
                        unbindFromProxy(previouslySelectedSkiArea);
                    }

                    if (newlySelectedSkiArea != null) {
                        bindToProxy(newlySelectedSkiArea);
                    }
                }
        );
    }

    private List<Skiarea> readFromFile() {
        // create Stream, seperate lines and fields and collect all
        try (Stream<String> stream = getStreamOfLines(FILE_NAME)) {
            return stream.skip(1)
                    .map(line -> new Skiarea(line.split(DELIMITER, 22)))
                    .collect(Collectors.toList());
        }
    }

    private Path getPath(String fileName) {
        try {
            return Paths.get(getClass().getResource(fileName).toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    //implement
    public void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(getPath(FILE_NAME))) {
            writer.write("Switzerland's most famous SkiAreas");
            writer.newLine();
            allSkiAreas.stream()
                    .map(skiarea -> skiarea.infoAsLine(DELIMITER))
                    .forEach(line -> {
                        try {
                            writer.write((String) line);
                            writer.newLine();
                        } catch (IOException e) {
                            throw new IllegalStateException(e);
                        }
                    });
        } catch (IOException e) {
            throw new IllegalStateException("The skiarea couldn't be saved. Please try again.");
        }
    }

    private Stream<String> getStreamOfLines(String fileName) {
        try {
            return Files.lines(getPath(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void bindToProxy(Skiarea skiarea) {
        skiAreaProxy.idProperty().bind(skiarea.idProperty());
        skiAreaProxy.skiareaNameProperty().bindBidirectional(skiarea.skiareaNameProperty());
        skiAreaProxy.regionProperty().bindBidirectional(skiarea.regionProperty());
        skiAreaProxy.OPEN_LIFTSProperty().bindBidirectional(skiarea.OPEN_LIFTSProperty());
        skiAreaProxy.LIFTS_TOTALProperty().bindBidirectional(skiarea.LIFTS_TOTALProperty());
        skiAreaProxy.regionProperty().bindBidirectional(skiarea.regionProperty());
    }

    private void unbindFromProxy(Skiarea skiarea) {
        skiAreaProxy.idProperty().unbind();
        skiAreaProxy.skiareaNameProperty().unbindBidirectional(skiarea.skiareaNameProperty());
        skiAreaProxy.regionProperty().unbindBidirectional(skiarea.regionProperty());
        skiAreaProxy.OPEN_LIFTSProperty().unbindBidirectional(skiarea.OPEN_LIFTSProperty());
        skiAreaProxy.LIFTS_TOTALProperty().unbindBidirectional(skiarea.LIFTS_TOTALProperty());
        skiAreaProxy.regionProperty().unbindBidirectional(skiarea.regionProperty());
    }

    private Skiarea getSkiarea(int id) {
        return allSkiAreas.stream()
                .filter(skiArea -> skiArea.getId() == id)
                .findAny()
                .orElse(null);
    }

    // all getters and setters
    public Skiarea getSkiAreaProxy() {
        return skiAreaProxy;
    }

    public String getApplicationTitle() {
        return applicationTitle.get();
    }

    public StringProperty applicationTitleProperty() {
        return applicationTitle;
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle.set(applicationTitle);
    }

    public ObservableList<Skiarea> getAllSkiAreas() {
        return allSkiAreas;
    }

    public int getSelectedSkiAreaId() {
        return selectedSkiAreaId.get();
    }

    public IntegerProperty selectedSkiAreaIdProperty() {
        return selectedSkiAreaId;
    }

    public void setSelectedSkiAreaId(int selectedSkiAreaId) {
        this.selectedSkiAreaId.set(selectedSkiAreaId);
    }


}

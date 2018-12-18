package ch.fhnw.oop2.skiareasfx.presentationmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    private final StringProperty applicationTitle = new SimpleStringProperty("SkiAreasFX");
    private final StringProperty greeting         = new SimpleStringProperty("Hello World!");

    private static final String FILE_NAME = "SKIAREA.csv";
    private static final String DELIMITER = ";";

    private final ObservableList<Skiarea> skiareas = FXCollections.observableArrayList();

    public RootPM() {
        skiareas.addAll(readFromFile());
    }

    private List<Skiarea> readFromFile() {
        // zuerst Stream erstellen
        try(Stream<String> stream = getStreamOfLines(FILE_NAME)) {
            // Jede Line ist ein String
            return stream.skip(1)
                    // wir teilen den String einer Line in die einzelnen Spalten auf und erstellen mit diesen Daten ein Resultat Objekt
                    .map(line -> new Skiarea(line.split(DELIMITER, 22)))
                    .collect(Collectors.toList());
        }
    }

    private Path getPath(String fileName)  {
        try {
            return Paths.get(getClass().getResource(fileName).toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Stream<String> getStreamOfLines(String fileName) {
        try {
            return Files.lines(getPath(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    // all getters and setters
    public String getApplicationTitle() {
        return applicationTitle.get();
    }

    public StringProperty applicationTitleProperty() {
        return applicationTitle;
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle.set(applicationTitle);
    }

    public String getGreeting() {
        return greeting.get();
    }

    public StringProperty greetingProperty() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting.set(greeting);
    }

    public ObservableList<Skiarea> getSkiareas() {
        return skiareas;
    }

}

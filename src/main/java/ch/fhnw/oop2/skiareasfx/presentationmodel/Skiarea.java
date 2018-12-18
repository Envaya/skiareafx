package ch.fhnw.oop2.skiareasfx.presentationmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Skiarea {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty skiareaName = new SimpleStringProperty();
    private final StringProperty region = new SimpleStringProperty();


    public Skiarea(String[] line) {
        setId(Integer.valueOf(line[0]));
        setSkiareaName(line[1]);
        setRegion(line[2]);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getSkiareaName() {
        return skiareaName.get();
    }

    public StringProperty skiareaNameProperty() {
        return skiareaName;
    }

    public void setSkiareaName(String skiareaName) {
        this.skiareaName.set(skiareaName);
    }

    public String getRegion() {
        return region.get();
    }

    public StringProperty regionProperty() {
        return region;
    }

    public void setRegion(String region) {
        this.region.set(region);
    }
}

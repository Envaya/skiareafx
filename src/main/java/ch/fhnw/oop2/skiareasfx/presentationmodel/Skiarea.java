package ch.fhnw.oop2.skiareasfx.presentationmodel;

import javafx.beans.property.*;

public class Skiarea {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty skiareaName = new SimpleStringProperty();
    private final StringProperty region = new SimpleStringProperty();
    private final StringProperty COMMUNES_IN_AREA = new SimpleStringProperty();
    private final IntegerProperty METERS_ABOVE_SEA_MIN = new SimpleIntegerProperty();
    private final IntegerProperty METERS_ABOVE_SEA_MAX = new SimpleIntegerProperty();
    private final DoubleProperty SKI_RUNS_KM = new SimpleDoubleProperty();
    private final IntegerProperty DRAG_LIFTS = new SimpleIntegerProperty();
    private final IntegerProperty CHAIR_LIFTS = new SimpleIntegerProperty();
    private final IntegerProperty CABLE_CARS = new SimpleIntegerProperty();
    private final IntegerProperty LIFTS_TOTAL = new SimpleIntegerProperty();
    private final IntegerProperty OPEN_LIFTS = new SimpleIntegerProperty();
    private final IntegerProperty SNOW_DEPTH_CM = new SimpleIntegerProperty();
    private final IntegerProperty VISITORS_TODAY = new SimpleIntegerProperty();
    private final BooleanProperty CAR_FREE = new SimpleBooleanProperty();
    private final BooleanProperty FUNPARK_AVAILABLE = new SimpleBooleanProperty();
    private final StringProperty IMAGE_URL = new SimpleStringProperty();



    public Skiarea(){
        //proxy
    }

    public Skiarea(int id){
        setId(id);
    }

    public String infoAsLine(String delimiter) {
        return String.join(delimiter,
                Integer.toString(getId()),
                getSkiareaName(),
                getRegion(),
                getCOMMUNES_IN_AREA(),
                Integer.toString(getMETERS_ABOVE_SEA_MIN()),
                Integer.toString(getMETERS_ABOVE_SEA_MAX()),
                Double.toString(getSKI_RUNS_KM()),
                Integer.toString(getDRAG_LIFTS()),
                Integer.toString(getCHAIR_LIFTS()),
                Integer.toString(getCABLE_CARS()),
                Integer.toString(getLIFTS_TOTAL()),
                Integer.toString(getOPEN_LIFTS()),
                Integer.toString((int)getSNOW_DEPTH_CM()),
                Integer.toString(getVISITORS_TODAY()),
                Boolean.toString(isCAR_FREE()),
                Boolean.toString(isFUNPARK_AVAILABLE()),
                getIMAGE_URL()
        );
    }

//    public Skiarea(int id, String skiareaName, String region, String communes, int masMin,
//                   int masMax, double runsKm, int dragLifts, int chairLifts, int cableCars,
//                   int liftsTotal, double snowDepth, int visitors, boolean carFree, boolean funparkAvailable, String imgURL) {
//        setId(id);
//        setSkiareaName(skiareaName);
//        setRegion(region);
//        setCOMMUNES_IN_AREA(communes);
//        setMETERS_ABOVE_SEA_MIN(masMin);
//        setMETERS_ABOVE_SEA_MAX(masMax);
//        setSKI_RUNS_KM(runsKm);
//        setDRAG_LIFTS(dragLifts);
//        setCHAIR_LIFTS(chairLifts);
//        setCABLE_CARS(cableCars);
//        setLIFTS_TOTAL(liftsTotal);
//        setSNOW_DEPTH_CM(snowDepth);
//        setVISITORS_TODAY(visitors);
//        setCAR_FREE(carFree);
//        setFUNPARK_AVAILABLE(funparkAvailable);
//        setIMAGE_URL(imgURL);
//    }

    public Skiarea(String[] line) {
        setId(Integer.valueOf(line[0]));
        setSkiareaName(line[1]);
        setRegion(line[2]);
        setCOMMUNES_IN_AREA(line[3]);
        setMETERS_ABOVE_SEA_MIN(Integer.valueOf(line[4]));
        setMETERS_ABOVE_SEA_MAX(Integer.valueOf(line[5]));
        setSKI_RUNS_KM(Double.valueOf(line[6]));
        setDRAG_LIFTS(Integer.valueOf(line[7]));
        setCHAIR_LIFTS(Integer.valueOf(line[8]));
        setCABLE_CARS(Integer.valueOf(line[9]));
        setLIFTS_TOTAL(Integer.valueOf(line[10]));
        setOPEN_LIFTS(Integer.valueOf(line[11]));
        setSNOW_DEPTH_CM(Integer.valueOf(line[12]));
        setVISITORS_TODAY(Integer.valueOf(line[13]));
        setCAR_FREE(Boolean.valueOf(line[14]));
        setFUNPARK_AVAILABLE(Boolean.valueOf(line[15]));
        String s =(line[16]);
        setIMAGE_URL(s);
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

    public String getCOMMUNES_IN_AREA() {
        return COMMUNES_IN_AREA.get();
    }

    public StringProperty COMMUNES_IN_AREAProperty() {
        return COMMUNES_IN_AREA;
    }

    public void setCOMMUNES_IN_AREA(String COMMUNES_IN_AREA) {
        this.COMMUNES_IN_AREA.set(COMMUNES_IN_AREA);
    }

    public int getMETERS_ABOVE_SEA_MIN() {
        return METERS_ABOVE_SEA_MIN.get();
    }

    public IntegerProperty METERS_ABOVE_SEA_MINProperty() {
        return METERS_ABOVE_SEA_MIN;
    }

    public void setMETERS_ABOVE_SEA_MIN(int METERS_ABOVE_SEA_MIN) {
        this.METERS_ABOVE_SEA_MIN.set(METERS_ABOVE_SEA_MIN);
    }

    public int getMETERS_ABOVE_SEA_MAX() {
        return METERS_ABOVE_SEA_MAX.get();
    }

    public IntegerProperty METERS_ABOVE_SEA_MAXProperty() {
        return METERS_ABOVE_SEA_MAX;
    }

    public void setMETERS_ABOVE_SEA_MAX(int METERS_ABOVE_SEA_MAX) {
        this.METERS_ABOVE_SEA_MAX.set(METERS_ABOVE_SEA_MAX);
    }

    public double getSKI_RUNS_KM() {
        return SKI_RUNS_KM.get();
    }

    public DoubleProperty SKI_RUNS_KMProperty() {
        return SKI_RUNS_KM;
    }

    public void setSKI_RUNS_KM(double SKI_RUNS_KM) {
        this.SKI_RUNS_KM.set(SKI_RUNS_KM);
    }

    public int getDRAG_LIFTS() {
        return DRAG_LIFTS.get();
    }

    public IntegerProperty DRAG_LIFTSProperty() {
        return DRAG_LIFTS;
    }

    public void setDRAG_LIFTS(int DRAG_LIFTS) {
        this.DRAG_LIFTS.set(DRAG_LIFTS);
    }

    public int getCHAIR_LIFTS() {
        return CHAIR_LIFTS.get();
    }

    public IntegerProperty CHAIR_LIFTSProperty() {
        return CHAIR_LIFTS;
    }

    public void setCHAIR_LIFTS(int CHAIR_LIFTS) {
        this.CHAIR_LIFTS.set(CHAIR_LIFTS);
    }

    public int getCABLE_CARS() {
        return CABLE_CARS.get();
    }

    public IntegerProperty CABLE_CARSProperty() {
        return CABLE_CARS;
    }

    public void setCABLE_CARS(int CABLE_CARS) {
        this.CABLE_CARS.set(CABLE_CARS);
    }

    public int getLIFTS_TOTAL() {
        return LIFTS_TOTAL.get();
    }

    public IntegerProperty LIFTS_TOTALProperty() {
        return LIFTS_TOTAL;
    }

    public void setLIFTS_TOTAL(int LIFTS_TOTAL) {
        this.LIFTS_TOTAL.set(LIFTS_TOTAL);
    }

    public int getOPEN_LIFTS() {
        return OPEN_LIFTS.get();
    }

    public IntegerProperty OPEN_LIFTSProperty() {
        return OPEN_LIFTS;
    }

    public void setOPEN_LIFTS(int OPEN_LIFTS) {
        this.OPEN_LIFTS.set(OPEN_LIFTS);
    }

    public int getSNOW_DEPTH_CM() {
        return SNOW_DEPTH_CM.get();
    }

    public IntegerProperty SNOW_DEPTH_CMProperty() {
        return SNOW_DEPTH_CM;
    }

    public void setSNOW_DEPTH_CM(int SNOW_DEPTH_CM) {
        this.SNOW_DEPTH_CM.set(SNOW_DEPTH_CM);
    }

    public int getVISITORS_TODAY() {
        return VISITORS_TODAY.get();
    }

    public IntegerProperty VISITORS_TODAYProperty() {
        return VISITORS_TODAY;
    }

    public void setVISITORS_TODAY(int VISITORS_TODAY) {
        this.VISITORS_TODAY.set(VISITORS_TODAY);
    }

    public boolean isCAR_FREE() {
        return CAR_FREE.get();
    }

    public BooleanProperty CAR_FREEProperty() {
        return CAR_FREE;
    }

    public void setCAR_FREE(boolean CAR_FREE) {
        this.CAR_FREE.set(CAR_FREE);
    }

    public boolean isFUNPARK_AVAILABLE() {
        return FUNPARK_AVAILABLE.get();
    }

    public BooleanProperty FUNPARK_AVAILABLEProperty() {
        return FUNPARK_AVAILABLE;
    }

    public void setFUNPARK_AVAILABLE(boolean FUNPARK_AVAILABLE) {
        this.FUNPARK_AVAILABLE.set(FUNPARK_AVAILABLE);
    }

    public String getIMAGE_URL() {
        return IMAGE_URL.get();
    }

    public StringProperty IMAGE_URLProperty() {
        return IMAGE_URL;
    }

    public void setIMAGE_URL(String IMAGE_URL) {
        this.IMAGE_URL.set(IMAGE_URL);
    }

}

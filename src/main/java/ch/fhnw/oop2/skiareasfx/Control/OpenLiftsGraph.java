package ch.fhnw.oop2.skiareasfx.Control;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleablePropertyFactory;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.util.Duration;

/**
 * @author Reto Bornhauser
 */
public class OpenLiftsGraph extends Region {
    // needed for StyleableProperties
    private static final StyleablePropertyFactory<OpenLiftsGraph> FACTORY = new StyleablePropertyFactory<>(Region.getClassCssMetaData());


    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    private static final String FONTS_CSS = "/fonts/fonts.css";
    private static final String STYLE_CSS = "openLiftStyle.css";

    private static final double ARTBOARD_WIDTH  = 250;
    private static final double ARTBOARD_HEIGHT = 265;

    private static final double ASPECT_RATIO = ARTBOARD_WIDTH / ARTBOARD_HEIGHT;

    private static final double MINIMUM_WIDTH  = 80;
    private static final double MINIMUM_HEIGHT = MINIMUM_WIDTH / ASPECT_RATIO;

    private static final double MAXIMUM_WIDTH = 800;

    private static final Duration ANIMATION_DURATION = Duration.millis(400);

    // all parts
    private Text   titleLabel;
    private Text   valueLabel;
    private Text maxLabel;
    private Circle barBackground;
    private Arc    bar;

    // all properties
    private final StringProperty title    = new SimpleStringProperty("Geöffnete Lifte");
    private final IntegerProperty minValue = new SimpleIntegerProperty();
    private final IntegerProperty maxValue = new SimpleIntegerProperty();
    private final IntegerProperty value    = new SimpleIntegerProperty();

    private final IntegerProperty animatedValue = new SimpleIntegerProperty();

    private final BooleanProperty          timerIsRunning = new SimpleBooleanProperty(false);
    private final ObjectProperty<Duration> pulse          = new SimpleObjectProperty<>(Duration.seconds(0.5));

    //CSS stylable properties
    private static final CssMetaData<OpenLiftsGraph, Color> BASE_COLOR_META_DATA = FACTORY.createColorCssMetaData("-base-color", s -> s.baseColor);

    private final StyleableObjectProperty<Color> baseColor = new SimpleStyleableObjectProperty<Color>(BASE_COLOR_META_DATA, this, "baseColor") {
        @Override
        protected void invalidated() {
            setStyle(BASE_COLOR_META_DATA.getProperty() + ": " + get().toString().replace("0x", "#") + ";");
            applyCss();
        }
    };

    // all animations
    private final Timeline timeline = new Timeline();

    // all parts need to be children of the drawingPane
    private Pane drawingPane;

    private final AnimationTimer timer = new AnimationTimer() {
        private long lastTimerCall;

        @Override
        public void handle(long now) {
            if (now > lastTimerCall + (getPulse().toMillis() * 1_000_000L)) {
                performPeriodicTask();
                lastTimerCall = now;
            }
        }
    };

    public OpenLiftsGraph() {
        initializeSelf();
        initializeParts();
        initializeDrawingPane();
        layoutParts();
        initializeAnimations();
        setupEventHandlers();
        setupValueChangedListeners();
        setupBindings();
    }

    private void initializeSelf() {
        addStyleSheets(this);
        getStyleClass().add("slim-value-display");
    }

    private void initializeParts() {
        double cx = ARTBOARD_WIDTH * 0.5;


        titleLabel = createCenteredText(cx, 19, "title");
        titleLabel.setTextOrigin(VPos.TOP);

        valueLabel = createCenteredText(cx, 150, "value");

        maxLabel = createCenteredText(cx, 188, "unit");
        maxLabel.setTextOrigin(VPos.TOP);

        barBackground = new Circle(125, 150, 100);
        barBackground.getStyleClass().add("bar-background");

        bar = new Arc(125, 150, 100, 100, 90, 0);
        bar.getStyleClass().add("bar");
        bar.setType(ArcType.OPEN);
    }

    private void initializeDrawingPane() {
        drawingPane = new Pane();
        drawingPane.getStyleClass().add("drawing-pane");
        drawingPane.setMaxSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        drawingPane.setMinSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        drawingPane.setPrefSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
    }

    private void layoutParts() {
        // add all your parts here
        drawingPane.getChildren().addAll(barBackground, bar, titleLabel, valueLabel, maxLabel);

        getChildren().add(drawingPane);
    }

    private void initializeAnimations() {

    }

    private void setupEventHandlers() {

    }

    private void setupValueChangedListeners() {
        valueProperty().addListener((observable, oldValue, newValue) -> {
            bar.setVisible(true);
            checkBoundaries(newValue);

            timeline.stop();
            timeline.getKeyFrames().setAll(new KeyFrame(ANIMATION_DURATION,
                    new KeyValue(animatedValueProperty(),
                            newValue,
                            Interpolator.EASE_BOTH)));

            timeline.play();
        });

        animatedValueProperty().addListener((observable, oldValue, newValue) -> bar.setLength(getAngle(newValue)));

        minValueProperty().addListener((observable, oldValue, newValue) -> {
            bar.setVisible(true);
            checkBoundaries(getValue());
            bar.setLength(getAngle(getValue()));
        });

        maxValueProperty().addListener((observable, oldValue, newValue) -> {
            bar.setVisible(true);
            checkBoundaries(getValue());
            bar.setLength(getAngle(getValue()));
        });

        // if you need the timer
        timerIsRunning.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                timer.start();
            } else {
                timer.stop();
            }
        });
    }


    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        resize();
    }

    private void checkBoundaries(Number newValue) {
        setTimerIsRunning(newValue.doubleValue() > getMaxValue() || newValue.doubleValue() < getMinValue());
    }

    private double getAngle(Number value) {
        return -(3.6 * getPercentage(value));
    }

    private double getPercentage(Number newValue) {
        double min = getMinValue();
        double max = getMaxValue();
        return Math.max(1.0, Math.min(100.0, (newValue.doubleValue() - min) / ((max - min) / 100.0)));
    }

    private void setupBindings() {
        titleLabel.textProperty().bind(titleProperty());
        valueLabel.textProperty().bind(animatedValueProperty().asString());
        maxLabel.textProperty().bind(maxValueProperty().asString());

    }

    private void performPeriodicTask() {
        bar.setVisible(!bar.isVisible());
    }

    // some useful helper-methods

    private Text createCenteredText(String styleClass) {
        return createCenteredText(ARTBOARD_WIDTH * 0.5, ARTBOARD_HEIGHT * 0.5, styleClass);
    }

    private Text createCenteredText(double cx, double cy, String styleClass) {
        Text text = new Text();
        text.getStyleClass().add(styleClass);
        text.setTextOrigin(VPos.CENTER);
        text.setTextAlignment(TextAlignment.CENTER);
        double width = cx > ARTBOARD_WIDTH * 0.5 ? ((ARTBOARD_WIDTH - cx) * 2.0) : cx * 2.0;
        text.setWrappingWidth(width);
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setY(cy);

        return text;
    }

    /*
     * angle in degrees, 0 is north
     */
    private double angle(double cx, double cy, double x, double y) {
        double deltaX = x - cx;
        double deltaY = y - cy;
        double radius = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        double nx     = deltaX / radius;
        double ny     = deltaY / radius;
        double theta  = Math.toRadians(90) + Math.atan2(ny, nx);

        return Double.compare(theta, 0.0) >= 0 ? Math.toDegrees(theta) : Math.toDegrees((theta)) + 360.0;
    }

    /*
     * angle in degrees, 0 is north
     */
    private Point2D pointOnCircle(double cX, double cY, double radius, double angle) {
        return new Point2D(cX - (radius * Math.sin(Math.toRadians(angle - 180))),
                cY + (radius * Math.cos(Math.toRadians(angle - 180))));
    }

    private void resize() {
        Insets padding         = getPadding();
        double availableWidth  = getWidth() - padding.getLeft() - padding.getRight();
        double availableHeight = getHeight() - padding.getTop() - padding.getBottom();

        double width = Math.max(Math.min(Math.min(availableWidth, availableHeight * ASPECT_RATIO), MAXIMUM_WIDTH), MINIMUM_WIDTH);

        double scalingFactor = width / ARTBOARD_WIDTH;

        if (availableWidth > 0 && availableHeight > 0) {
            drawingPane.relocate((getWidth() - ARTBOARD_WIDTH) * 0.5, (getHeight() - ARTBOARD_HEIGHT) * 0.5);
            drawingPane.setScaleX(scalingFactor);
            drawingPane.setScaleY(scalingFactor);
        }
    }

    private void addStyleSheets(Parent parent) {
//        String fonts = getClass().getResource(FONTS_CSS).toExternalForm();
    //    parent.getStylesheets().add(fonts);

        String stylesheet = getClass().getResource(STYLE_CSS).toExternalForm();
        parent.getStylesheets().add(stylesheet);
    }

    // compute sizes

    @Override
    protected double computeMinWidth(double height) {
        Insets padding           = getPadding();
        double horizontalPadding = padding.getLeft() + padding.getRight();

        return MINIMUM_WIDTH + horizontalPadding;
    }

    @Override
    protected double computeMinHeight(double width) {
        Insets padding         = getPadding();
        double verticalPadding = padding.getTop() + padding.getBottom();

        return MINIMUM_HEIGHT + verticalPadding;
    }

    @Override
    protected double computePrefWidth(double height) {
        Insets padding           = getPadding();
        double horizontalPadding = padding.getLeft() + padding.getRight();

        return ARTBOARD_WIDTH + horizontalPadding;
    }

    @Override
    protected double computePrefHeight(double width) {
        Insets padding         = getPadding();
        double verticalPadding = padding.getTop() + padding.getBottom();

        return ARTBOARD_HEIGHT + verticalPadding;
    }

    // getter and setter for all properties

    public Color getBaseColor() {
        return baseColor.get();
    }

    public StyleableObjectProperty<Color> baseColorProperty() {
        return baseColor;
    }

    public void setBaseColor(Color baseColor) {
        this.baseColor.set(baseColor);
    }

    public void setTimerIsRunning(boolean timerIsRunning) {
        this.timerIsRunning.set(timerIsRunning);
    }

    public Duration getPulse() {
        return pulse.get();
    }


    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }


    public double getMinValue() {
        return minValue.get();
    }

    public IntegerProperty minValueProperty() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue.set(minValue);
    }

    public double getMaxValue() {
        return maxValue.get();
    }

    public IntegerProperty maxValueProperty() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue.set(maxValue);
    }

    public double getValue() {
        return value.get();
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public double getAnimatedValue() {
        return animatedValue.get();
    }

    public IntegerProperty animatedValueProperty() {
        return animatedValue;
    }

    public void setAnimatedValue(int animatedValue) {
        this.animatedValue.set(animatedValue);
    }
}

package ch.fhnw.oop2.skiareasfx.view;

import ch.fhnw.oop2.skiareasfx.presentationmodel.RootPM;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;

public class Content extends StackPane implements ViewMixin {
    private Button stackPaneButton;

    public Content() {
        init();
    }

    @Override
    public void initializeControls() {
        stackPaneButton = new Button("StackPane");
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(stackPaneButton);
    }
}

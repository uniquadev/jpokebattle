package jpokebattle.gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import jpokebattle.JPokeBattle;
import jpokebattle.model.Pokemon;

public class HealthBar extends StackPane {
    Rectangle healthRect;

    private static final int RECT_HDELTA = 8;
    private static final int RECT_VDELTA = 10;

    public HealthBar() {
        super();

        setAlignment(Pos.CENTER_LEFT);
        setPrefHeight(25);
        setPrefWidth(208);

        healthRect = new Rectangle();
        healthRect.setWidth(getPrefWidth() - RECT_HDELTA);
        healthRect.setHeight(getPrefHeight() - RECT_VDELTA);
        healthRect.setFill(Paint.valueOf("#1fff81"));
        healthRect.setStrokeWidth(0);
        healthRect.setTranslateX(4);

        ImageView healthBar = new ImageView(
            new Image(
                JPokeBattle.class.getResource("resources/sprites/bar.png").toExternalForm()
            )
        );
        healthBar.setFitHeight(getPrefHeight() * 0.6);
        healthBar.setFitWidth(getPrefWidth());
    
        getChildren().addAll(healthRect, healthBar);
    }

    public void tweenHealthAndWait(Pokemon pokemon) {
        // TODO
    }

    public void setHealth(Pokemon pokemon) {
        
    }
    

}

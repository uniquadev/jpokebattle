package jpokebattle.gui;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import jpokebattle.JPokeBattle;
import jpokebattle.model.Pokemon;

public class HealthBar extends StackPane {
    private Rectangle healthRect;

    private static final int RECT_HDELTA = 8;
    private static final int RECT_VDELTA = 10;

    private Timeline tweenTimeline;

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

    private int calculateHealthWidth(Pokemon pokemon) {
        return (int) (pokemon.getHealth() * (getPrefWidth() - RECT_HDELTA) / pokemon.getTotPS());
    }

    private Paint getColorFromHealth(Pokemon pokemon) {
        double healthPercentage = (double) pokemon.getHealth() / pokemon.getTotPS();
        if (healthPercentage > 0.5)
            return Paint.valueOf("#1fff81");
        if (healthPercentage > 0.2)
            return Paint.valueOf("#eba834");
        return Paint.valueOf("#c91404");
    }

    public void updateColor(Pokemon pokemon) {
        healthRect.setFill(getColorFromHealth(pokemon));
    }

    public void tweenHealth(Pokemon pokemon, EventHandler<ActionEvent> onFinished) {
        int newWidth = calculateHealthWidth(pokemon);
        Duration cycleDuration = Duration.millis(1000);
        tweenTimeline = new Timeline(
            new KeyFrame(
                cycleDuration,
                new KeyValue(healthRect.widthProperty(),newWidth,Interpolator.EASE_BOTH)
            )
        );
        updateColor(pokemon);

        tweenTimeline.play();
        tweenTimeline.setOnFinished(event->{
            healthRect.setWidth(newWidth);
            if (onFinished != null)
                onFinished.handle(event);
        });
    }

    private void stopAnimation() {
        if (tweenTimeline != null)
            tweenTimeline.stop();
    }

    public void updateHealth(Pokemon pokemon) {
        stopAnimation();
        healthRect.setWidth(calculateHealthWidth(pokemon));
        updateColor(pokemon);
    }

}

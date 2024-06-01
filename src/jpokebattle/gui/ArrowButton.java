package jpokebattle.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jpokebattle.service.SoundService;

public class ArrowButton extends Button {
    private Image arrow;
    private ImageView imageView;

    public ArrowButton()
    {
        this(40, 40);
    }

    public ArrowButton(int width, int height)
    {
        super();

        this.getStyleClass().add("simple-button");
        arrow = new Image(
            getClass().getResourceAsStream("../resources/sprites/arrow.png"),
            width,
            height,
            false,
            false
        );
        imageView = new ImageView(arrow);

        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        addEventHandler(ActionEvent.ACTION, e -> {
            SoundService.getSingleton().playPress();
        });

        this.setGraphic(imageView);
    }

    public void resize(int width, int height)
    {
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }
}

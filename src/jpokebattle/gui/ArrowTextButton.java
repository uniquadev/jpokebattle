package jpokebattle.gui;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import jpokebattle.service.SoundService;

public class ArrowTextButton extends HBox {
    private Button button;
    private ImageView arrow;

    public ArrowTextButton() {
        this("Button", true);
    }

    public ArrowTextButton(String text, boolean enabled) {
        super();
        setSpacing(10);
        setAlignment(Pos.CENTER_LEFT);

        button = new Button(text);
        button.setDisable(!enabled);

        button.addEventHandler(ActionEvent.ACTION, e -> {
            SoundService.getSingleton().playPress();
        });

        Image arrowImage = new Image(
            getClass().getResourceAsStream("../resources/sprites/arrow.png"),
            30,
            30,
            false,
            false
        );
        arrow = new ImageView(arrowImage);
        arrow.getStyleClass().add("arrow");
        
        getChildren().addAll(arrow, button);
    }

    public void removeArrow()
    {
        getChildren().remove(arrow);
    }
    public void addArrow()
    {
        getChildren().clear();
        getChildren().addAll(arrow, button);
    }

    public Button getButton() {
        return button;
    }

    public void setText(String text) {
        button.setText(text);
    }

    public void showArrow(boolean show) {
        arrow.setVisible(show);
    }
}

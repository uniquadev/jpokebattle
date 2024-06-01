package jpokebattle.gui;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import jpokebattle.services.SoundService;

public class ArrowTextButton extends HBox {
    private Button button;
    private ArrowButton arrow;

    public ArrowTextButton(String text, boolean enabled) {
        super();
        setSpacing(10);
        setAlignment(Pos.CENTER_LEFT);

        button = new Button(text);
        button.getStyleClass().add("simple-button");
        button.setDisable(!enabled);

        button.addEventHandler(ActionEvent.ACTION, e -> {
            SoundService.getSingleton().playPress();
        });

        arrow = new ArrowButton(30, 30);
        arrow.setDisable(false);
        
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

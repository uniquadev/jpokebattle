package jpokebattle.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.TextAlignment;
import jpokebattle.JPokeBattle;
import jpokebattle.model.Pokemon;

public class PokeInfoCard extends VBox  {
    private Rectangle healthRect;
    private Label nameLabel;
    private Label hpLabel;
    private Pokemon pokemon;
    private HealthBar healthBar;

    public PokeInfoCard() {
        super();
        setFillWidth(false);
        setPrefHeight(81.0);
        setPrefWidth(260.0);
        setSpacing(5.0);
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        nameLabel = new Label();
        nameLabel.setText("Bulbasaur");
        nameLabel.getStyleClass().add("name-label");
        Label level = new Label();
        level.setText(":L1");
        level.setAlignment(Pos.TOP_RIGHT);
        level.setTextAlignment(TextAlignment.RIGHT);
        level.setPrefWidth(getPrefWidth() * 0.3);
        level.setPrefHeight(17.0);
        level.getStyleClass().add("level-label");
        hpLabel = new Label();
        hpLabel.setText("20/20");
        hpLabel.setAlignment(Pos.CENTER);
        hpLabel.setTextAlignment(TextAlignment.CENTER);
        hpLabel.setPrefWidth(getPrefWidth() * 0.9);
        hpLabel.getStyleClass().add("hp-label");

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefHeight(25.0);
        hbox.setPrefWidth(getPrefWidth());

        getChildren().addAll(nameLabel, level, hbox, hpLabel);

        Label health = new Label("HP: ");
        healthBar = new HealthBar();

        hbox.getChildren().addAll(health, healthBar);
        getStyleClass().add("poke-info-card");
    }

    public void update(Pokemon pokemon) {
        this.pokemon = pokemon;
        nameLabel.setText(pokemon.getName());
        hpLabel.setText(pokemon.getHealth() + "/" + pokemon.getTotPS());
    }

    public void removeHpLabel() {
        getChildren().remove(hpLabel);
        nameLabel.setTranslateX(-20);
    }
}

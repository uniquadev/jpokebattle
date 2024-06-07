package jpokebattle.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import jpokebattle.interfaces.PokeCallback;
import jpokebattle.model.Pokemon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PokeInfoCard extends VBox  {
    private Label nameLabel;
    private Label hpLabel;
    private Label leveLabel;
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
        leveLabel = new Label();
        leveLabel.setText(":L1");
        leveLabel.setAlignment(Pos.TOP_RIGHT);
        leveLabel.setTextAlignment(TextAlignment.RIGHT);
        leveLabel.setPrefWidth(getPrefWidth() * 0.3);
        leveLabel.setPrefHeight(17.0);
        leveLabel.getStyleClass().add("level-label");
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

        getChildren().addAll(nameLabel, leveLabel, hbox, hpLabel);

        Label health = new Label("HP: ");
        healthBar = new HealthBar();

        hbox.getChildren().addAll(health, healthBar);
        getStyleClass().add("poke-info-card");
    }

    public void update(Pokemon pokemon) {
        this.pokemon = pokemon;
        healthBar.updateHealth(pokemon);
        nameLabel.setText(pokemon.getName());
        hpLabel.setText(pokemon.getHealth() + "/" + pokemon.getTotPS());
        leveLabel.setText(":L" + pokemon.getLevel());
    }

    public void updateHealthLabel() {
        hpLabel.setText(pokemon.getHealth() + "/" + pokemon.getTotPS());
    }

    public void tweenHealth(PokeCallback onFinished) {
        healthBar.tweenHealth(pokemon, onFinished);
        updateHealthLabel();
    }

    public void updateHealth() {
        healthBar.updateHealth(pokemon);
        updateHealthLabel();
    }

    public void removeHpLabel() {
        getChildren().remove(hpLabel);
        nameLabel.setTranslateX(-20);
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }
}

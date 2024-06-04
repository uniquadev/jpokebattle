package jpokebattle.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import jpokebattle.JPokeBattle;
import jpokebattle.model.Pokemon;

public class PokeCard extends StackPane {
    private PokemonView pokemonView;
    private Label pokemonName;
    private Label levelLabel;
    private Label psLabel;
    private ImageView arrow;
    private HealthBar healthBar;

    public PokeCard() {
        super();
        setMaxHeight(USE_PREF_SIZE);
        setPrefHeight(60.0);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setSpacing(25.0);
        Image arrowImage = new Image(
            JPokeBattle.class.getResource("resources/sprites/arrow.png").toExternalForm(),
            40, 40,
            false, false
        );
        arrow = new ImageView(arrowImage);
        arrow.setFitHeight(23.0);
        arrow.setFitWidth(23.0);
        arrow.setTranslateX(5.0);
        arrow.setVisible(false);

        pokemonView = new PokemonView();
        pokemonView.setFitHeight(59.0);
        pokemonView.setFitWidth(59.0);

        VBox infoBox = new VBox();
        infoBox.setSpacing(5.0);
        infoBox.setPadding(new Insets(5.0, 0.0, 5.0, 0.0));

        pokemonName = new Label("Sterbinos");
        pokemonName.getStyleClass().add("name-label");

        HBox healthBox = new HBox();
        healthBox.setAlignment(Pos.CENTER_LEFT);
        Label hpLabel = new Label("HP: ");
        hpLabel.getStyleClass().add("hp-label");
        healthBar = new HealthBar();
        healthBox.getChildren().addAll(hpLabel, healthBar);

        infoBox.getChildren().addAll(pokemonName, healthBox);
        hbox.getChildren().addAll(arrow, pokemonView, infoBox);

        VBox infoBox2 = new VBox();
        infoBox2.setAlignment(Pos.TOP_RIGHT);
        infoBox2.setPadding(new Insets(5.0, 20.0, 5.0, 0.0));
        infoBox2.setSpacing(5.0);

        levelLabel = new Label("L1");
        levelLabel.getStyleClass().add("level-label");
        psLabel = new Label("20/20");
        psLabel.getStyleClass().add("ps-label");
        infoBox2.getChildren().addAll(levelLabel, psLabel);

        getChildren().addAll(hbox, infoBox2);
        getStyleClass().add("poke-card");

        setAlignment(infoBox2, Pos.CENTER_RIGHT);
    }

    public void toggleArrow(boolean flag)
    {
        arrow.setVisible(flag);
    }

    public void update(Pokemon pokemon) {
        pokemonView.setId(pokemon.getId());
        pokemonName.setText(pokemon.getName());
        healthBar.setHealth(pokemon);
        levelLabel.setText("L" + pokemon.getLevel());
        psLabel.setText(pokemon.getHealth() + "/" + pokemon.getTotPS());
    }
}

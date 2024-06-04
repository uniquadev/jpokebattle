package jpokebattle.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import jpokebattle.JPokeBattle;
import jpokebattle.model.Pokemon;

public class PokeCard extends StackPane {
    private PokemonView pokemonView;
    private Label pokemonName;

    public PokeCard() {
        super();
        setMaxHeight(USE_PREF_SIZE);
        setPrefHeight(86.0);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setSpacing(5.0);
        Image arrowImage = new Image(
            JPokeBattle.class.getResource("resources/sprites/arrow.png").toExternalForm(),
            40, 40,
            false, false
        );
        ImageView arrow = new ImageView(arrowImage);
        arrow.setFitHeight(40.0);
        arrow.setFitWidth(40.0);
        arrow.setTranslateX(5.0);

        pokemonView = new PokemonView();
        pokemonView.setFitHeight(56.0);
        pokemonView.setFitWidth(56.0);

        VBox infoBox = new VBox();
        infoBox.setSpacing(5.0);
        infoBox.setPadding(new Insets(15.0, 0.0, 15.0, 0.0));

        pokemonName = new Label("Sterbinos");
        pokemonName.getStyleClass().add("name-label");

        
        Label hpLabel = new Label("HP: ");
        hpLabel.getStyleClass().add("hp-label");

        infoBox.getChildren().addAll(pokemonName, hpLabel);
        hbox.getChildren().addAll(arrow, pokemonView, infoBox);

        getChildren().addAll(hbox);
        getStyleClass().add("poke-card");
    }

    public void toggleArrow(boolean flag)
    {

    }

    public void update(Pokemon pokemon) {
        pokemonView.setId(pokemon.getId());
        pokemonName.setText(pokemon.getName());
    }
}

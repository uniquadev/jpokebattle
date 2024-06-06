package jpokebattle.gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import jpokebattle.JPokeBattle;
import jpokebattle.model.Trainer;

public class HPokeBalls extends HBox {
    public HPokeBalls() {
        super();
        setMaxHeight(USE_PREF_SIZE);
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(0);
    }

    public ImageView getPokeball() {
        Image image = new Image(
            JPokeBattle.class.getResourceAsStream("resources/sprites/pokeball.png"),
            256, 256, false, false
        );
        ImageView pokeball = new ImageView(image);
        pokeball.setFitHeight(28);
        pokeball.setFitWidth(28);
        return pokeball;
    
    }

    public void update(Trainer trainer) {
        getChildren().clear();
        var availablePokemons = trainer.getAvailablePokemons();
        for (int i = 0; i < availablePokemons.size(); i++) {
            getChildren().add(getPokeball());
        }
        int remaining = 6 - availablePokemons.size();
        for (int i = 0; i < remaining; i++) {
            var pokeView = getPokeball();
            pokeView.setOpacity(0.5);
            getChildren().add(pokeView);
        }
    }
}

package jpokebattle.gui;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;

public class PokeTeamList extends VBox {
    public PokeTeamList() {
        super();

        setFillWidth(true);
    }

    public void update(Trainer trainer) {
        getChildren().clear();
        Pokemon[] pokemons = trainer.getPokemons();
        for (int i = 0; i < 6; i++) {
            Pokemon pokemon = pokemons[i];
            if (pokemon == null)
                continue;
            PokeCard card = new PokeCard();
            card.update(pokemon);
            getChildren().add(card);
        }
    }
}

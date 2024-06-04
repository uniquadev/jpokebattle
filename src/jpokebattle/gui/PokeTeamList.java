package jpokebattle.gui;

import javafx.scene.layout.VBox;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;

public class PokeTeamList extends VBox {
    public PokeTeamList() {
        super();
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

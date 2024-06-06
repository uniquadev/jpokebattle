package jpokebattle.gui;

import javafx.scene.layout.VBox;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;
import jpokebattle.service.SoundService;

public class PokeTeamList extends VBox {

    static public interface ClickCallback {
        public void onClick(int slot, Pokemon option);
    }

    private PokeCard currentCard;
    private ClickCallback callback;

    public PokeTeamList() {
        super();
        setFillWidth(true);
    }

    public void update(Trainer trainer) {
        getChildren().clear();
        currentCard = null;
        Pokemon[] pokemons = trainer.getPokemons();
        for (int i = 0; i < 6; i++) {
            Pokemon pokemon = pokemons[i];
            if (pokemon == null)
                continue;
            PokeCard card = new PokeCard();
            card.update(pokemon);
            getChildren().add(card);
            if (currentCard == null)
                currentCard = card;
            card.setOnMouseEntered(e -> {
                toggleCurrentCardArrow(false);
                currentCard = card;
                currentCard.toggleArrow(true);
            });
            final int slot = i;
            card.setOnMouseClicked(e -> {
                SoundService.getSingleton().playPress();
                if (callback != null)
                    callback.onClick(slot, pokemon);
            });
        }
        toggleCurrentCardArrow(true);
    }

    private void toggleCurrentCardArrow(boolean visible)
    {
        if (currentCard != null)
            currentCard.toggleArrow(visible);
    }

    public void setCallback(ClickCallback callback) {
        this.callback = callback;
    }
}

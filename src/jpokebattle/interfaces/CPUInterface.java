package jpokebattle.interfaces;

import jpokebattle.model.AIAction;
import jpokebattle.model.Pokemon;

public interface CPUInterface {
    public Pokemon handlePokemonChange();
    public AIAction handleTurn();
}

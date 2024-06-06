package jpokebattle.interfaces;

import jpokebattle.model.CPUAction;
import jpokebattle.model.Pokemon;

public interface CPUInterface {
    public Pokemon handlePokemonChange();
    public CPUAction handleTurn();
}

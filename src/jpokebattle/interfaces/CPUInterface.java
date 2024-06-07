package jpokebattle.interfaces;

import jpokebattle.model.BattleAction;
import jpokebattle.model.Pokemon;

public interface CPUInterface {
    public Pokemon handlePokemonChange();
    public BattleAction handleTurn();
}

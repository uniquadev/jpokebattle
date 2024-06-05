package jpokebattle.moves;

import jpokebattle.controller.BattleController;
import jpokebattle.model.AttackData;
import jpokebattle.model.Pokemon;
import jpokebattle.utils.MoveType;
import jpokebattle.utils.PokeElement;

public interface MoveInterface {
    public String getName();
    public PokeElement getElement();
    public MoveType getType();
    public int getRequiredLevel();
    public int getMaxUses();
    public int getPower();
    public int getAccuracy();
    
    public AttackData perform(Pokemon attacker, Pokemon defender);
    public void draw(BattleController bc, Pokemon attacker, Pokemon defender);
}

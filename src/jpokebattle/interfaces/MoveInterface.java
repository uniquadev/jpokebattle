package jpokebattle.interfaces;

import jpokebattle.controller.BattleController;
import jpokebattle.enums.MoveType;
import jpokebattle.enums.PokeElement;
import jpokebattle.model.AttackData;
import jpokebattle.model.Pokemon;

public interface MoveInterface {
    public String getName();
    public PokeElement getElement();
    public MoveType getType();
    public int getMaxUses();
    public int getPower();
    public int getAccuracy();
    public int getRequiredLevel();
    
    public AttackData perform(Pokemon attacker, Pokemon defender);
    public void draw(BattleController bc, Pokemon attacker, Pokemon defender);
}

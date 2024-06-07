package jpokebattle.interfaces;

import jpokebattle.controller.BattleController;
import jpokebattle.enums.MoveType;
import jpokebattle.enums.PokeElement;
import jpokebattle.model.AttackResult;
import jpokebattle.model.Pokemon;

public interface MoveInterface {
    public String getName();
    public PokeElement getElement();
    public MoveType getType();
    public int getMaxUses();
    public int getPower();
    public int getAccuracy();
    public int getRequiredLevel();
    
    public AttackResult perform(Pokemon attacker, Pokemon defender);
    public void draw(BattleController bc, Pokemon attacker, Pokemon defender);
}

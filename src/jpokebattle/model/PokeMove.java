package jpokebattle.model;

import jpokebattle.controller.BattleController;
import jpokebattle.enums.MoveType;
import jpokebattle.enums.PokeElement;
import jpokebattle.interfaces.MoveInterface;
import jpokebattle.move.BaseMove;

public class PokeMove implements MoveInterface {
    private BaseMove move;
    private int usesLeft = 0;
    private int slot = 0;

    public PokeMove(BaseMove move) {
        this.move = move;
        this.usesLeft = getMaxUses();
    }

    public BaseMove getMove() {
        return move;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
    public int getSlot() {
        return slot;
    }

    public int getUsesLeft() {
        return usesLeft;
    }
    public void consumeUses() {
        usesLeft--;
    }
    public void resetUses() {
        usesLeft = getMaxUses();
    }
    public boolean canUse() {
        return usesLeft > 0;
    }
    @Override
    public int getRequiredLevel() {
        return move.getRequiredLevel();
    }
    @Override
    public String getName() {
        return move.getName();
    }
    @Override
    public PokeElement getElement() {
        return move.getElement();
    }
    @Override
    public MoveType getType() {
        return move.getType();
    }
    @Override
    public int getMaxUses() {
        return move.getMaxUses();
    }
    @Override
    public int getPower() {
        return move.getPower();
    }
    @Override
    public int getAccuracy() {
        return move.getAccuracy();
    }
    @Override
    public MoveResult perform(Pokemon attacker, Pokemon defender) {
        return move.perform(attacker, defender);
    }
    @Override
    public void draw(BattleController bc, Pokemon attacker, Pokemon defender) {
        move.draw(bc, attacker, defender);
    }
    
}

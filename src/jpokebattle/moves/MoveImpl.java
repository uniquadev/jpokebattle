package jpokebattle.moves;

import jpokebattle.controller.BattleController;
import jpokebattle.model.Pokemon;

public interface MoveImpl {
    public void perform(BattleController bc, Pokemon attacker, Pokemon defender);
    public void draw(BattleController bc, Pokemon attacker, Pokemon defender);
}

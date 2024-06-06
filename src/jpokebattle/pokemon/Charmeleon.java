package jpokebattle.pokemon;

import jpokebattle.utils.PokeElement;
import jpokebattle.utils.PokeEnum;

public class Charmeleon extends PokemonInfo {
        
    public Charmeleon() {
        baseStats.setPS(58);
        baseStats.setAttack(64);
        baseStats.setDefense(58);
        baseStats.setSpecial(80);
        baseStats.setSpeed(80);
    }

    @Override
    public String getName() {
        return "Charmeleon";
    }

    @Override
    public PokeEnum getId() {
        return PokeEnum.CHARMELEON;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.FIRE;
    }

    @Override
    public int getCurrentEvolution() {
        return 1;
    }

    @Override
    public int getMaxEvolution() {
        return 2;
    }

    @Override
    public int getDropExp() {
        return 142;
    }
}

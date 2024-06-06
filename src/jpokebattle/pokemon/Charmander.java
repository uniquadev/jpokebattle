package jpokebattle.pokemon;

import jpokebattle.utils.PokeElement;
import jpokebattle.utils.PokeEnum;

public class Charmander extends PokemonInfo {
        
    public Charmander() {
        baseStats.setPS(39);
        baseStats.setAttack(52);
        baseStats.setDefense(43);
        baseStats.setSpecial(60);
        baseStats.setSpeed(65);
    }

    @Override
    public String getName() {
        return "Charmander";
    }

    @Override
    public PokeEnum getId() {
        return PokeEnum.CHARMANDER;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.FIRE;
    }

    @Override
    public int getCurrentEvolution() {
        return 0;
    }

    @Override
    public int getMaxEvolution() {
        return 2;
    }

    @Override
    public int getDropExp() {
        return 62;
    }
}

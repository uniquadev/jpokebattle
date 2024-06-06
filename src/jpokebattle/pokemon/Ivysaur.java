package jpokebattle.pokemon;

import jpokebattle.utils.PokeElement;
import jpokebattle.utils.PokeEnum;

public class Ivysaur extends PokemonInfo {
    
    public Ivysaur() {
        baseStats.setPS(60);
        baseStats.setAttack(62);
        baseStats.setDefense(63);
        baseStats.setSpecial(80);
        baseStats.setSpeed(60);
    }

    @Override
    public String getName() {
        return "Ivysaur";
    }

    @Override
    public PokeEnum getId() {
        return PokeEnum.IVYSAUR;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.GRASS;
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

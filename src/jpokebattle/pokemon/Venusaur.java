package jpokebattle.pokemon;

import jpokebattle.utils.PokeElement;
import jpokebattle.utils.PokeEnum;

public class Venusaur extends PokemonInfo {
        
    public Venusaur() {
        baseStats.setPS(80);
        baseStats.setAttack(82);
        baseStats.setDefense(83);
        baseStats.setSpecial(100);
        baseStats.setSpeed(80);
    }

    @Override
    public String getName() {
        return "Venusaur";
    }

    @Override
    public PokeEnum getId() {
        return PokeEnum.VENUSAUR;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.GRASS;
    }

    @Override
    public int getCurrentEvolution() {
        return 2;
    }

    @Override
    public int getMaxEvolution() {
        return 2;
    }

    @Override
    public int getDropExp() {
        return 263;
    }
}

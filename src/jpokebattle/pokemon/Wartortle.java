package jpokebattle.pokemon;

import jpokebattle.enums.PokeElement;
import jpokebattle.enums.PokeEnum;

public class Wartortle extends PokemonInfo {
        
    public Wartortle() {
        baseStats.setPS(59);
        baseStats.setAttack(63);
        baseStats.setDefense(80);
        baseStats.setSpecial(65);
        baseStats.setSpeed(58);
    }

    @Override
    public String getName() {
        return "Wartortle";
    }

    @Override
    public PokeEnum getId() {
        return PokeEnum.WARTORTLE;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.WATER;
    }

    @Override
    public int getCurrentEvolution() {
        return 1;
    }

    @Override
    public int getMaxEvolution() {
        return 3;
    }

    @Override
    public int getDropExp() {
        return 142;
    }

    @Override
    public int evolveAtLevel() {
        return 36;
    }
}

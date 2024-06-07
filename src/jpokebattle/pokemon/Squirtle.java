package jpokebattle.pokemon;

import jpokebattle.enums.PokeElement;
import jpokebattle.enums.PokeEnum;

public class Squirtle extends PokemonInfo {
        
    public Squirtle() {
        baseStats.setPS(44);
        baseStats.setAttack(48);
        baseStats.setDefense(65);
        baseStats.setSpecial(50);
        baseStats.setSpeed(43);
    }

    @Override
    public String getName() {
        return "Squirtle";
    }

    @Override
    public PokeEnum getId() {
        return PokeEnum.SQUIRTLE;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.WATER;
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
        return 63;
    }

    @Override
    public int evolveAtLevel() {
        return 16;
    }
}

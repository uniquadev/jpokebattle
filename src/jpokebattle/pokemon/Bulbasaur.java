package jpokebattle.pokemon;

import jpokebattle.enums.PokeElement;
import jpokebattle.enums.PokeEnum;

public class Bulbasaur extends PokemonInfo {

    public Bulbasaur() {
        baseStats.setPS(45);
        baseStats.setAttack(49);
        baseStats.setDefense(49);
        baseStats.setSpecial(65);
        baseStats.setSpeed(45);
    }

    @Override
    public String getName() {
        return "Bulbasaur";
    }

    @Override
    public PokeEnum getId() {
        return PokeEnum.BULBASAUR;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.GRASS;
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
        return 64;
    }

    @Override
    public int evolveAtLevel() {
        return 16;
    }
}

package jpokebattle.pokemon;

import jpokebattle.utils.PokeElement;
import jpokebattle.utils.PokeEnum;

public class Blastoise extends PokemonInfo {
    public Blastoise() {
        baseStats.setPS(79);
        baseStats.setAttack(83);
        baseStats.setDefense(100);
        baseStats.setSpecial(85);
        baseStats.setSpeed(78);
    }

    @Override
    public String getName() {
        return "Blastoise";
    }

    @Override
    public PokeEnum getId() {
        return PokeEnum.BLASTOISE;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.WATER;
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
        return 265;
    }
}

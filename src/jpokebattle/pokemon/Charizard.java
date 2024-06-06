package jpokebattle.pokemon;

import jpokebattle.utils.PokeElement;
import jpokebattle.utils.PokeEnum;

public class Charizard extends PokemonInfo {
            
    public Charizard() {
        baseStats.setPS(78);
        baseStats.setAttack(84);
        baseStats.setDefense(78);
        baseStats.setSpecial(109);
        baseStats.setSpeed(100);
    }

    @Override
    public String getName() {
        return "Charizard";
    }

    @Override
    public PokeEnum getId() {
        return PokeEnum.CHARIZARD;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.FIRE;
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
        return 267;
    }
}

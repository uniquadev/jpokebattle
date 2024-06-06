package jpokebattle.pokemon;

import jpokebattle.interfaces.PokemonInterface;
import jpokebattle.interfaces.StatsInterface;
import jpokebattle.model.PokeStats;
import jpokebattle.utils.LevelUpGroup;

public abstract class PokemonInfo implements PokemonInterface, StatsInterface {

    protected PokeStats baseStats = new PokeStats();

    public LevelUpGroup getLevelType() {
        return LevelUpGroup.MEDIUM_SLOW;
    }

    @Override
    public int getPS() {
        return baseStats.getPS();
    }

    @Override
    public int getAttack() {
        return baseStats.getAttack();
    }

    @Override
    public int getDefense() {
        return baseStats.getDefense();
    }

    @Override
    public int getSpecial() {
        return baseStats.getSpecial();
    }

    @Override
    public int getSpeed() {
        return baseStats.getSpeed();
    }

    public int getLeveRequiredExp(int level) {
        int result = 0;
        switch (getLevelType()) {
            case MEDIUM_FAST:
                result = (int) Math.pow(level, 3);
                break;
            case MEDIUM_SLOW:
                result = (int) (6/5*Math.pow(level, 3) - 15*Math.pow(level, 2) + 100*level - 140);
                break;
            case FAST:
                result = (int) (4 * Math.pow(level, 3) / 5);
                break;
            case SLOW:
                result = (int) (5 * Math.pow(level, 3) / 5);
                break;
        }
        return Math.max(0, result);
    }

    @Override
    public boolean canEvolve() {
        return getCurrentEvolution() < getMaxEvolution();
    }

}

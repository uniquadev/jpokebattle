package jpokebattle.pokemon;

import jpokebattle.enums.LevelUpGroup;
import jpokebattle.interfaces.PokemonInterface;
import jpokebattle.interfaces.StatsInterface;
import jpokebattle.model.PokeStats;

public abstract class PokemonInfo implements PokemonInterface, StatsInterface {

    protected PokeStats baseStats = new PokeStats();

    public LevelUpGroup getLevelType() {
        return LevelUpGroup.MEDIUM_SLOW;
    }

    public int getDropExp() {
        return 0;
    }

    public int getPS() {
        return baseStats.getPS();
    }
    public int getAttack() {
        return baseStats.getAttack();
    }
    public int getDefense() {
        return baseStats.getDefense();
    }
    public int getSpecial() {
        return baseStats.getSpecial();
    }
    public int getSpeed() {
        return baseStats.getSpeed();
    }
    public boolean canEvolve() {
        return getCurrentEvolution() < getMaxEvolution();
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
}

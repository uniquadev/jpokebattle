package jpokebattle.utils;

import jpokebattle.model.Pokemon;

public class PokeBuilder {
    private int minLevel;
    private int maxLevel;
    private int minHp;
    private int maxHp;
    private int minAttack;
    private int maxAttack;
    private int minDefense;
    private int maxDefense;
    private int minSpeed;
    private int maxSpeed;
    private int minSpecial;
    private int maxSpecial;
    private int minAccuracy;
    private int maxAccuracy;
    private int minEvasion;
    private int maxEvasion;
    private int minExp;
    private int maxExp;
    private int minExpToNextLevel;
    private int maxExpToNextLevel;
    private PokeEnum id;

    public void setId(PokeEnum id) {
        this.id = id;
    }
    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }
    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Pokemon generate()
    {
        // TODO: implement
        return null;
    }
}

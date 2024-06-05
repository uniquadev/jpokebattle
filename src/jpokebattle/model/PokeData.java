package jpokebattle.model;

import jpokebattle.utils.PokeElement;
import jpokebattle.utils.PokeEnum;

public class PokeData {

    public enum LevelGroup {
        MEDIUM_FAST,
        MEDIUM_SLOW,
        FAST,
        SLOW
    }

    private PokeEnum id;
    private String name;
    private PokeElement element;

    private int ps;
    private int attack;
    private int defense;
    private int special;
    private int speed;
    
    private LevelGroup levelType;
    private int exp;

    private boolean isFemale;
    private boolean isLegendary;

    private int currentEvolution = 0;
    private int maxEvolution = 0;

    public int getCurrentEvolution(){
        return currentEvolution;
    }
    public int getMaxEvolution(){
        return maxEvolution;
    }

    public PokeData(
        PokeEnum id, String name, PokeElement element, 
        int ps, int attack, int defense, int special, int speed,
        LevelGroup levelType, int exp,
        int currentEvolution, int maxEvolution,
        boolean isFemale, boolean isLegendary) {
        this.id = id;
        this.name = name;
        this.element = element;
        this.ps = ps;
        this.attack = attack;
        this.defense = defense;
        this.special = special;
        this.speed = speed;
        this.levelType = levelType;
        this.exp = exp;
        this.currentEvolution = currentEvolution;
        this.maxEvolution = maxEvolution;
        this.isFemale = isFemale;
        this.isLegendary = isLegendary;
    }

    public int getLeveRequiredExp(int level) {
        int result = 0;
        switch (levelType) {
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
    public int getBaseExp() {
        return exp;
    }
    
    public int getPs() {
        return ps;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecial() {
        return special;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public String getName() {
        return name;
    }

    public PokeEnum getId() {
        return id;
    }

    public PokeElement getElement() {
        return element;
    }
}

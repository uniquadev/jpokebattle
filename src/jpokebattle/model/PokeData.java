package jpokebattle.model;

import jpokebattle.utils.PokeElement;
import jpokebattle.utils.PokeEnum;

public class PokeData {
    private PokeEnum id;
    private String name;
    private PokeElement element;

    private int ps;
    private int attack;
    private int defense;
    private int special;
    private int speed;

    private boolean isFemale;
    private boolean isLegendary;

    public PokeData(
        PokeEnum id, String name, PokeElement element, 
        int ps, int attack, int defense, int special, int speed,
        boolean isFemale, boolean isLegendary) {
        this.id = id;
        this.name = name;
        this.element = element;
        this.ps = ps;
        this.attack = attack;
        this.defense = defense;
        this.special = special;
        this.speed = speed;
        this.isFemale = isFemale;
        this.isLegendary = isLegendary;
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

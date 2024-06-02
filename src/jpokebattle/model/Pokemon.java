package jpokebattle.model;

import jpokebattle.moves.BaseMove;

public class Pokemon {
    private BaseMove[] moves = new BaseMove[4];
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private int speed;
    private int level;
    private int exp;
    private int expToNextLevel;
    private int maxHp;
    private int maxAttack;
    private int maxDefense;
    private int maxSpeed;
    private int maxLevel;
    private int maxExp;
    private int maxExpToNextLevel;
    
    // Dati base del pokemon
    private PokeData data;

    public PokeData getData() {
        return data;
    }
    public String getName() {
        return name;
    }
}

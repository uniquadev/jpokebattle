package jpokebattle.model;

import jpokebattle.moves.BaseMove;

public class Pokemon {
    private BaseMove[] moves = new BaseMove[4];
    private String name;
    private int health = 0;

    private int ps = 0;
    private int psPA = 0;

    private int attack = 0;
    private int attackPA = 0;

    private int defense = 0;
    private int defensePA = 0;

    private int special = 0;
    private int specialPA = 0;

    private int speed = 0;
    private int speedPA = 0;

    private int level = 0;
    private int exp = 0;
    private int expToNextLevel = 0;
    
    // Dati base del pokemon
    private PokeData data;

    public PokeData getData() {
        return data;
    }
    public void setData(PokeData data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void restoreHealth() {
        this.health = getTotPS();
    }

    public void damage(int damage) {
        this.health -= damage;
        this.health = Math.max(0, this.health);
    }
    public void heal(int heal) {
        this.health += heal;
        this.health = Math.min(getTotPS(), this.health);
    }

    public int getPS() {
        return ps;
    }
    public int getTotPS() {
        return ps + (psPA / 4) + data.getPs();
    }
    public void setPS(int ps) {
        this.ps = ps;
    }

    public int getAttack() {
        return attack;
    }
    public int getTotAttack() {
        return attack + (attackPA / 4) + data.getAttack();
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }
    public int getTotDefense() {
        return defense + (defensePA / 4) + data.getDefense();
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecial() {
        return special;
    }
    public int getTotSpecial() {
        return special + (specialPA / 4) + data.getSpecial();
    }
    public void setSpecial(int special) {
        this.special = special;
    }

    public int getSpeed() {
        return speed;
    }
    public int getTotSpeed() {
        return speed + (speedPA / 4) + data.getSpeed();
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getExpToNextLevel() {
        return expToNextLevel;
    }
    public void setExpToNextLevel(int expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    public BaseMove[] getMoves() {
        return moves;
    }
    public void setMoves(BaseMove[] moves) {
        this.moves = moves;
    }
    public void setMove(int index, BaseMove move) {
        this.moves[index] = move;
    }


}

package jpokebattle.model;

public class PokeStats {
    private int attack = 0;
    private int defense = 0;
    private int special = 0;
    private int speed = 0;
    private int ps = 0;

    private int maxValue;
    public PokeStats() {
        this(999);
    }
    public PokeStats(int maxValue) {
        this.maxValue = maxValue;
    }

    public void reset() {
        attack = 0;
        defense = 0;
        special = 0;
        speed = 0;
        ps = 0;
    }

    public int getPs() {
        return ps;
    }
    public void setPS(int ps) {
        this.ps = Math.min(maxValue, ps);
    }

    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = Math.min(maxValue, attack);
    }

    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = Math.min(maxValue, defense);
    }

    public int getSpecial() {
        return special;
    }
    public void setSpecial(int special) {
        this.special = Math.min(maxValue, special);
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = Math.min(maxValue, speed);
    }
    
    public void sum(PokeStats other) {
        setAttack(attack + other.attack);
        setDefense(defense + other.defense);
        setSpecial(special + other.special);
        setSpeed(speed + other.speed);
        setPS(ps + other.ps);
    }
}

package jpokebattle.model;

public class PokeStats {
    private int attack = 0;
    private int defense = 0;
    private int special = 0;
    private int speed = 0;
    private int ps = 0;

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
        this.ps = ps;
    }

    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecial() {
        return special;
    }
    public void setSpecial(int special) {
        this.special = special;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void sum(PokeStats other) {
        this.attack += other.attack;
        this.defense += other.defense;
        this.special += other.special;
        this.speed += other.speed;
        this.ps += other.ps;
    }
}

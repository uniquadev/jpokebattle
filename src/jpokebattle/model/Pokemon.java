package jpokebattle.model;

import java.util.ArrayList;

import jpokebattle.moves.BaseMove;
import jpokebattle.utils.PokeElement;
import jpokebattle.utils.PokeEnum;

public class Pokemon {
    private PokeMove[] moves = new PokeMove[4];
    private int health = 0;

    // statistiche personali (iv)
    private PokeStats iv = new PokeStats();
    private PokeStats effects = new PokeStats();

    private int psPA = 0;
    private int attackEV = 0;
    private int defenseEV = 0;
    private int specialEV = 0;
    private int speedEV = 0;
    
    private int level = 0;
    private int exp = 0;

    // dati base del pokemon
    private PokeData data;

    public int getExp() {
        return exp;
    }

    public PokeData getData() {
        return data;
    }
    public PokeEnum getId() {
        return data.getId();
    }
    public void setData(PokeData data) {
        this.data = data;
    }

    public String getName() {
        return data.getName();
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
    public boolean isDead() {
        return health <= 0;
    }

    public void damage(int damage) {
        this.health -= damage;
        this.health = Math.max(0, this.health);
    }
    public void heal(int heal) {
        this.health += heal;
        this.health = Math.min(getTotPS(), this.health);
    }

    private int calculateStat(int base, int iv, int pa) {
        return (((base + iv) * 2 + pa/4) * level) / 100 + 5;
    }

    public int getPS() {
        return iv.getPs();
    }
    public int getTotPS() {
        return calculateStat(data.getPs(), iv.getPs(), psPA) + level + 5;
    }
    public void setPS(int ps) {
        iv.setPS(ps);
    }

    public int getAttack() {
        return iv.getAttack();
    }
    public int getTotAttack() {
        return calculateStat(data.getAttack(), iv.getAttack(), attackEV);
    }
    public void setAttack(int attack) {
        iv.setAttack(attack);
    }

    public int getDefense() {
        return iv.getDefense();
    }
    public int getTotDefense() {
        return calculateStat(data.getDefense(), iv.getDefense(), defenseEV);
    }
    public void setDefense(int defense) {
        iv.setDefense(defense);
    }

    public int getSpecial() {
        return iv.getSpecial();
    }
    public int getTotSpecial() {
        return calculateStat(data.getSpecial(), iv.getSpecial(), specialEV);
    }
    public void setSpecial(int special) {
        iv.setSpecial(special);
    }

    public int getSpeed() {
        return iv.getSpeed();
    }
    public int getTotSpeed() {
        return calculateStat(data.getSpeed(), iv.getSpeed(), speedEV);
    }
    public void setSpeed(int speed) {
        iv.setSpeed(speed);
    }

    public PokeStats getEffects() {
        return effects;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
        this.exp = data.getLeveRequiredExp(level); // imposta l'exp necessaria per il livello attuale
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getExpToNextLevel() {
        return data.getLeveRequiredExp(level + 1) - exp;
    }
    public boolean canLevelUp() {
        return exp >= data.getLeveRequiredExp(level + 1);
    }

    // s = numero di pokemon che hanno partecipato alla battaglia
    public void gainExp(Pokemon enemy, int s) {
        int b = enemy.getData().getBaseExp();
        int L = enemy.getLevel();
        double a = 1.5;
        Double res = (b*L)/7 * 1/s * a;
        exp += res.intValue();
    }

    public void levelUp() {
        level++;
    }

    public ArrayList<PokeMove> getMoves() {
        ArrayList<PokeMove> result = new ArrayList<>();
        for (PokeMove move : moves) {
            if (move != null)
                result.add(move);
        }
        return result;
    }
    public void setMoves(PokeMove[] moves) {
        this.moves = moves;
    }
    public PokeMove getMove(int index) {
        return moves[index];
    }
    public void setMove(int index, PokeMove move) {
        this.moves[index] = move;
        move.setSlot(index);
    }
    public int getAvailableMoves() {
        int count = 0;
        for (PokeMove move : moves) {
            if (move != null)
                count++;
        }
        return count;
    }


    public PokeElement getElement() {
        return data.getElement();
    }

    public boolean hasEvolution() {
        return data.getCurrentEvolution() < data.getMaxEvolution();
    }

    public void restore() {
        restoreHealth();
        effects.reset();
        for (PokeMove move : getMoves())
            move.resetUses();
    }
}

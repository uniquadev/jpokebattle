package jpokebattle.model;

import java.util.ArrayList;

import jpokebattle.moves.BaseMove;
import jpokebattle.utils.PokeElement;
import jpokebattle.utils.PokeEnum;

public class Pokemon {
    private BaseMove[] moves = new BaseMove[4];
    private int health = 0;

    // statistiche personali (iv)
    private PokeStats stats = new PokeStats();
    private PokeStats effects = new PokeStats();

    private int psPA = 0;
    private int attackPA = 0;
    private int defensePA = 0;
    private int specialPA = 0;
    private int speedPA = 0;
    
    private int level = 0;
    private int exp = 0;
    private int expToNextLevel = 0;

    // dati base del pokemon
    private PokeData data;

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

    public int getPS() {
        return stats.getPS();
    }
    public int getTotPS() {
        return getPS() + (psPA / 4) + data.getPs();
    }
    public void setPS(int ps) {
        stats.setPS(ps);
    }

    public int getAttack() {
        return stats.getAttack();
    }
    public int getTotAttack() {
        return getAttack() + effects.getAttack() + (attackPA / 4) + data.getAttack();
    }
    public void setAttack(int attack) {
        stats.setAttack(attack);
    }

    public int getDefense() {
        return stats.getDefense();
    }
    public int getTotDefense() {
        return getDefense() + effects.getDefense() + (defensePA / 4) + data.getDefense();
    }
    public void setDefense(int defense) {
        stats.setDefense(defense);
    }

    public int getSpecial() {
        return stats.getSpecial();
    }
    public int getTotSpecial() {
        return getSpecial() + effects.getSpecial() + (specialPA / 4) + data.getSpecial();
    }
    public void setSpecial(int special) {
        stats.setSpecial(special);
    }

    public int getSpeed() {
        return stats.getSpeed();
    }
    public int getTotSpeed() {
        return getSpeed() + effects.getSpecial() + (speedPA / 4) + data.getSpeed();
    }
    public void setSpeed(int speed) {
        stats.setSpeed(speed);
    }

    public PokeStats getEffects() {
        return effects;
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

    public ArrayList<BaseMove> getMoves() {
        ArrayList<BaseMove> result = new ArrayList<>();
        for (BaseMove move : moves) {
            if (move != null)
                result.add(move);
        }
        return result;
    }
    public void setMoves(BaseMove[] moves) {
        this.moves = moves;
    }
    public BaseMove getMove(int index) {
        return moves[index];
    }
    public void setMove(int index, BaseMove move) {
        this.moves[index] = move;
        move.setSlot(index);
    }
    public int getAvailableMoves() {
        int count = 0;
        for (BaseMove move : moves) {
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
        for (BaseMove move : getMoves())
            move.resetUses();
    }
}

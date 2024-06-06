package jpokebattle.model;

import java.util.ArrayList;

import jpokebattle.enums.LevelUpGroup;
import jpokebattle.enums.PokeElement;
import jpokebattle.enums.PokeEnum;
import jpokebattle.interfaces.PokemonInterface;
import jpokebattle.interfaces.StatsInterface;
import jpokebattle.pokemon.PokemonInfo;

public class Pokemon implements PokemonInterface, StatsInterface {
    private PokeMove[] moves = new PokeMove[4];
    private int health = 0;

    // statistiche personali (iv)
    private PokeStats iv = new PokeStats(15);
    private PokeStats effects = new PokeStats(6);

    private int psEV = 0;
    private int attackEV = 0;
    private int defenseEV = 0;
    private int specialEV = 0;
    private int speedEV = 0;
    
    private int level = 0;
    private int exp = 0;

    // dati base del pokemon
    private PokemonInfo info;

    public Pokemon(PokemonInfo info) {
        this.info = info;
        setLevel(1);
    }

    public int getExp() {
        return exp;
    }

    public PokemonInfo getInfo() {
        return info;
    }
    public PokeEnum getId() {
        return info.getId();
    }

    public String getName() {
        return info.getName();
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

    public int getTotPS() {
        return calculateStat(info.getPS(), iv.getPS(), psEV) + level + 5;
    }
    public void setPS(int ps) {
        iv.setPS(ps);
    }

    public int getTotAttack() {
        return calculateStat(info.getAttack(), iv.getAttack(), attackEV);
    }
    public void setAttack(int attack) {
        iv.setAttack(attack);
    }

    public int getTotDefense() {
        return calculateStat(info.getDefense(), iv.getDefense(), defenseEV);
    }
    public void setDefense(int defense) {
        iv.setDefense(defense);
    }

    public int getTotSpecial() {
        return calculateStat(info.getSpecial(), iv.getSpecial(), specialEV);
    }
    public void setSpecial(int special) {
        iv.setSpecial(special);
    }

    public int getTotSpeed() {
        return calculateStat(info.getSpeed(), iv.getSpeed(), speedEV);
    }
    public void setSpeed(int speed) {
        iv.setSpeed(speed);
    }

    // StatsInterface with iv
    @Override
    public int getPS() {
        return iv.getPS();
    }
    @Override
    public int getAttack() {
        return iv.getAttack();
    }
    @Override
    public int getDefense() {
        return iv.getDefense();
    }
    @Override
    public int getSpecial() {
        return iv.getSpecial();
    }
    @Override
    public int getSpeed() {
        return iv.getSpeed();
    }

    // PokemonInterface
    @Override
    public PokeElement getElement() {
        return info.getElement();
    }
    
    @Override
    public boolean canEvolve() {
        return info.getCurrentEvolution() < info.getMaxEvolution();
    }

    @Override
    public LevelUpGroup getLevelType() {
        return info.getLevelType();
    }

    @Override
    public int getCurrentEvolution() {
        return info.getCurrentEvolution();
    }

    @Override
    public int getMaxEvolution() {
        return info.getMaxEvolution();
    }

    @Override
    public int getDropExp() {
        return info.getDropExp();
    }

    public PokeStats getEffects() {
        return effects;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
        this.exp = info.getLeveRequiredExp(level); // imposta l'exp necessaria per il livello attuale
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getExpToNextLevel() {
        return info.getLeveRequiredExp(level + 1) - exp;
    }
    public boolean canLevelUp() {
        return exp >= info.getLeveRequiredExp(level + 1);
    }

    // s = numero di pokemon che hanno partecipato alla battaglia
    public void gainExp(Pokemon enemy, int s) {
        int b = enemy.getDropExp();
        int L = enemy.getLevel();
        double a = 1.5;
        Double res = (b*L)/7 * 1/s * a;
        exp += res.intValue();
    }

    public void levelUp() {
        level++;
        // siccome non ci si puo' curare durante la battaglia, il pokemon viene 
        // curato automaticamente quando sale di livello
        restore();
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

    public void restore() {
        restoreHealth();
        effects.reset();
        for (PokeMove move : getMoves())
            move.resetUses();
    }
}

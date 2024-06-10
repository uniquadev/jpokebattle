package jpokebattle.model;

import java.util.ArrayList;

import jpokebattle.enums.LevelUpGroup;
import jpokebattle.enums.PokeElement;
import jpokebattle.enums.PokeEnum;
import jpokebattle.interfaces.PokemonInterface;
import jpokebattle.move.BaseMove;
import jpokebattle.pokemon.PokemonInfo;
import jpokebattle.service.MoveService;
import jpokebattle.service.PokeService;

/**
 * Represents a Pokemon in the game.
 */
public class Pokemon implements PokemonInterface {
    private PokeMove[] moves = new PokeMove[4];

    private PokeStats iv = new PokeStats(15);
    private PokeStats ev = new PokeStats(255);
    private PokeStats effects = new PokeStats(6);
    
    private int level = 0;
    private int exp = 0;
    private int health = 0;

    // pokemon info
    private PokemonInfo info;

    /**
     * Constructs a new Pokemon with the given PokemonInfo.
     * @param info The PokemonInfo object containing the base data of the Pokemon.
     */
    public Pokemon(PokemonInfo info) {
        this.info = info;
        setLevel(1);
    }

    /**
     * Gets the current experience points of the Pokemon.
     * @return The current experience points.
     */
    public int getExp() {
        return exp;
    }

    /**
     * Gets the PokemonInfo object associated with this Pokemon.
     * @return The PokemonInfo object.
     */
    public PokemonInfo getInfo() {
        return info;
    }

    /**
     * Gets the current health points of the Pokemon.
     * @return The current health points.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Restores the health of the Pokemon to its maximum value.
     */
    public void restoreHealth() {
        this.health = getTotPS();
    }

    /**
     * Checks if the Pokemon is dead.
     * @return true if the Pokemon is dead, false otherwise.
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Inflicts damage to the Pokemon.
     * @param damage The amount of damage to inflict.
     */
    public void damage(int damage) {
        this.health -= damage;
        this.health = Math.max(0, this.health);
    }

    /**
     * Heals the Pokemon.
     * @param heal The amount of health points to heal.
     */
    public void heal(int heal) {
        this.health += heal;
        this.health = Math.max(Math.min(getTotPS(), this.health), 0);
    }

    /**
     * Calculates the total power points of a specific stat for the Pokemon.
     * @param base The base value of the stat.
     * @param iv The individual value of the stat.
     * @param pa The effort value of the stat.
     * @return The total power points of the stat.
     */
    private int calculateStat(int base, int iv, int pa) {
        return (((base + iv) * 2 + pa/4) * level) / 100 + 5;
    }

    /**
     * Gets the total power points of the Pokemon's health stat.
     * @return The total power points of the health stat.
     */
    public int getTotPS() {
        return calculateStat(info.getPS(), iv.getPS(), ev.getPS()) + level + 5;
    }

    /**
     * Sets the individual value of the Pokemon's health stat.
     * @param ps The new individual value.
     */
    public void setPS(int ps) {
        iv.setPS(ps);
    }

    /**
     * Gets the total power points of the Pokemon's attack stat.
     * @return The total power points of the attack stat.
     */
    public int getTotAttack() {
        return calculateStat(info.getAttack(), iv.getAttack(), ev.getAttack());
    }

    /**
     * Sets the individual value of the Pokemon's attack stat.
     * @param attack The new individual value.
     */
    public void setAttack(int attack) {
        iv.setAttack(attack);
    }

    /**
     * Gets the total power points of the Pokemon's defense stat.
     * @return The total power points of the defense stat.
     */
    public int getTotDefense() {
        return calculateStat(info.getDefense(), iv.getDefense(), ev.getDefense());
    }

    /**
     * Sets the individual value of the Pokemon's defense stat.
     * @param defense The new individual value.
     */
    public void setDefense(int defense) {
        iv.setDefense(defense);
    }

    /**
     * Gets the total power points of the Pokemon's special stat.
     * @return The total power points of the special stat.
     */
    public int getTotSpecial() {
        return calculateStat(info.getSpecial(), iv.getSpecial(), ev.getSpecial());
    }

    /**
     * Sets the individual value of the Pokemon's special stat.
     * @param special The new individual value.
     */
    public void setSpecial(int special) {
        iv.setSpecial(special);
    }

    /**
     * Gets the total power points of the Pokemon's speed stat.
     * @return The total power points of the speed stat.
     */
    public int getTotSpeed() {
        return calculateStat(info.getSpeed(), iv.getSpeed(), ev.getSpeed());
    }

    /**
     * Sets the individual value of the Pokemon's speed stat.
     * @param speed The new individual value.
     */
    public void setSpeed(int speed) {
        iv.setSpeed(speed);
    }

    /**
     * Gets the total power points of all the Pokemon's stats.
     * @return The total power points of all the stats.
     */
    public PokeStats getTotStats() {
        PokeStats res = new PokeStats();
        res.setPS(getTotPS());
        res.setAttack(getTotAttack());
        res.setDefense(getTotDefense());
        res.setSpecial(getTotSpecial());
        res.setSpeed(getTotSpeed());
        return res;
    }

    /**
     * Gets the effects of the Pokemon.
     * @return The effects of the Pokemon.
     */
    public PokeStats getEffects() {
        return effects;
    }

    /**
     * Gets the level of the Pokemon.
     * @return The level of the Pokemon.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the Pokemon.
     * @param level The new level.
     */
    public void setLevel(int level) {
        this.level = level;
        this.exp = info.getLevelRequiredExp(level); // imposta l'exp necessaria per il livello attuale
    }

    /**
     * Sets the experience points of the Pokemon.
     * @param exp The new experience points.
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * Gets the experience points required to reach the next level.
     * @return The experience points required to reach the next level.
     */
    public int getExpToNextLevel() {
        return info.getLevelRequiredExp(level + 1) - exp;
    }

    /**
     * Checks if the Pokemon can level up.
     * @return true if the Pokemon can level up, false otherwise.
     */
    public boolean canLevelUp() {
        return getExpToNextLevel() <= 0;
    }

    /**
     * Checks if the Pokemon can evolve.
     * 
     * @return true if the Pokemon can evolve, false otherwise.
     */
    public boolean canEvolve() {
        return info.evolveAtLevel() <= level;
    }

    /**
     * Evolves the Pokemon to its next form, if it has an evolution.
     * If the Pokemon does not have an evolution or the next form is not found, the method returns without making any changes.
     * Otherwise, the Pokemon's information is updated to the next form and its stats are restored to their original values.
     */
    public void evolve() {
        PokemonInfo nextInfo = info.getNextEvolution();
        if (nextInfo == null) return;
        info = nextInfo;
        restore();
    }

    /**
     * Calculates the experience points gained by the Pokemon after defeating an enemy.
     * @param enemy The enemy Pokemon.
     * @param s The number of Pokemon that participated in the battle.
     */
    public void gainExp(Pokemon enemy, int s) {
        int b = enemy.getInfo().getDropExp();
        int L = enemy.getLevel();
        double a = 1.5;
        Double res = (b*L)/7 * 1/s * a;
        exp += res.intValue();
    }

    /**
     * Levels up the Pokemon until it reach exp level.
     */
    public void levelUp() {
        while (canLevelUp()) {
            level++;
        }
        // siccome non ci si puo' curare durante la battaglia, il pokemon viene 
        // curato automaticamente quando sale di livello
        restore();
    }

    /**
     * Gets the list of moves that the Pokemon knows and can use.
     * @return The list of moves.
     */
    public ArrayList<PokeMove> getMoves() {
        ArrayList<PokeMove> result = new ArrayList<>();
        for (PokeMove move : moves) {
            if (move != null && move.getUsesLeft() > 0)
                result.add(move);
        }
        return result;
    }

    /**
     * Sets the moves that the Pokemon knows.
     * @param moves The array of moves.
     */
    public void setMoves(PokeMove[] moves) {
        this.moves = moves;
    }

    /**
     * Gets the move at the specified index.
     * @param index The index of the move.
     * @return The move at the specified index.
     */
    public PokeMove getMove(int index) {
        return moves[index];
    }

    /**
     * Sets the move at the specified index.
     * @param index The index of the move.
     * @param move The move to set.
     */
    public void setMove(int index, PokeMove move) {
        this.moves[index] = move;
        move.setSlot(index);
    }

    /**
     * Checks if the Pokemon has a specific move.
     *
     * @param move the move to check
     * @return true if the Pokemon has the move, false otherwise
     */
    public boolean hasMove(BaseMove move) {
        for (PokeMove m : moves) {
            if (m != null && m.getName() == move.getName())
                return true;
        }
        return false;
    }

    /**
     * Checks if the Pokemon can learn a move at its current level.
     * 
     * @return A PokeMove object representing the move that the Pokemon can learn, or null if it cannot learn any move.
     */
    public PokeMove canLearnMove() {
        var moves = MoveService.getSingleton().getMovesAtLevel(getId(), getLevel());
        for (BaseMove move : moves) {
            if (!hasMove(move))
                return new PokeMove(move);
        }
        return null; 
    }

    /**
     * Adds a move to the Pokemon's move set where slot is free.
     * 
     * @param move the move to be added
     * @return the slot where the move was added, or null if no slot was available
     */
    public Integer addMove(PokeMove move) {
        Integer slot = null;
        for (int i = 0; i < moves.length; i++) {
            if (moves[i] == null) {
                slot = i;
                setMove(i, move);
                break;
            }
        }
        return slot;
    }

    /**
     * Restores the Pokemon to its initial state.
     */
    public void restore() {
        restoreHealth();
        effects.reset();
        for (PokeMove move : getMoves())
            move.resetUses();
    }
    
    /**
     * Returns the name of the Pokemon.
     *
     * @return the name of the Pokemon
     */
    @Override
    public String getName() {
        return info.getName();
    }

    /**
     * Returns the ID of the Pokemon.
     *
     * @return the ID of the Pokemon
     */
    @Override
    public PokeEnum getId() {
        return info.getId();
    }

    /**
     * Gets the element type of the Pokemon.
     * @return The element type.
     */
    @Override
    public PokeElement getElement() {
        return info.getElement();
    }

    /**
     * Returns the level up group of the Pokemon.
     *
     * @return the level up group of the Pokemon
     */
    @Override
    public LevelUpGroup getLevelType() {
        return info.getLevelType();
    }

    /**
     * Returns the current evolution of the Pokemon.
     *
     * @return the current evolution of the Pokemon
     */
    @Override
    public int getCurrentEvolution() {
        return info.getCurrentEvolution();
    }

    /**
     * Returns the maximum evolution of the Pokemon.
     *
     * @return the maximum evolution of the Pokemon
     */
    @Override
    public int getMaxEvolution() {
        return info.getMaxEvolution();
    }

    /**
     * Returns the amount of experience points that this Pokemon drops when defeated.
     *
     * @return the amount of experience points dropped by this Pokemon
     */
    @Override
    public int getDropExp() {
        return info.getDropExp();
    }

    /**
     * Checks if the Pokemon has an evolution.
     *
     * @return true if the Pokemon has an evolution, false otherwise.
     */
    @Override
    public boolean hasEvolution() {
        return info.hasEvolution();
    }

    /**
     * Returns the level at which this Pokemon evolves.
     *
     * @return the level at which this Pokemon evolves
     */
    @Override
    public int evolveAtLevel() {
        return info.evolveAtLevel();
    }
}

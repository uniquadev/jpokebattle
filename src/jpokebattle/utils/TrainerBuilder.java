package jpokebattle.utils;

import java.util.ArrayList;
import java.util.Random;

import jpokebattle.model.Trainer;
import jpokebattle.pokemon.PokemonInfo;

/**
 * The TrainerFactory class is responsible for generating Trainer objects with random names and teams of Pokemon.
 */
public class TrainerBuilder {
    private PokeBuilder pokeFactory = new PokeBuilder();
    private PokeRandomizer randomizer = new PokeRandomizer();
    private Random random = new Random();
    private boolean generateTeam = true;
    private boolean useStarters = false;

    private int minTeamSize = 1;
    private int maxTeamSize = 1;

    private static final String[] NAMES = {
        "Ash", "Misty", "Brock", "Gary", "Jessie",
        "James", "May", "Dawn", "Iris", "Cilan", "Serena",
        "Clemont", "Bonnie", "Lana", "Kiawe", "Lillie", "Mallow",
        "Sophocles", "Goh", "Sterbini" // OMG
    };
    
    /**
     * Generates a Trainer object with random name and team of Pokemon.
     * 
     * @return The generated Trainer object.
     */
    public Trainer generate() {
        Trainer trainer = new Trainer();
        generateName(trainer);
        if (generateTeam) {
            generateTeam(trainer);
        }
        return trainer;
    }

    /**
     * Generates a random name for the given Trainer object.
     * 
     * @param trainer The Trainer object for which to generate a name.
     */
    private void generateName(Trainer trainer) {
        trainer.setName(getRandomName());
    }

    /**
     * Generates a random team of Pokemon for the given Trainer object.
     * 
     * @param trainer The Trainer object for which to generate a team.
     */
    private void generateTeam(Trainer trainer) {
        ArrayList<PokemonInfo> infos;
        int teamSize = random.nextInt(minTeamSize, maxTeamSize+1);
        if (useStarters) {
            infos = randomizer.getRandomStarters(teamSize);
        } else {
            infos = randomizer.getRandomUniqueInfo(teamSize);
        }
        
        for (int i = 0; i < Math.min(infos.size(), teamSize); i++) {
            PokemonInfo info = infos.get(i);
            pokeFactory.setId(info.getId());
            trainer.setPokemon(i, pokeFactory.generate());
        }
        if (teamSize > 0) {
            trainer.setActivePokemon(0);
        }
    }

    /**
     * Generates a random name from the predefined list of names.
     * 
     * @return A random name.
     */
    public static String getRandomName() {
        Random random = new Random();
        return NAMES[random.nextInt(NAMES.length)];
    }

    /**
     * Sets the minimum team size for the Trainer object.
     * 
     * @param teamSize The minimum team size.
     * @return The TrainerBuilder object.
     */
    public TrainerBuilder setMinTeamSize(int teamSize) {
        this.minTeamSize = Math.max(1, teamSize);
        return this;
    }

    /**
     * Sets the maximum team size for the Trainer object.
     * 
     * @param teamSize The maximum team size.
     * @return The TrainerBuilder object.
     */
    public TrainerBuilder setMaxTeamSize(int teamSize) {
        this.maxTeamSize = Math.min(6, teamSize);
        return this;
    }

    /**
     * Sets the minimum level for the generated Pokemon.
     * 
     * @param minPokeLevel The minimum level.
     * @return The TrainerBuilder object.
     */
    public TrainerBuilder setMinPokeLevel(int minPokeLevel) {
        pokeFactory.setMinLevel(minPokeLevel);
        return this;
    }

    /**
     * Sets the maximum level for the generated Pokemon.
     * 
     * @param maxPokeLevel The maximum level.
     * @return The TrainerBuilder object.
     */
    public TrainerBuilder setMaxPokeLevel(int maxPokeLevel) {
        pokeFactory.setMaxLevel(maxPokeLevel);
        return this;
    }

    /**
     * Sets the maximum evolution stage for the generated Pokemon.
     * 
     * @param maxEvolution The maximum evolution stage.
     * @return The TrainerBuilder object.
     */
    public TrainerBuilder setMaxEvolution(int maxEvolution) {
        randomizer.setMaxEvolution(maxEvolution);
        return this;
    }

    /**
     * Sets the minimum evolution stage for the generated Pokemon.
     * 
     * @param minEvolution The minimum evolution stage.
     * @return The TrainerBuilder object.
     */
    public TrainerBuilder setMinEvolution(int minEvolution) {
        randomizer.setMinEvolution(minEvolution);
        return this;
    }

    /**
     * Sets whether to use starter Pokemon for the generated team.
     * 
     * @param useStarters True to use starter Pokemon, false otherwise.
     * @return The TrainerBuilder object.
     */
    public TrainerBuilder useStarters(boolean useStarters) {
        this.useStarters = useStarters;
        return this;
    }
}

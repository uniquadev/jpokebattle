package jpokebattle.utils;

import java.util.ArrayList;
import java.util.Random;

import jpokebattle.model.PokeData;
import jpokebattle.model.Trainer;

public class TrainerBuilder {
    private PokeBuilder pokeBuilder = new PokeBuilder();
    private PokeRandomizer randomizer = new PokeRandomizer();
    private boolean generateName = true;
    private boolean generateTeam = true;
    private boolean generateItems = true;
    private boolean useStarters = false;

    private int teamSize = 6;

    private static final String[] NAMES = {
        "Ash", "Misty", "Brock", "Gary", "Jessie",
        "James", "May", "Dawn", "Iris", "Cilan", "Serena",
        "Clemont", "Bonnie", "Lana", "Kiawe", "Lillie", "Mallow",
        "Sophocles", "Goh", "Sterbini" // OMG
    };
    
    public TrainerAI generate() {
        TrainerAI trainer = new TrainerAI();
        if (generateName) {
            generateName(trainer);
        }
        if (generateTeam) {
            generateTeam(trainer);
        }
        if (generateItems) {
            generateItems(trainer);
        }
        return trainer;
    }

    public static String getRandomName() {
        Random random = new Random();
        return NAMES[random.nextInt(NAMES.length)];
    }

    private void generateName(TrainerAI trainer) {
        trainer.setName(getRandomName());
    }

    private void generateTeam(TrainerAI trainer) {
        ArrayList<PokeData> datas;
        if (useStarters) {
            datas = randomizer.getRandomStarters(teamSize);
        } else {
            datas = randomizer.getRandomUniqueData(teamSize);
        }
        
        for (int i = 0; i < Math.min(datas.size(), teamSize); i++) {
            PokeData data = datas.get(i);
            pokeBuilder.setId(data.getId());
            trainer.setPokemon(i, pokeBuilder.generate());
        }
        if (teamSize > 0) {
            trainer.setCurrentPokemon(0);
        }
    }

    private void generateItems(Trainer trainer) {
        // TODO
    }

    public TrainerBuilder setGenerateName(boolean generateName) {
        this.generateName = generateName;
        return this;
    }

    public TrainerBuilder setTeamSize(int teamSize) {
        this.teamSize = Math.min(6, teamSize);
        return this;
    }

    public TrainerBuilder setMinPokeLevel(int minPokeLevel) {
        pokeBuilder.setMinLevel(minPokeLevel);
        return this;
    }

    public TrainerBuilder setMaxPokeLevel(int maxPokeLevel) {
        pokeBuilder.setMaxLevel(maxPokeLevel);
        return this;
    }

    public TrainerBuilder setMaxEvolution(int maxEvolution) {
        randomizer.setMaxEvolution(maxEvolution);
        return this;
    }

    public TrainerBuilder useStarters(boolean useStarters) {
        this.useStarters = useStarters;
        return this;
    }
}

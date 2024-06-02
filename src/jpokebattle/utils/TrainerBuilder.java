package jpokebattle.utils;

import java.util.ArrayList;
import java.util.Random;

import jpokebattle.model.PokeData;
import jpokebattle.model.Trainer;
import jpokebattle.service.PokeService;

public class TrainerBuilder {
    private boolean generateName = true;
    private boolean generateTeam = true;
    private boolean generateItems = true;
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

    private void generateName(Trainer trainer) {
        trainer.setName(getRandomName());
    }
    

    private void generateTeam(Trainer trainer) {
        PokeService service = PokeService.getSingleton();
        ArrayList<PokeData> datas = service.getRandomUniqueData(teamSize);
        PokeBuilder builder = new PokeBuilder();

        for (int i = 0; i < datas.size(); i++) {
            PokeData data = datas.get(i);
            builder.setId(data.getId());
            trainer.setPokemon(i, builder.generate());
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
}

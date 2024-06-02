package jpokebattle.model;

public class Trainer {
    private Pokemon[] pokemons = new Pokemon[6];
    private String name = "Trainer";

    public Pokemon getPokemon(int index) {
        assert index >= 0 && index < 6;
        return pokemons[index];
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void generateRandomTeam() {
        // TODO: implement
    }
}

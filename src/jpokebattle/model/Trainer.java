package jpokebattle.model;

public class Trainer {
    private Pokemon[] pokemons = new Pokemon[6];
    private String nickname;

    public Pokemon getPokemon(int index) {
        assert index >= 0 && index < 6;
        return pokemons[index];
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getNickname() {
        return nickname;
    }

    public void generateRandomTeam() {
        // TODO: implement
    }
}

package jpokebattle.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jpokebattle.JPokeBattle;
import jpokebattle.enums.PokeEnum;
import jpokebattle.model.Pokemon;

public class PokemonView extends ImageView {
    private PokeEnum id = PokeEnum.BULBASAUR;
    private boolean renderBack = false;

    public PokemonView() {
        super();
        updateImage();
    }

    public void updateImage() {
        String imagePath;
        if (renderBack) {
            imagePath = "resources/sprites/pokemons/back/" + id.ordinal() + ".png";
        } else {
            imagePath = "resources/sprites/pokemons/" + id.ordinal() + ".png";
        }
        Image image = new Image(
            JPokeBattle.class.getResourceAsStream(imagePath),
            168, 168,
            false, false
        );
        setImage(image);
    }

    public void setPokemon(Pokemon pokemon) {
        this.id = pokemon.getInfo().getId();
        updateImage();
    }

    public void setRenderBack(boolean renderBack) {
        this.renderBack = renderBack;
        updateImage();
    }
}

package jpokebattle.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import jpokebattle.JPokeBattle;
import jpokebattle.gui.GridMenu;
import jpokebattle.gui.PokeInfoCard;
import jpokebattle.gui.PokemonView;
import jpokebattle.gui.GridMenu.MenuOption;
import jpokebattle.model.Pokemon;
import jpokebattle.service.GameState;
import jpokebattle.utils.TrainerBuilder;

public class BattleController {
    
    @FXML
    StackPane bottomStackPane;
    @FXML
    Label battleLabel;
    @FXML
    GridMenu actionMenu;
    @FXML
    ImageView pokeBox;

    @FXML
    PokemonView playerPokemonView;
    @FXML
    PokeInfoCard playerInfoCard;
    @FXML
    PokeInfoCard botInfoCard;
    @FXML
    PokemonView botPokemonView;

    public void initialize() {
        playerPokemonView.setRenderBack(true);
        bottomStackPane.setPrefHeight(JPokeBattle.HEIGHT * 0.3);
        battleLabel.setPrefWidth(JPokeBattle.WIDTH * 0.4);
        actionMenu.Initialize(new MenuOption[]{
            new GridMenu.MenuOption("Azioni", true),
            new GridMenu.MenuOption("Pokemon", true),
            new GridMenu.MenuOption("Oggetti", true),
            new GridMenu.MenuOption("Fuggi", false)
        }, 2);
        actionMenu.setArrowSize(25, 25);
        actionMenu.setAlignment(Pos.CENTER);
        pokeBox.fitWidthProperty().bind(bottomStackPane.widthProperty());
        pokeBox.fitHeightProperty().bind(bottomStackPane.heightProperty());
        botInfoCard.removeHpLabel();

        bottomStackPane.setBackground(
            new Background(new BackgroundFill(
                Color.WHITESMOKE,
                null,
                null
            ))
        );
        
        startBattle();
    }

    private void startBattle() {
        GameState.getSingleton().setState(GameState.State.BATTLE);
        generateAI();
        updatePokemons();
        // TODO: start intro animation (make method)
    }

    public void generateAI() {
        // TODO: implement difficulty based on score and player team level
        TrainerBuilder builder = new TrainerBuilder();
        GameState.getSingleton().setAI(builder.generate());
        System.out.println("AI generato!");
    }

    private void updatePokemons() {
        GameState state = GameState.getSingleton();
        var player = state.getPlayer();
        var ai = state.getAI();
        Pokemon playerPokemon = player.getCurrentPokemon();
        Pokemon aiPokemon = ai.getCurrentPokemon();
        if (playerPokemon != null) {
            playerPokemonView.setId(playerPokemon.getId());
            playerInfoCard.update(playerPokemon);
        }
        if (aiPokemon != null) {
            botPokemonView.setId(aiPokemon.getId());
            botInfoCard.update(aiPokemon);
        }
    }
}

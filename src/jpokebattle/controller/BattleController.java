package jpokebattle.controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import jpokebattle.JPokeBattle;
import jpokebattle.gui.GridMenu;
import jpokebattle.gui.PokeInfoCard;
import jpokebattle.gui.PokemonView;
import jpokebattle.gui.GridMenu.MenuOption;
import jpokebattle.model.BaseItem;
import jpokebattle.model.Pokemon;
import jpokebattle.service.GameState;
import jpokebattle.utils.TrainerBuilder;

public class BattleController {

    GameState game;
    
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
    ImageView arrowToLeft;

    @FXML
    PokeInfoCard botInfoCard;
    @FXML
    ImageView arrowToRight;
    @FXML
    PokemonView botPokemonView;

    public BattleController() {
        game = GameState.getSingleton();
    }

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
        game.setState(GameState.State.BATTLE);
        generateAI();
        updatePokemons();
        startIntroAnimation();
    }

    private void toggleInfoCards(boolean flag)
    {
        playerInfoCard.setVisible(flag);
        botInfoCard.setVisible(flag);
        arrowToLeft.setVisible(flag);
        arrowToRight.setVisible(flag);
    }

    private void startIntroAnimation() {
        toggleInfoCards(false);
        playerPokemonView.setTranslateX(-JPokeBattle.WIDTH/2);
        botPokemonView.setTranslateX(JPokeBattle.WIDTH/2);
        // animate them to tween to translateX = 0
        TranslateTransition playerTransition = new TranslateTransition(Duration.seconds(1), playerPokemonView);
        playerTransition.setToX(0);
        playerTransition.setDuration(Duration.seconds(1));
        playerTransition.play();
        TranslateTransition botTransition = new TranslateTransition(Duration.seconds(1), botPokemonView);
        botTransition.setToX(0);
        botTransition.setDuration(Duration.seconds(1));
        botTransition.play();
        playerTransition.setOnFinished(e -> {
            toggleInfoCards(true);
        });
    }

    public void generateAI() {
        // TODO: implement difficulty based on score and player team level
        TrainerBuilder builder = new TrainerBuilder();
        game.setAI(builder.generate());
        System.out.println("AI generato!");
    }

    private void updatePokemons() {
        var player = game.getPlayer();
        var ai = game.getAI();
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

    private void useItem(BaseItem item)
    {
        // TODO:
    }

    private void switchPokemon(Pokemon pokemon)
    {
        
    }

    private void runAway()
    {
    }
}

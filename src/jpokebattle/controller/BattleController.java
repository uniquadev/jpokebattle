package jpokebattle.controller;

import java.util.ArrayList;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import jpokebattle.JPokeBattle;
import jpokebattle.gui.ArrowTextButton;
import jpokebattle.gui.GridMenu;
import jpokebattle.gui.PokeInfoCard;
import jpokebattle.gui.PokeTeamList;
import jpokebattle.gui.PokemonView;
import jpokebattle.gui.GridMenu.MenuOption;
import jpokebattle.model.BaseItem;
import jpokebattle.model.Pokemon;
import jpokebattle.moves.BaseMove;
import jpokebattle.service.GameState;

public class BattleController {

    GameState game;
    
    @FXML
    StackPane root;
    @FXML
    VBox pokemonBox;
    @FXML
    ArrowTextButton pokemonBackButton;
    @FXML
    ArrowTextButton actionBackButton;
    @FXML
    VBox battleBox;
    @FXML
    StackPane battlePane;
    @FXML
    StackPane bottomStackPane;
    @FXML
    StackPane menuPane;
    @FXML
    StackPane pokemonActionPane;
    @FXML
    PokeTeamList pokeTeamList;
    @FXML
    Label battleLabel;
    @FXML
    GridMenu mainMenu;
    @FXML
    ImageView pokeBox;

    @FXML
    StackPane moveInfoPane;
    @FXML
    Label moveTypeLabel;
    @FXML
    Label moveUsesLabel;

    @FXML
    StackPane actionPane;
    @FXML
    GridMenu actionMenu;

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
        mainMenu.Initialize(new MenuOption[]{
            new GridMenu.MenuOption("Azioni", true),
            new GridMenu.MenuOption("Pokemon", true),
            new GridMenu.MenuOption("Oggetti", true),
            new GridMenu.MenuOption("Fuggi", false)
        }, 2);
        mainMenu.setClickCallback(this::onMainGridClick);
        mainMenu.setArrowSize(25, 25);
        actionMenu.setClickCallback(this::onActionGridClick);
        actionMenu.setSelectCallback(this::onActionSelect);
        actionMenu.setArrowSize(25, 25);
        actionMenu.setHgap(180);

        pokeBox.fitWidthProperty().bind(bottomStackPane.widthProperty());
        pokeBox.fitHeightProperty().bind(bottomStackPane.heightProperty());
        botInfoCard.removeHpLabel();

        pokemonBackButton.setText("< Indietro");
        pokemonBackButton.removeArrow();
        pokemonBackButton.getButton().setOnAction(e -> {
            togglePokemonView(false);
        });

        actionBackButton.setText("Indietro");
        actionBackButton.removeArrow();
        actionBackButton.getButton().setOnAction(e -> {
            toggleActionView(false);
        });

        bottomStackPane.setBackground(
            new Background(new BackgroundFill(
                Color.WHITESMOKE,
                null,
                null
            ))
        );
        moveInfoPane.setBackground(
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
        game.generateAI();
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
        TranslateTransition playerTransition = new TranslateTransition(Duration.seconds(1.2), playerPokemonView);
        playerTransition.setToX(0);
        playerTransition.play();
        TranslateTransition botTransition = new TranslateTransition(Duration.seconds(1.2), botPokemonView);
        botTransition.setToX(0);
        botTransition.play();
        playerTransition.setOnFinished(e -> {
            toggleInfoCards(true);
        });
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

    private void togglePokemonView(boolean flag) {
        battlePane.setVisible(!flag);
        menuPane.setVisible(!flag);
        pokeTeamList.update(game.getPlayer());
        pokemonBox.setVisible(flag);
        pokemonActionPane.setVisible(flag);
    }

    private void updateActionMenu(Pokemon pokemon) {
        ArrayList<BaseMove> moves = pokemon.getMoves();
        MenuOption[] options = new MenuOption[moves.size()];
        for (int i = 0; i < moves.size(); i++) {
            BaseMove move = moves.get(i);
            MenuOption option = new MenuOption(move.getName(), true, move.getSlot());
            options[i] = option;
        }
        actionMenu.Initialize(options, 2);
    }

    private void toggleActionView(boolean flag) {
        if (flag)
            updateActionMenu(game.getPlayer().getCurrentPokemon());
        menuPane.setVisible(!flag);
        actionPane.setVisible(flag);
        moveInfoPane.setVisible(flag);
    }

    private void onActionSelect(MenuOption option) {
        if (!game.isPlayerTurn())
            return;
        Pokemon pokemon = game.getPlayer().getCurrentPokemon();
        BaseMove move = pokemon.getMove(option.getValue());
        moveTypeLabel.setText("TIPO/" + move.getElement().name());
        moveUsesLabel.setText(move.getUsesLeft() + "/" + move.getMaxUses());
    }

    private void onActionGridClick(MenuOption option) {
        if (!game.isPlayerTurn())
            return;
        Pokemon player = game.getPlayer().getCurrentPokemon();
        BaseMove move = player.getMove(option.getValue());
        System.out.println("Player used " + move.getName());
    }

    private void onMainGridClick(MenuOption option) {
        switch (option.getText()) {
            case "Fuggi":
                break;
            case "Pokemon":
                togglePokemonView(true);
                break;
            case "Oggetti":
                break;
            case "Azioni":
                toggleActionView(true);
                break;
            default:
                break;
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

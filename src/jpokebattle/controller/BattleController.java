package jpokebattle.controller;

import java.util.ArrayList;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
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
import jpokebattle.enums.MoveType;
import jpokebattle.gui.ArrowTextButton;
import jpokebattle.gui.GridMenu;
import jpokebattle.gui.HPokeBalls;
import jpokebattle.gui.PokeInfoCard;
import jpokebattle.gui.PokeTeamList;
import jpokebattle.gui.PokemonView;
import jpokebattle.gui.GridMenu.MenuOption;
import jpokebattle.model.AttackData;
import jpokebattle.model.PokeMove;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;
import jpokebattle.service.GameState;
import jpokebattle.service.SoundService;
import jpokebattle.utils.TurnManager;

public class BattleController {

    private GameState game;
    private TurnManager turnManager;
    private SoundService sound;
    
    @FXML
    private Label scoreLabel;
    @FXML
    private StackPane root;
    @FXML
    private VBox pokemonBox;
    @FXML
    private ArrowTextButton pokemonBackButton;
    @FXML
    private ArrowTextButton actionBackButton;
    @FXML
    private VBox battleBox;
    @FXML
    private StackPane battlePane;
    @FXML
    private StackPane bottomStackPane;
    @FXML
    private StackPane menuPane;
    @FXML
    private StackPane pokemonActionPane;
    @FXML
    private PokeTeamList pokeTeamList;
    @FXML
    private Label battleLabel;
    @FXML
    private GridMenu mainMenu;
    @FXML
    private ImageView pokeBox;

    @FXML
    private StackPane moveInfoPane;
    @FXML
    private Label moveTypeLabel;
    @FXML
    private Label moveUsesLabel;

    @FXML
    private StackPane actionPane;
    @FXML
    private GridMenu actionMenu;

    @FXML
    private PokemonView playerPokemonView;
    @FXML
    private PokeInfoCard playerInfoCard;
    @FXML
    private ImageView arrowToLeft;
    @FXML
    private HPokeBalls botPokeballs;

    @FXML
    private PokeInfoCard botInfoCard;
    @FXML
    private ImageView arrowToRight;
    @FXML
    private PokemonView botPokemonView;

    public BattleController() {
        game = GameState.getSingleton();
        sound = SoundService.getSingleton();
    }

    public void initialize() {
        playerPokemonView.setRenderBack(true);
        bottomStackPane.setPrefHeight(JPokeBattle.HEIGHT * 0.3);
        battleLabel.setPrefWidth(JPokeBattle.WIDTH * 0.4);
        updateMainMenu();
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

        pokeTeamList.setCallback(this::onSwitchPokemon);
        
        initializeBattle();
    }

    private void updateMainMenu() {
        mainMenu.Initialize(new MenuOption[]{
            new GridMenu.MenuOption("Azioni", true),
            new GridMenu.MenuOption("Pokemon", true),
            new GridMenu.MenuOption("Oggetti", false),
            new GridMenu.MenuOption("Fuggi", true)
        }, 2);
    }

    private void initializeBattle() {
        turnManager = new TurnManager(game, game.getPlayer());
        updatePokemons();
        startIntroAnimation();
    }

    private void toggleInfoCards(boolean flag)
    {
        playerInfoCard.setVisible(flag);
        botInfoCard.setVisible(flag);
        arrowToLeft.setVisible(flag);
        arrowToRight.setVisible(flag);
        botPokeballs.setVisible(flag);
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
        Trainer player = game.getPlayer();
        Trainer opponent = turnManager.getOpponent();
        Pokemon playerPokemon = player.getCurrentPokemon();
        Pokemon botPokemon = opponent.getCurrentPokemon();
        botPokeballs.update(opponent);
        if (playerPokemon != null) {
            playerPokemonView.setPokemon(playerPokemon);
            playerInfoCard.update(playerPokemon);
        }
        if (botPokemon != null) {
            botPokemonView.setPokemon(botPokemon);
            botInfoCard.update(botPokemon);
        }
    }

    private void togglePokemonView(boolean flag) {
        battlePane.setVisible(!flag);
        scoreLabel.setVisible(!flag);
        menuPane.setVisible(!flag);
        pokeTeamList.update(game.getPlayer());
        pokemonBox.setVisible(flag);
        pokemonActionPane.setVisible(flag);
    }

    private void updateActionMenu(Pokemon pokemon) {
        ArrayList<PokeMove> moves = pokemon.getMoves();
        MenuOption[] options = new MenuOption[moves.size()];
        for (int i = 0; i < moves.size(); i++) {
            PokeMove move = moves.get(i);
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
        if (!turnManager.isPlayerTurn())
            return;
        Pokemon pokemon = game.getPlayer().getCurrentPokemon();
        PokeMove move = pokemon.getMove(option.getValue());
        moveTypeLabel.setText("TIPO/" + move.getElement().name());
        moveUsesLabel.setText(move.getUsesLeft() + "/" + move.getMaxUses());
    }

    private void onActionGridClick(MenuOption option) {
        if (!turnManager.isPlayerTurn())
            return;
        Pokemon playerPokemon = game.getPlayer().getCurrentPokemon();
        PokeMove move = playerPokemon.getMove(option.getValue());
        if (!move.canUse())
            return;
        toggleActionView(false);
        menuPane.setDisable(true);

        Trainer opponent = turnManager.getOpponent();
        Pokemon botPokemon = opponent.getCurrentPokemon();
        AttackData attack = move.perform(playerPokemon, botPokemon);
        move.consumeUse();
        move.draw(this, playerPokemon, botPokemon);

        if (attack.getType() == MoveType.MISS) {
            // playDialogAndWait("Mossa fallita!");
        } else if (attack.getType() == MoveType.ATTACK) {
            // applica danno
            System.out.println("Applicando danno: " + attack.getDamage());
            botPokemon.damage(attack.getDamage());
            botInfoCard.tweenHealth(this::onBotHealthTweenFinished);
                
        } else if (attack.getType() == MoveType.STATUS) {
            botPokemon.getEffects().sum(attack.getEffects());
            menuPane.setDisable(false);
        }
        // playDialogAndWait("Il tuo " + playerPokemon.getName() + " usa " + move.getName());
    }

    private void onBotHealthTweenFinished(ActionEvent e) {
        menuPane.setDisable(false);
        Pokemon botPokemon = turnManager.getOpponent().getCurrentPokemon();
        // controlla se il pokemon e' morto
        if (botPokemon.isDead())
        {
            // TODO: play dead animation
            // distribuisci exp
            ArrayList<Pokemon> playerPokemons = game.getPlayer().getAvailablePokemons();
            for (Pokemon p : playerPokemons) {
                p.gainExp(botPokemon, playerPokemons.size());
                if (p.getExp() >= p.getExpToNextLevel()) {
                    p.levelUp();
                }
            }
            updatePokemons();
            Pokemon newBotPokemon = turnManager.handlePokemonChange();
            if (newBotPokemon != null) {
                botPokemonView.setPokemon(newBotPokemon);
                botInfoCard.update(newBotPokemon);
            }
            else {
                // il bot non ha piu' pokemon o si arrende
                game.increaseScore();
                scoreLabel.setText("punteggio: " + game.getScore());
                Pokemon captured = turnManager.playerCatchPokemon();
                if (captured != null) {
                    if (game.getPlayer().getTeamSize() < 6) {
                        game.getPlayer().addPokemon(captured);
                        togglePokemonView(true);
                    }
                }
                turnManager.generateNewOpponent();
                updatePokemons();
                updateMainMenu();
            }
        }
    }

    private void onMainGridClick(MenuOption option) {
        switch (option.getText()) {
            case "Fuggi":
                runAway();
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

    public void runAway() {
        SoundService.getSingleton().getBattleMusic().stop();
        if (game.getScore() > 0) {
            JPokeBattle.getInstance().setView("finish");
        } else {
            JPokeBattle.getInstance().setView("menu");
        }
    }

    private void handleCPUTurn() {
        menuPane.setDisable(true);
        menuPane.setDisable(false);
    }

    private void onSwitchPokemon(int slot, Pokemon pokemon) {
        if (!turnManager.isPlayerTurn())
            return;
        Trainer player = game.getPlayer();
        if (pokemon == player.getCurrentPokemon()) {
            togglePokemonView(false);
            return;
        }
        game.getPlayer().setCurrentPokemon(pokemon);
        updatePokemons();
        togglePokemonView(false);
        handleCPUTurn();
    }
}

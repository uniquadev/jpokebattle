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
import jpokebattle.enums.CPUActionType;
import jpokebattle.enums.MoveType;
import jpokebattle.gui.ArrowTextButton;
import jpokebattle.gui.GridMenu;
import jpokebattle.gui.HPokeBalls;
import jpokebattle.gui.PokeInfoCard;
import jpokebattle.gui.PokeTeamList;
import jpokebattle.gui.PokemonView;
import jpokebattle.gui.GridMenu.MenuOption;
import jpokebattle.model.AttackData;
import jpokebattle.model.CPUAction;
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

    private Pokemon catchingPokemon;
    private PokeMove learningMove;
    private boolean justWin = false;
    
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
    private Label pokemonLabel;

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
            catchingPokemon = null;
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
        Pokemon playerPokemon = player.getActivePokemon();
        Pokemon botPokemon = opponent.getActivePokemon();
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
        if (flag) {
            if (catchingPokemon != null) {
                pokemonLabel.setText("Vuoi catturare " + catchingPokemon.getName() + " L" + catchingPokemon.getLevel() + " ?");
            } else {
                pokemonLabel.setText("Quale Pokemon vuoi mandare in campo?");
            }
        }
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
            updateActionMenu(game.getPlayer().getActivePokemon());
        menuPane.setVisible(!flag);
        actionPane.setVisible(flag);
        moveInfoPane.setVisible(flag);
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

    private void onActionSelect(MenuOption option) {
        if (!turnManager.isPlayerTurn())
            return;
        Pokemon pokemon = game.getPlayer().getActivePokemon();
        PokeMove move = pokemon.getMove(option.getValue());
        moveTypeLabel.setText("TIPO/" + move.getElement().name());
        moveUsesLabel.setText(move.getUsesLeft() + "/" + move.getMaxUses());
    }

    private void onActionGridClick(MenuOption option) {
        if (!turnManager.isPlayerTurn())
            return;
        Pokemon playerPokemon = game.getPlayer().getActivePokemon();
        PokeMove move = playerPokemon.getMove(option.getValue());
        if (!move.canUse())
            return;
        toggleActionView(false);
        menuPane.setDisable(true);

        Trainer opponent = turnManager.getOpponent();
        Pokemon botPokemon = opponent.getActivePokemon();
        AttackData attack = turnManager.performAttack(playerPokemon, botPokemon, move);
        move.draw(this, playerPokemon, botPokemon);

        if (attack.getType() == MoveType.PHYSICAL) {
            botInfoCard.tweenHealth(this::onBotHealthTweenFinished);
        } else {
            handleCPUTurn();
        }
    }

    private void onBotHealthTweenFinished() {
        menuPane.setDisable(false);
        Pokemon botPokemon = turnManager.getOpponent().getActivePokemon();
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
                    PokeMove newMove = p.canLearnMove();
                    if (newMove != null) {
                        if(p.addMove(newMove) == null) {
                            // TODO: make the player choose a move to replace
                        };
                    }
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
                    }
                    else {
                        catchingPokemon = captured;
                    }
                    togglePokemonView(true);
                }
                justWin = true;
                turnManager.generateNewOpponent();
                updatePokemons();
                updateMainMenu();
            }
        }
        handleCPUTurn();
    }

    private void handleCPUTurn() {
        menuPane.setDisable(true);
        CPUAction action = turnManager.handleTurn();
        if (action.getType() == CPUActionType.ATTACK) {
            Pokemon defender = action.getPokemon();
            Pokemon attacker = turnManager.getOpponent().getActivePokemon();
            PokeMove move = action.getMove();
            move.draw(this, attacker, defender);
            if (move.getType() == MoveType.PHYSICAL) 
                playerInfoCard.tweenHealth(this::onPlayerHealthTweenFinished);
            else
                menuPane.setDisable(false);
        } else {
            if (action.getType() == CPUActionType.POKEMON_SWITCH)
                updatePokemons();
            menuPane.setDisable(false);
        }
    }

    private void onPlayerHealthTweenFinished() {
        menuPane.setDisable(false);
    }

    private void onSwitchPokemon(int slot, Pokemon pokemon) {
        if (!turnManager.isPlayerTurn())
            return;
        if (catchingPokemon != null) {
            game.getPlayer().setPokemon(slot, catchingPokemon);
            catchingPokemon = null;
            updatePokemons();
            togglePokemonView(false);
            return;
        }
        Trainer player = game.getPlayer();
        if (pokemon == player.getActivePokemon()) {
            togglePokemonView(false);
            return;
        }
        game.getPlayer().setActivePokemon(pokemon);
        updatePokemons();
        togglePokemonView(false);
        if (!justWin)
            handleCPUTurn();
        justWin = false;
    }
}

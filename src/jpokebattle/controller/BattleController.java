package jpokebattle.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
import jpokebattle.battle.BattleCommentor;
import jpokebattle.battle.BattleHandler;
import jpokebattle.enums.BattleActionType;
import jpokebattle.enums.MoveType;
import jpokebattle.gui.ArrowTextButton;
import jpokebattle.gui.GridMenu;
import jpokebattle.gui.HPokeBalls;
import jpokebattle.gui.HealthBar;
import jpokebattle.gui.PokeInfoCard;
import jpokebattle.gui.PokeTeamList;
import jpokebattle.gui.PokemonView;
import jpokebattle.gui.GridMenu.MenuOption;
import jpokebattle.model.MoveResult;
import jpokebattle.model.BattleAction;
import jpokebattle.model.PokeMove;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;
import jpokebattle.service.GameState;
import jpokebattle.service.SoundService;
//import jpokebattle.utils.OldTurnManager;

public class BattleController {

    private GameState game;
    private BattleHandler battleHandler;
    private Queue<BattleAction> actions;
    private BattleAction lastAction;
    private boolean freeSwitch = false;

    //private OldTurnManager turnManager;
    private SoundService sound;

    private Trainer player;
    private Pokemon catchingPokemon;
    private PokeMove learningMove;
    
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
        battleHandler = new BattleHandler(game);
        player = game.getPlayer();
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
        
        updatePokemons();
        startIntroAnimation();
    }

    private void updateMainMenu() {
        mainMenu.update(new MenuOption[]{
            new GridMenu.MenuOption("Azioni", true),
            new GridMenu.MenuOption("Pokemon", true),
            new GridMenu.MenuOption("Oggetti", false),
            new GridMenu.MenuOption("Fuggi", true)
        }, 2);
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
        Trainer opponent = battleHandler.getCPU();
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
        actionMenu.update(options, 2);
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
        SoundService.getSingleton().getBattleAudio().stop();
        if (game.getScore() > 0) {
            JPokeBattle.getInstance().setView("finish");
        } else {
            JPokeBattle.getInstance().setView("menu");
        }
    }

    private void onActionSelect(MenuOption option) {
        Pokemon pokemon = game.getPlayer().getActivePokemon();
        PokeMove move = pokemon.getMove(option.getValue());
        moveTypeLabel.setText("TIPO/" + move.getElement().name());
        moveUsesLabel.setText(move.getUsesLeft() + "/" + move.getMaxUses());
    }

    public HealthBar getHealthBar(Trainer trainer) {
        if (trainer == player) {
            return playerInfoCard.getHealthBar();
        } else {
            return botInfoCard.getHealthBar();
        }
    }

    private void onActionGridClick(MenuOption option) {
        Pokemon playerPokemon = game.getPlayer().getActivePokemon();
        PokeMove move = playerPokemon.getMove(option.getValue());
        if (!move.canUse())
            return;
        toggleActionView(false);
        menuPane.setDisable(true);

        BattleAction action = new BattleAction(game.getPlayer(), battleHandler.getCPU(), move);
        startTurn(action);
    }

    private void startTurn(BattleAction playerAction) {
        battleHandler.setPlayer1Action(playerAction);
        actions =  battleHandler.startTurn();
        menuPane.setDisable(true);
        
        handleAction(actions.poll());
    }

    private void handleAction(BattleAction action) {
        if (action == null) {
            menuPane.setDisable(false);
            return;
        }
        lastAction = action;

        MoveResult attack = action.performAction();
        HealthBar healthBar = getHealthBar(action.getOpponent());
        Trainer trainer = action.getTrainer();
        Trainer opponent = action.getOpponent();
        Pokemon attacker = trainer.getActivePokemon();
        PokeMove move = action.getMove();
        System.out.println(BattleCommentor.comment(action, attack));
        
        if (attack != null) {
            Pokemon defender = opponent.getActivePokemon();
            move.draw(this, attacker, defender);

            if (attack.getType() == MoveType.PHYSICAL) {
                healthBar.tweenHealth(defender, this::onHealthTweenFinished);
            } else {
                handleAction(actions.poll());
            }
        }
        else {
            if (action.getType() == BattleActionType.POKEMON_SWITCH) {
                updatePokemons();
            }
            handleAction(actions.poll());
        }

        
    }

    private void onHealthTweenFinished() {
        boolean wasCPU = lastAction.getTrainer() == battleHandler.getCPU();
        Trainer trainer = wasCPU ? battleHandler.getCPU() : player;
        Trainer opponent = wasCPU ? player : battleHandler.getCPU();
        Pokemon damanged = opponent.getActivePokemon();

        if (!damanged.isDead()) {
            handleAction(actions.poll());
            return;
        }
            
        if (!wasCPU) {
            ArrayList<Pokemon> attackers = trainer.getAvailablePokemons();
            for (Pokemon p : attackers) {
                p.gainExp(damanged, attackers.size());
                if (p.canLevelUp()) {
                    p.levelUp();
                    // try evolve
                    if (p.canEvolve()) {
                        p.evolve();
                        updatePokemons();
                    }
                    // try learn new move
                    PokeMove newMove = p.canLearnMove();
                    if (newMove != null) {
                        if(p.addMove(newMove) == null) {
                            // TODO: make the player choose a move to replace
                        };
                    }
                }
            }
            updatePokemons();

            Pokemon newPokemon = battleHandler.handlePokemonChange();
            if (newPokemon != null) {
                botPokemonView.setPokemon(newPokemon);
                botInfoCard.update(newPokemon);
            }
            else {
                // il giocatore ha vinto
                game.increaseScore();
                scoreLabel.setText("punteggio: " + game.getScore());
                Pokemon captured = battleHandler.playerCatchPokemon();
                if (captured != null) {
                    if (game.getPlayer().getTeamSize() < 6) {
                        game.getPlayer().addPokemon(captured);
                        System.out.println("Hai catturato " + captured.getName());
                    }
                    else {
                        catchingPokemon = captured;
                        togglePokemonView(true);
                    }
                }
                if (player.getAvailablePokemons().size() > 1) {
                    freeSwitch = true;
                }
                battleHandler.generateNewOpponent();
                updatePokemons();
            }
        } else {
            // la cpu ha ucciso il pokemon del giocatore
            var pokemons = player.getAvailablePokemons();
            if (pokemons.size() == 0) {
                // il giocatore ha perso
                runAway();
            } else {
                pokemonBackButton.setVisible(false);
                togglePokemonView(true);
            }
        }
        
        menuPane.setDisable(false);
    }

    private void onSwitchPokemon(int slot, Pokemon pokemon) {
        // select caught pokemon
        if (catchingPokemon != null) {
            game.getPlayer().setPokemon(slot, catchingPokemon);
            catchingPokemon = null;
            updatePokemons();
            togglePokemonView(true);
            return;
        }
        // ignore dead pokemons
        if (pokemon.isDead()) return;
        // ignore if the selected pokemon is already active
        Trainer player = game.getPlayer();
        if (pokemon == player.getActivePokemon()) {
            togglePokemonView(false);
            freeSwitch = false;
            return;
        }
        // allow player to change pokemon before the battle start
        if (!freeSwitch) {
            togglePokemonView(false);
            BattleAction action = new BattleAction(player, pokemon);
            startTurn(action);
        } else {
            player.setActivePokemon(pokemon);
            updatePokemons();
            togglePokemonView(false);
            freeSwitch = false;
        }
    }
}

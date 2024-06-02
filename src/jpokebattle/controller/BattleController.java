package jpokebattle.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import jpokebattle.JPokeBattle;
import jpokebattle.gui.GridMenu;
import jpokebattle.gui.GridMenu.MenuOption;
import jpokebattle.service.GameState;
import jpokebattle.utils.TrainerBuilder;

public class BattleController {
    
    @FXML
    StackPane bottomStackPane;

    @FXML
    Label battleLabel;

    @FXML
    GridMenu actionMenu;

    public void initialize() {
        bottomStackPane.setPrefHeight(JPokeBattle.HEIGHT * 0.3);
        battleLabel.setPrefWidth(JPokeBattle.WIDTH * 0.45);
        actionMenu.Initialize(new MenuOption[]{
            new GridMenu.MenuOption("Attacca", true),
            new GridMenu.MenuOption("Pokemon", true),
            new GridMenu.MenuOption("Oggetti", true),
            new GridMenu.MenuOption("Fuggi", false)
        }, 2);
        actionMenu.setArrowSize(25, 25);
        actionMenu.setAlignment(Pos.CENTER);
        startBattle();
    }

    public void startBattle() {
        GameState.getSingleton().setState(GameState.State.BATTLE);
        generateAI();
        // TODO: start intro animation (make method)
    }

    public void generateAI() {
        // TODO: implement difficulty based on score and player team level
        TrainerBuilder builder = new TrainerBuilder();
        GameState.getSingleton().setAI(builder.generate());
        System.out.println("AI generato!");
    }
}

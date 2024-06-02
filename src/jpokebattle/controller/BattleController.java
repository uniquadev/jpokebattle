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

public class BattleController {
    
    @FXML
    StackPane bottomStackPane;

    @FXML
    Label battleLabel;

    @FXML
    GridMenu actionMenu;

    public void initialize() {
        bottomStackPane.setPrefHeight(JPokeBattle.HEIGHT * 0.34);
        battleLabel.setPrefWidth(JPokeBattle.WIDTH * 0.45);
        actionMenu.Initialize(new MenuOption[]{
            new GridMenu.MenuOption("Attacca", true),
            new GridMenu.MenuOption("Pokemon", true),
            new GridMenu.MenuOption("Oggetti", true),
            new GridMenu.MenuOption("Fuggi", true)
        }, 2);
        actionMenu.setArrowSize(25, 25);
        actionMenu.setAlignment(Pos.CENTER);
        startBattle();
    }

    public void startBattle() {
        GameState.getSingleton().setState(GameState.State.BATTLE);
        // TODO: generate new AI (make method)
        // TODO: generate AI team
        // TODO: start intro animation (make method)
    }
}

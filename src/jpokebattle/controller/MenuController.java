package jpokebattle.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import jpokebattle.gui.GridMenu;
import jpokebattle.gui.GridMenu.MenuOption;

public class MenuController implements Initializable {

    @FXML
    private GridMenu gridMenu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridMenu.Initialize(new MenuOption[]{
            new MenuOption("Nuova Partita", true),
            new MenuOption("Carica Partita", false),
            new MenuOption("Classifica", true),
            new MenuOption("Opzioni", true),
            new MenuOption("Esci", true)
        }, 1);
    }
    
}

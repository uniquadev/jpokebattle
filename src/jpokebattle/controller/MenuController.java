package jpokebattle.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import jpokebattle.JPokeBattle;
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
            new MenuOption("Informazioni", true),
            new MenuOption("Esci", true)
        }, 1);
        gridMenu.setCallback(this::onClick);
    }
    
    public void onClick(MenuOption option) {
        switch (option.text) {
            case "Nuova Partita":
                // TODO
                break;
            case "Carica Partita":
                break;
            case "Opzioni":
                break;
            case "Informazioni":
                JPokeBattle.getInstance().setView("info");
                break;
            case "Esci":
                try {
                    Thread.sleep(670);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    System.exit(0);
                }
                break;
            default:
                break;
        }
    } 
}

package jpokebattle.controller;

import javafx.fxml.FXML;

import jpokebattle.JPokeBattle;
import jpokebattle.gui.GridMenu;
import jpokebattle.gui.GridMenu.MenuOption;

public class MenuController  {

    @FXML
    private GridMenu gridMenu;

    public void initialize() {
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
    
    private void onClick(MenuOption option) {
        switch (option.text) {
            case "Nuova Partita":
                JPokeBattle.getInstance().setView("setup");
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
                    Thread.sleep(370);
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

    private void caricaPartita() {
        
    }
}

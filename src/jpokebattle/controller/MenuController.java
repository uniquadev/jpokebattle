package jpokebattle.controller;

import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;
import jpokebattle.JPokeBattle;
import jpokebattle.gui.GridMenu;
import jpokebattle.gui.GridMenu.MenuOption;
import jpokebattle.service.SoundService;

public class MenuController  {

    @FXML
    private GridMenu gridMenu;

    public void initialize() {
        AudioClip titleMusic = SoundService.getSingleton().getTitleMusic();
        if (!titleMusic.isPlaying())
            titleMusic.play();

        gridMenu.Initialize(new MenuOption[]{
            new MenuOption("Nuova Partita", true),
            new MenuOption("Carica Partita", false),
            new MenuOption("Classifica", true),
            new MenuOption("Opzioni", true),
            new MenuOption("Informazioni", true),
            new MenuOption("Esci", true)
        }, 1);
        gridMenu.setClickCallback(this::onClick);
    }

    public void deinitialize() {
        
    }
    
    private void onClick(MenuOption option) {
        switch (option.getText()) {
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

package jpokebattle.controller;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import jpokebattle.JPokeBattle;
import jpokebattle.gui.GridMenu;
import jpokebattle.gui.GridMenu.MenuOption;
import jpokebattle.service.SoundService;
import jpokebattle.utils.SplashText;

public class MenuController  {

    @FXML
    private GridMenu gridMenu;
    @FXML
    private Label splashLabel;

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

        splashLabel.setText(SplashText.getText());;
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), splashLabel);
        scaleTransition.setFromX(1.2);
        scaleTransition.setToX(1.5);
        scaleTransition.setFromY(1.2);
        scaleTransition.setToY(1.5);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
        scaleTransition.setAutoReverse(true);

        // Start the animation
        scaleTransition.play();
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
            case "Classifica":
                JPokeBattle.getInstance().setView("leader");
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

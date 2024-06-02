package jpokebattle.service;

import javafx.scene.media.AudioClip;
import jpokebattle.JPokeBattle;

public class SoundService {

    // Singleton pattern
    static private SoundService singleton;
    static public SoundService getSingleton() {
        if (singleton == null) {
            singleton = new SoundService();
        }
        return singleton;
    }

    // Costruttore carica i suoni
    private SoundService() {
        purchaseSound = loadAudio("SFX_PURCHASE.wav");
        pressSound = loadAudio("SFX_PRESS_AB.wav");
    }

    private AudioClip purchaseSound;
    private AudioClip pressSound;

    private AudioClip loadAudio(String name) throws NullPointerException
    {
        AudioClip audioClip = new AudioClip(JPokeBattle.class.getResource("resources/sounds/" + name).toExternalForm());
        audioClip.setVolume(0.06);
        return audioClip;
    }

    // Metodi per riprodurre i suoni
    public void playPurchase() {
        purchaseSound.play();
    }
    public void playPress() {
        pressSound.play();
    }
}

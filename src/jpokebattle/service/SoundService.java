package jpokebattle.service;

import java.util.ArrayList;

import javafx.scene.media.AudioClip;
import jpokebattle.JPokeBattle;

public class SoundService {

    static private SoundService singleton;
    static public SoundService getSingleton() {
        synchronized (SoundService.class) {
            if (singleton == null) {
                singleton = new SoundService();
            }
        }
        return singleton;
    }

    public static enum SoundType {
        NONE,
        EFFECT,
        MUSIC
    }

    ArrayList<AudioClip> effects = new ArrayList<>();
    ArrayList<AudioClip> musics = new ArrayList<>();

    private AudioClip battleMusic;
    private AudioClip titleMusic;
    private AudioClip victoryMusic;
    private AudioClip purchaseSound;
    private AudioClip pressSound;

    // Costruttore carica i suoni
    private SoundService() {
        // musics
        battleMusic = loadAudio("trainer-battle-music.wav", SoundType.MUSIC);
        battleMusic.setCycleCount(AudioClip.INDEFINITE);
        titleMusic = loadAudio("title-screen-music.wav", SoundType.MUSIC);
        titleMusic.setCycleCount(AudioClip.INDEFINITE);
        victoryMusic = loadAudio("victory.mp3", SoundType.MUSIC);
        // effects
        purchaseSound = loadAudio("SFX_PURCHASE.wav", SoundType.EFFECT);
        pressSound = loadAudio("SFX_PRESS_AB.wav", SoundType.EFFECT);
    }

    private AudioClip loadAudio(String name, SoundType type) throws NullPointerException
    {
        AudioClip audioClip = new AudioClip(JPokeBattle.class.getResource("resources/sounds/" + name).toExternalForm());
        audioClip.setVolume(0.06);
        switch (type) {
            case EFFECT:
                effects.add(audioClip);
                break;
            case MUSIC:
                musics.add(audioClip);
                break;
            default:
                break;
        }
        return audioClip;
    }

    // Metodi per riprodurre i suoni
    public void playPurchase() {
        purchaseSound.play();
    }
    public void playPress() {
        pressSound.play();
    }
    public AudioClip getTitleMusic() {
        return titleMusic;
    }
    public AudioClip getBattleMusic() {
        return battleMusic;
    }
    public AudioClip getVictoryMusic() {
        return victoryMusic;
    }
}

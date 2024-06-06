package jpokebattle.service;

import java.util.ArrayList;

import javafx.scene.media.AudioClip;
import jpokebattle.JPokeBattle;

/**
 * The SoundService class is responsible for managing and playing audio clips in the game.
 */
public class SoundService {

    static private SoundService singleton;

    /**
     * Returns the singleton instance of the SoundService class.
     *
     * @return The singleton instance of the SoundService class.
     */
    static public SoundService getSingleton() {
        synchronized (SoundService.class) {
            if (singleton == null) {
                singleton = new SoundService();
            }
        }
        return singleton;
    }

    /**
     * Represents the type of sound.
     */
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

    /**
     * Constructs a SoundService object and loads the audio clips.
     */
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

    /**
     * Loads an audio clip and adds it to the appropriate list based on the sound type.
     *
     * @param name The name of the audio file.
     * @param type The type of sound.
     * @return The loaded AudioClip object.
     * @throws NullPointerException If the audio file is not found.
     */
    private AudioClip loadAudio(String name, SoundType type) throws NullPointerException {
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

    /**
     * Plays the purchase sound.
     */
    public void playPurchase() {
        purchaseSound.play();
    }

    /**
     * Plays the press sound.
     */
    public void playPress() {
        pressSound.play();
    }

    /**
     * Returns the title music audio clip.
     *
     * @return The title music audio clip.
     */
    public AudioClip getTitleMusic() {
        return titleMusic;
    }

    /**
     * Returns the battle music audio clip.
     *
     * @return The battle music audio clip.
     */
    public AudioClip getBattleMusic() {
        return battleMusic;
    }

    /**
     * Returns the victory music audio clip.
     *
     * @return The victory music audio clip.
     */
    public AudioClip getVictoryMusic() {
        return victoryMusic;
    }
}

package jpokebattle.utils;

/**
 * The SplashText class provides a collection of splash texts that can be randomly retrieved.
 */
public class SplashText {
    private static final String[] texts = {
        "90% bug free!",
        "JPokeBattle !!",
        "Now in 2D!",
        "Yes, sir!",
        "Also try Terraria",
        "Also try Minecraft",
        "Ahhhhhh",
        "Sapienza best",
        "It's a game!",
        "Check it out!",
        "Made in Italy!",
        "NAH, I'D WIN."
    };

    /**
     * Returns a random splash text from the collection.
     *
     * @return a random splash text
     */
    public static String getText() {
        return texts[(int) (Math.random() * (texts.length - 1))];
    }
}

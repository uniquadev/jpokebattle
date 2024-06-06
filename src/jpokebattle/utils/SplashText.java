package jpokebattle.utils;

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

    public static String getText() {
        return texts[(int) (Math.random() * (texts.length - 1))];
    }
}

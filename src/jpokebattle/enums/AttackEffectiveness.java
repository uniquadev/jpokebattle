package jpokebattle.enums;

public enum AttackEffectiveness {
    MISS,
    NOT_EFFECTIVE,
    NORMAL,
    EFFECTIVE;

    public String getText() {
        switch (this) {
            case MISS:
                return "ma il colpo non e' andato a buon fine";
            case NOT_EFFECTIVE:
                return "ma il colpo non e' molto efficace";
            case NORMAL:
                return "";
            case EFFECTIVE:
                return " facendo molti danni!";
            default:
                return "";
        }
    }

    public double getEffectiveness() {
        switch (this) {
            case MISS:
                return 0;
            case NOT_EFFECTIVE:
                return 0.5;
            case NORMAL:
                return 1;
            case EFFECTIVE:
                return 2;
            default:
                return 0;
        }
    }
}

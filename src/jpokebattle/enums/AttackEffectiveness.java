package jpokebattle.enums;

public enum AttackEffectiveness {
    MISS,
    NOT_EFFECTIVE,
    NORMAL,
    EFFECTIVE;

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

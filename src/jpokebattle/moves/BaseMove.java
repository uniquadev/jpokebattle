package jpokebattle.moves;

import jpokebattle.utils.PokeElement;

public class BaseMove {
    private int uses = 0;
    
    public PokeElement getElement() {
        return PokeElement.NORMAL;
    }

    public int getPower() {
        return 0;
    }

    public int getAccuracy() {
        return 100;
    }

    public String getName() {
        return "BaseMove";
    }

    public int getUses() {
        return uses;
    }

    public int getMaxUses() {
        return 10;
    }
}

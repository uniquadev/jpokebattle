package jpokebattle.model;

import jpokebattle.utils.PokeElement;
import jpokebattle.utils.PokeEnum;

public class PokeData {
    private PokeEnum id;
    private String name;
    private PokeElement element;
    private boolean isFemale;
    private boolean isLegendary;

    public PokeData(PokeEnum id, String name, PokeElement element, boolean isFemale, boolean isLegendary) {
        this.id = id;
        this.name = name;
        this.element = element;
        this.isFemale = isFemale;
        this.isLegendary = isLegendary;
    }

    public String getName() {
        return name;
    }

    public PokeEnum getId() {
        return id;
    }

    public PokeElement getElement() {
        return element;
    }
}

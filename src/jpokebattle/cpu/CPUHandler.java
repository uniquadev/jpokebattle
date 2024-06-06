package jpokebattle.cpu;

import jpokebattle.interfaces.CPUInterface;
import jpokebattle.model.Trainer;

public abstract class CPUHandler implements CPUInterface {
    protected Trainer trainer;
    public CPUHandler(Trainer trainer) {
        this.trainer = trainer;
    }
}

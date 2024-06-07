package jpokebattle.cpu;

import java.util.Random;

import jpokebattle.interfaces.CPUInterface;
import jpokebattle.model.Trainer;

public abstract class CPUHandler implements CPUInterface {
    protected Trainer trainer;
    protected Trainer opponent;
    protected Random random = new Random();
    public CPUHandler(Trainer trainer, Trainer opponent) {
        this.trainer = trainer;
    }
}

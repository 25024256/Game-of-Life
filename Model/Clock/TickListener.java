package Model.Clock;

public interface TickListener {
    /**
     * Elk object dat vanaf nu deze interface implementeert, belooft aan Java:
     * Ik heb een onTick methode, dus je mag mij een seintje geven als de tijd verstrijkt
     */
    void onTick(long tickNumber);
}
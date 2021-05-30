package io.muic.ooc.fab;

public class FoxObserver extends Observer{
    private int population;
    public FoxObserver(Subject subject){
        population = 0;
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        population++;
    }
    public void update(int val) {
        population=val;
    }
    public int getPopulation() {
        return population;
    }
}

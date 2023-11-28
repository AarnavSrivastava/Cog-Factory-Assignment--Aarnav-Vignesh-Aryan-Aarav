package Cog_Factory;

public class Worker implements Comparable<Worker> {
    private String name;
    private int cogsPerHour;
    private int cogsProduced;

    private int cogsWasted;
    private int cogsInProgress;

    public Worker(String name, int cph) {
        this.name = name;
        cogsPerHour = cph;

        cogsProduced = 0;
        cogsWasted = 0;
        cogsInProgress = 0;
    }

    @Override
    public int compareTo(Worker o) {
        return cogsPerHour == o.cogsPerHour ? 0 : (cogsPerHour > o.cogsPerHour) ? 1 : -1;
    }

    public int getCogsPerHour() {
        return cogsPerHour;
    }

    public int getCogsProduced() {
        return cogsProduced;
    }

    public int getCogsWasted() {
        return cogsWasted;
    }

    public int getCogsInProgress() {
        return cogsInProgress;
    }

    public String getName() {
        return name;
    }

    public void reset() {
        cogsProduced = 0;
        cogsWasted = 0;
        cogsInProgress = 0;
    }

    public boolean isBusy() {
        return cogsInProgress > 0;
    }

    public void assignOrder(int x) {
        cogsInProgress = x;
    }

    public void workOneHour() {
        if (cogsInProgress > cogsPerHour) {
            cogsInProgress -= cogsPerHour;
            cogsProduced += cogsPerHour;
        } else {
            cogsProduced += cogsInProgress;
            cogsWasted += cogsInProgress;
        }

        if (cogsInProgress < 0) {
            cogsInProgress = 0;
        }
    }

    @Override
    public String toString() {
        return name + ": \nCogs In Progress:" + cogsInProgress + "\n" + cogsPerHour + "\t" + cogsPerHour + "\t";
    }
}

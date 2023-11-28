package Cog_Factory;

import java.util.Comparator;

public class CogOrderComparator implements Comparator<Worker>{

    @Override
    public int compare(Worker w1, Worker w2) {
        return w1.getCph() - w2.getCph();
    }
    
}

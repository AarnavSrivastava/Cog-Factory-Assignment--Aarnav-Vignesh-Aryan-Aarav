package Cog_Factory;

import java.util.Comparator;

public class WorkerComparator implements Comparator<Worker>{

    @Override
    public int compare(Worker w1, Worker w2) {
        return w2.getCph() - w1.getCph();
    }
    
}

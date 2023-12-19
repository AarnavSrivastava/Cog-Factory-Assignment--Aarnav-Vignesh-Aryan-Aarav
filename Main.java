package Cog_Factory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        ArrayList<Worker> workers = new ArrayList<>();
        Queue<Integer> cogs = new LinkedList<>();
        workers.add(new Worker("A", 30));
        workers.add(new Worker("B", 25));
        
        cogs.add(60);
        cogs.add(40);
        cogs.add(30);
        cogs.add(20);
        cogs.add(35);
        cogs.add(80);
        FactorySimulator simulation = new FactorySimulator(workers, cogs);
        simulation.runRegular();    


        // workers = new ArrayList<>();
        // cogs = new LinkedList<>();
        // workers.add(new Worker("A", 30));
        // workers.add(new Worker("B", 25));
        
        // cogs.add(60);
        // cogs.add(40);
        // cogs.add(30);
        // cogs.add(20);
        // cogs.add(35);
        // cogs.add(80);

        // FactorySimulator simulation2 = new FactorySimulator(workers, cogs);
        // simulation2.run2();       
        
    }
}

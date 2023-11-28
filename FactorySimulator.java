package Cog_Factory;

import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;

public class FactorySimulator {
    
    
    private ArrayList<Worker> workers;
    private Queue<Integer> cogOrders;
    private PriorityQueue<Worker> workerQueue = new PriorityQueue<>(new WorkerComparator());    

    public FactorySimulator(ArrayList<Worker> w, Queue<Integer> c){
        this.workers = w;
        this.cogOrders = c;
        for (Worker worker : workers){
            workerQueue.add(worker);
        }
    }

    // Run factory Simulation
    public void run() {
        
        while (!workerQueue.isEmpty() && !cogOrders.isEmpty()) {
            Worker worker = workerQueue.poll();
            worker.assignOrder(cogOrders.poll());
        }
        
        while (!cogOrders.isEmpty()) {
            for (Worker worker : workers) {
                if (worker.isBusy()) {
                    System.out.println("Worker " + worker.getNames() + " is busy and has " + worker.getCogsInProgress() + " cogs in progress");
                    worker.work();
                } else {
                    if (!cogOrders.isEmpty()) {
                        System.out.println("Worker " + worker.getNames() + " is idle and has " + worker.getCogsInProgress() + " cogs in progress. They have completed " + worker.getTotalCogsProduced() + " cogs and wasted " + worker.getTotalWaste() + " cogs");
                        worker.assignOrder(cogOrders.poll());
                        worker.work();
                        System.out.println("Worker " + worker.getNames() + " is busy and has " + worker.getCogsInProgress() + " cogs in progress");
                
                    }
                }
            }
        }
        boolean allWorkersIdle = false;
        while (!allWorkersIdle) {
            for (Worker worker : workers) {
                if (worker.isBusy()) {
                    worker.work();
                    System.out.println("Worker " + worker.getNames() + " is busy and has " + worker.getCogsInProgress() + " cogs in progress");
                } else {
                    allWorkersIdle = true;
                    System.out.println("Worker " + worker.getNames() + " is idle and has " + worker.getCogsInProgress() + " cogs in progress. They have completed " + worker.getTotalCogsProduced() + " cogs and wasted " + worker.getTotalWaste() + " cogs");
                }
            }  
        }

        // Compute Statistics
        System.out.println("Statistics: ");
        double totalWaste = 0;
        for (Worker worker : workers) {
            totalWaste += worker.getTotalWaste();
        }
        System.out.println("Average Waste: " + (totalWaste/((double) workers.size())));
        
    }
}

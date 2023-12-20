package Cog_Factory;

import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Collections;

import java.util.LinkedList;

public class FactorySimulator {
    
    
    private ArrayList<Worker> workers;
    private Queue<Integer> cogOrders;
    private PriorityQueue<Worker> workerQueue = new PriorityQueue<>(new WorkerComparator());    

    public FactorySimulator(ArrayList<Worker> w, ArrayList<Integer> c){
        this.workers = w;

        this.cogOrders = new LinkedList<Integer>();
        this.cogOrders.addAll(c);

        for (Worker worker : workers){
            workerQueue.add(worker);
        }
    }

    // Run Basic factory Simulation
    public double[] runRegular() {
        int hoursWorked = 0;
        
        while (!workerQueue.isEmpty() && !cogOrders.isEmpty()) {
            Worker worker = workerQueue.poll();
            worker.assignOrder(cogOrders.poll());
        }

        while (!cogOrders.isEmpty() || !workersDone(workers)) {
            for (Worker worker : workers) {
                if (worker.isBusy()) {
                    System.out.println("Worker " + worker.getNames() + " is busy and has " + worker.getCogsInProgress() + " cogs in progress");
                } else {
                    if (!cogOrders.isEmpty()) {
                        System.out.println("Worker " + worker.getNames() + " is to be assigned a new order and has " + worker.getCogsInProgress() + " cogs in progress. They have completed " + worker.getTotalCogsProduced() + " cogs and wasted " + worker.getTotalWaste() + " cogs");
                        worker.assignOrder(cogOrders.poll());
                        System.out.println("Worker " + worker.getNames() + " is busy and has " + worker.getCogsInProgress() + " cogs in progress");
                    } else {
                        System.out.println("Worker " + worker.getNames() + " is idle and has " + worker.getCogsInProgress() + " cogs in progress. They have completed " + worker.getTotalCogsProduced() + " cogs and wasted " + worker.getTotalWaste() + " cogs");
                    }
                }

                worker.work();
            }

            hoursWorked++;
        }

        // print end stats for each worker
        for (Worker worker : workers) {
            System.out.println("Worker " + worker.getNames() + " is idle and has " + worker.getCogsInProgress() + " cogs in progress. They have completed " + worker.getTotalCogsProduced() + " cogs and wasted " + worker.getTotalWaste() + " cogs");
        }

        // Compute Statistics
        System.out.println("Statistics: ");
        double totalWaste = 0;
        for (Worker worker : workers) {
            totalWaste += worker.getTotalWaste();
        }
        System.out.println("Average Waste: " + (totalWaste/((double) workers.size())));
        System.out.println("Hours Worked: " + hoursWorked);

        return new double[] { (totalWaste/((double) workers.size())), hoursWorked };
    }

    private boolean workersDone(ArrayList<Worker> workers2) {
        for (Worker worker : workers2) {
            if (worker.isBusy()) {
                return false;
            }
        }

        return true;
    }

    //Assign largest inital orders to best CPH workers initally
    public double[] run2() {
        int hoursWorked = 0;
        PriorityQueue<Integer> orders = new PriorityQueue<Integer>();
        while(!cogOrders.isEmpty())
        {
           orders.add(cogOrders.poll());
        }
        
        while (!workerQueue.isEmpty() && !orders.isEmpty()) {
            Worker worker = workerQueue.poll();
            worker.assignOrder(orders.poll());
        }

        while (!orders.isEmpty() || !workersDone(workers)) {
            for (Worker worker : workers) {
                if (worker.isBusy()) {
                    System.out.println("Worker " + worker.getNames() + " is busy and has " + worker.getCogsInProgress() + " cogs in progress");
                } else {
                    if (!orders.isEmpty()) {
                        System.out.println("Worker " + worker.getNames() + " is to be assigned a new order and has " + worker.getCogsInProgress() + " cogs in progress. They have completed " + worker.getTotalCogsProduced() + " cogs and wasted " + worker.getTotalWaste() + " cogs");
                        worker.assignOrder(orders.poll());
                        System.out.println("Worker " + worker.getNames() + " is busy and has " + worker.getCogsInProgress() + " cogs in progress");
                    } else {
                        System.out.println("Worker " + worker.getNames() + " is idle and has " + worker.getCogsInProgress() + " cogs in progress. They have completed " + worker.getTotalCogsProduced() + " cogs and wasted " + worker.getTotalWaste() + " cogs");
                    }
                }

                worker.work();
            }

            hoursWorked++;
        }

        // print end stats for each worker
        for (Worker worker : workers) {
            System.out.println("Worker " + worker.getNames() + " is idle and has " + worker.getCogsInProgress() + " cogs in progress. They have completed " + worker.getTotalCogsProduced() + " cogs and wasted " + worker.getTotalWaste() + " cogs");
        }

        // Compute Statistics
        System.out.println("Statistics: ");
        double totalWaste = 0;
        for (Worker worker : workers) {
            totalWaste += worker.getTotalWaste();
        }
        System.out.println("Average Waste: " + (totalWaste/((double) workers.size())));
        System.out.println("Hours Worked: " + hoursWorked);

        
        return new double[] { (totalWaste/((double) workers.size())), hoursWorked };
    }
}

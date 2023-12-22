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
                    // System.out.println("Worker " + worker.getNames() + " is busy and has " + worker.getCogsInProgress() + " cogs in progress");
                } else {
                    if (!cogOrders.isEmpty()) {
                        // System.out.println("Worker " + worker.getNames() + " is to be assigned a new order and has " + worker.getCogsInProgress() + " cogs in progress. They have completed " + worker.getTotalCogsProduced() + " cogs and wasted " + worker.getTotalWaste() + " cogs");
                        worker.assignOrder(cogOrders.poll());
                        // System.out.println("Worker " + worker.getNames() + " is busy and has " + worker.getCogsInProgress() + " cogs in progress");
                    } else {
                        // System.out.println("Worker " + worker.getNames() + " is idle and has " + worker.getCogsInProgress() + " cogs in progress. They have completed " + worker.getTotalCogsProduced() + " cogs and wasted " + worker.getTotalWaste() + " cogs");
                    }
                }

                worker.work();
            }

            hoursWorked++;
        }

        // print end stats for each worker
        for (Worker worker : workers) {
            // System.out.println("Worker " + worker.getNames() + " is idle and has " + worker.getCogsInProgress() + " cogs in progress. They have completed " + worker.getTotalCogsProduced() + " cogs and wasted " + worker.getTotalWaste() + " cogs");
        }

        // Compute Statistics
        // System.out.println("Statistics: ");
        double totalWaste = 0;
        double totalCogsProduced = 0;
        double avratioPerWorker = 0;
        double workerCount = 0;
        for (Worker worker : workers) {
            totalCogsProduced += worker.getTotalCogsProduced();
            totalWaste += worker.getTotalWaste();
            workerCount+=1;
            avratioPerWorker += worker.getTotalWaste()/worker.getTotalCogsProduced();
        }
        // System.out.println("Average Waste: " + (totalWaste/((double) workers.size())));
        // System.out.println("Hours Worked: " + hoursWorked);

        return new double[] { (totalWaste/((double) workers.size())), hoursWorked, totalCogsProduced, avratioPerWorker/workerCount};
    }

    private boolean workersDone(ArrayList<Worker> workers2) {
        for (Worker worker : workers2) {
            if (worker.isBusy()) {
                return false;
            }
        }

        return true;
    }

    private boolean allOccupied(ArrayList<Worker> workers2) {
        for (Worker worker : workers2) {
            if (!worker.isBusy()) {
                return false;
            }
        }

        return true;
    }

    public int distanceFromWhole(double d)
    {
        d = d*1000;
        int dd = (int)d;
        int per = dd % 1000;
        
        if(1000-per <= per)
        {
            return 1000-per;
        }
        
        return per;
    }

    public int distanceFromWholeLarge(double d)
    {
        d = d*1000;
        int dd = (int)d;
        int per = dd % 1000;
        
        return 1000-per;
    }


    //Assign largest inital orders to best CPH workers initally
    public double[] run2() {
        int hoursWorked = 0;
        ArrayList<Integer> orders = new ArrayList<>();
        while(!cogOrders.isEmpty())
        {
           orders.add(cogOrders.poll());
        }

        Collections.sort(orders, Collections.reverseOrder());

        System.out.println(orders);
        ArrayList<Worker> workersList = new ArrayList<>();
        
        while (!workerQueue.isEmpty()) {
            Worker worker = workerQueue.poll();
            workersList.add(worker);
        }

        Collections.reverse(workersList);

        for (Worker worker : workersList) {
            
            int lowestIndex = 0;
            double lowestRemainder = Double.MAX_VALUE;
            
            for (int i = 0; i < orders.size(); i++) {
                int order = orders.get(i);

                double remainder = (double)order / (double)worker.getCph();

                if (distanceFromWholeLarge(remainder) < lowestRemainder) {
                    lowestRemainder = distanceFromWholeLarge(remainder);
                    lowestIndex = i;
                }
            }
            System.out.println(lowestRemainder);

            worker.assignOrder(orders.remove(lowestIndex));
        }

        while (!orders.isEmpty() || !workersDone(workers)) {
            
            int c = 0;
            for(Worker worker : workers)
            {
                if(!worker.isBusy())
                {
                    c++;
                }
            }

            for (Worker worker : workers) {
                if (worker.isBusy()) {
                    // System.out.println("Worker " + worker.getNames() + " is busy and has " + worker.getCogsInProgress() + " cogs in progress");
                } else {
                    if (!orders.isEmpty()) {
                        // System.out.println("Worker " + worker.getNames() + " is to be assigned a new order and has " + worker.getCogsInProgress() + " cogs in progress. They have completed " + worker.getTotalCogsProduced() + " cogs and wasted " + worker.getTotalWaste() + " cogs");
                        int lowestIndex = 0;
                        double lowestRemainder = Double.MAX_VALUE;
                        
                        if(c > orders.size())
                        {
                            c = orders.size();
                        }
                        for (int i = 0; i < c; i++) {
                            int order = orders.get(i);

                            double remainder = (double)order / (double)worker.getCph();

                            if (distanceFromWholeLarge(remainder) < lowestRemainder) {
                                lowestRemainder = distanceFromWholeLarge(remainder);
                                lowestIndex = i;
                            }
                        }
                        System.out.println(lowestRemainder);

                        worker.assignOrder(orders.remove(lowestIndex));

                        // System.out.println("Worker " + worker.getNames() + " is busy and has " + worker.getCogsInProgress() + " cogs in progress");
                    } else {
                        // System.out.println("Worker " + worker.getNames() + " is idle and has " + worker.getCogsInProgress() + " cogs in progress. They have completed " + worker.getTotalCogsProduced() + " cogs and wasted " + worker.getTotalWaste() + " cogs");
                    }
                }

                worker.work();
            }

            hoursWorked++;
        }

        // print end stats for each worker
        for (Worker worker : workers) {
            // System.out.println("Worker " + worker.getNames() + " is idle and has " + worker.getCogsInProgress() + " cogs in progress. They have completed " + worker.getTotalCogsProduced() + " cogs and wasted " + worker.getTotalWaste() + " cogs");
        }

        // Compute Statistics
        // System.out.println("Statistics: ");
        double totalWaste = 0;
        double totalCogsProduced = 0;
        double avratioPerWorker = 0;
        double workerCount = 0;
        for (Worker worker : workers) {
            totalWaste += worker.getTotalWaste();
            totalCogsProduced += worker.getTotalCogsProduced();
            workerCount+=1.0;
            avratioPerWorker += worker.getTotalWaste()/worker.getTotalCogsProduced();
        }
        System.out.println("Average Waste: " + (totalWaste/((double) workers.size())));
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Ratio Worked: " + avratioPerWorker/workerCount);

        
        return new double[] { (totalWaste/((double) workers.size())), hoursWorked, totalCogsProduced, avratioPerWorker/workerCount};
    }
}

package Cog_Factory;

import java.util.*;

public class FactorySimulator {
    private ArrayList<Worker> workers;

    private Queue<Integer> cogOrders;
    private ArrayList<Integer> cogList;

    public FactorySimulator(ArrayList<Worker> workers, Queue<Integer> cogs) {
        this.workers = workers;
        this.cogList = cogs;

        resetQueue();
    }

    public void resetQueue() {
        cogOrders = new LinkedList<Integer>();

        for (Integer i : cogList)
            cogOrders.add(i);
    }

    public void run() {
        int hours = 0;

        while(!cogOrders.isEmpty())
        {
            for(int i = 0; i < workers.size(); i++) {
                if(!workers.get(i).isBusy() && !cogOrders.isEmpty())
                    workers.get(i).assignOrder(cogOrders.poll());
                
                workers.get(i).workOneHour();
            }
            hours++;
        }

        while(!allDone()){
            for (Worker w: workers)
                w.workOneHour();
            
            hours++;
        }

        printResults(hours);
    }

    private void printResults(int hours) {
        System.out.println("Took " + hours + " hrs");

        for (Worker w : workers) {
            System.out.println(w.toString());
        }
    }

    private boolean allDone() {
        for (Worker w : workers) {
            if (w.isBusy()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
    }
}

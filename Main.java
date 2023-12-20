package Cog_Factory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // ArrayList<Worker> workers = new ArrayList<>();
        // Queue<Integer> cogs = new LinkedList<>();
        // workers.add(new Worker("A", 30));
        // workers.add(new Worker("B", 25));
        
        // cogs.add(60);
        // cogs.add(40);
        // cogs.add(30);
        // cogs.add(20);
        // cogs.add(35);
        // cogs.add(80);
        // FactorySimulator simulation = new FactorySimulator(workers, cogs);
        // simulation.runRegular();    


        ArrayList<Integer> allHoursBaseSim = new ArrayList<Integer>();
        ArrayList<Double> allWasteBaseSim = new ArrayList<Double>();

        ArrayList<Integer> allHours2 = new ArrayList<Integer>();
        ArrayList<Double> allWaste2 = new ArrayList<Double>();

        ArrayList<Integer> allHours3 = new ArrayList<Integer>();
        ArrayList<Double> allWaste3 = new ArrayList<Double>();

        for(int i = 0; i < 10000; i++)
        {
            Queue<Integer> cogss = new LinkedList<>();
            ArrayList<Worker> workerss = new ArrayList<>();
            Random ran = new Random();
            int numOfWorkers = ran.nextInt(50) + 1;
            int numOfCogOrders = ran.nextInt(500) +1;

            for(int j = 0; j< numOfWorkers; j++)
            {
                int workerEfficancy = ran.nextInt(100)+1;
                workerss.add(new Worker("" + j, workerEfficancy));
            }

            for(int j = 0; j< numOfCogOrders; j++)
            {
                int numOfCogs = ran.nextInt(100)+1;
                cogss.add(numOfCogs);
            }
            

            FactorySimulator simulation = new FactorySimulator(workerss, cogss);
            FactorySimulator simulation2 = new FactorySimulator(workerss, cogss);
            FactorySimulator simulation3 = new FactorySimulator(workerss, cogss);
            
            //RUN THE SIM METHODS RIGHT HERE
            String result1 = simulation.runRegular(); 
            String[] ra1 = result1.split(":");
            double waste = Double.parseDouble(ra1[0]);
            int har = Integer.parseInt(ra1[1]);
            allHoursBaseSim.add(har);
            allWasteBaseSim.add(waste);
            
            //VIGGY CHANGE RUN NAME TO YOUR SECOND METHOD HERE
            String result2 = simulation2.runRegular(); 
            String[] ra2 = result1.split(":");
            double waste2 = Double.parseDouble(ra2[0]);
            int har2 = Integer.parseInt(ra2[1]);
            allHours2.add(har2);
            allWaste2.add(waste2);
            
            //VIGGY CH
            String result3 = simulation3.runRegular(); 
            String[] ra3 = result1.split(":");
            double waste3 = Double.parseDouble(ra3[0]);
            int har3 = Integer.parseInt(ra3[1]);
            allHours3.add(har3);
            allWaste3.add(waste3);



        }


        double totalBaseWaste = 0;
        int totalBaseHours = 0;

        double total2Waste = 0;
        int total2Hours = 0;

        double total3Waste = 0;
        int total3Hours = 0;

        for(int i = 0; i < 10000; i++)
        {
            totalBaseWaste += allWasteBaseSim.get(i);
            totalBaseHours += allHoursBaseSim.get(i);

            total2Waste += allWaste2.get(i);
            total2Hours += allHours2.get(i);

            total3Waste += allWaste3.get(i);
            total3Hours += allHours3.get(i);

        }

        System.out.println("\nAverage waste after 10,000 runs for Base Model \t\t\t\t: " + totalBaseWaste/10000);
        System.out.println("Average number of hours after 10,000 runs for Base Model \t\t: " + totalBaseHours/10000);

        System.out.println("\nAverage waste after 10,000 runs for the Second Model\t\t\t: " + total2Waste/10000);
        System.out.println("Average number of hours after 10,000 runs for the Second Model \t\t: " + total2Hours/10000);

        System.out.println("\nAverage waste after 10,000 runs for the Third Model\t\t\t: " + total3Waste/10000);
        System.out.println("Average number of hours after 10,000 runs for the Third Model \t\t: " + total3Hours/10000);
        
    }
}

package Cog_Factory;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        long t0 = System.nanoTime();
        double totalWaste1 = 0;
        double totalTime1 = 0;
        double totalCogsProduced1 = 0;
        double totalRatio1 = 0;

        double totalWaste2 = 0;
        double totalTime2 = 0;
        double totalCogsProduced2 = 0;
        double totalRatio2 = 0;

        int numberOfTrials = 10000;
        int numberOfOrders = 100;
        double targetPercentageOrders = 0.97;

        int averageProductionRate = 35;
        int variation = 20;
        int minProductionRate = 15;
        int maxProductionRate = 55;

        for (int i = 0; i < numberOfTrials; i++) {
            ArrayList<Worker> workers = new ArrayList<>();
            ArrayList<Integer> cogOrders = new ArrayList<>();

            int numberOfWorkers = (int) (Math.random() * 6) + 3;

            for (int j = 1; j <= numberOfWorkers; j++) {
                int cph = 0;

                Random random = new Random();

                int randomVariation = random.nextInt(2 * variation + 1) - variation;
                int actualProductionRate = averageProductionRate + randomVariation;
                cph = actualProductionRate;
                // = Math.max(minProductionRate, Math.min(maxProductionRate, actualProductionRate));

                workers.add(new Worker(j+"", cph));
            }

            for (int j = 0; j < numberOfOrders; j++) {
                Random random = new Random();
                double randomValue = random.nextDouble();

                int cogs = 0;

                System.out.println("RANDOM VALUE " + randomValue);
                System.out.println(randomValue < targetPercentageOrders);
                if (randomValue < targetPercentageOrders) {
                    int randomNumber = getRandomNumberInRange(20, 100);
                    cogs = randomNumber;
                } else {
                    int randomNumber;
                    do {
                        randomNumber = random.nextInt(120) + 1; // You can also use nextInt(max) to limit the range
                    } while (randomNumber >= 20 && randomNumber <= 100);
                    cogs = randomNumber;
                }

                System.out.println("COGS: " + cogs);

                cogOrders.add(cogs);
            }

            ArrayList<Worker> workerss = workers;
            ArrayList<Integer> cogOrderss = cogOrders;

            FactorySimulator sim1 = new FactorySimulator(workers, cogOrders);
            FactorySimulator sim2 = new FactorySimulator(workerss, cogOrderss);

            double[] result = sim2.runRegular();
            totalWaste1 += result[0];
            totalTime1 += result[1];
            totalCogsProduced1 += result[2];
            totalRatio1 += result[3];

            double[] result2 = sim1.run2();
            //double[] result2 = {2, 2};
            totalWaste2 += result2[0];
            totalTime2 += result2[1];
            totalCogsProduced2 += result2[2];
            totalRatio2 += result2[3];
        }

        System.out.println("\nAverage waste after " + numberOfTrials + " runs for Base Model: " + totalWaste1/((double) numberOfTrials));
        System.out.println("Average number of hours after " + numberOfTrials + " runs for Base Model: " + totalTime1/((double) numberOfTrials));
        System.out.println("Average number of Cogs Produced " + numberOfTrials + " runs for Base Model: " + totalCogsProduced1/((double) numberOfTrials));
        System.out.println("Average worker waste/produced ratio " + numberOfTrials + " runs for Base Model: " + (totalRatio1/((double) numberOfTrials))/2);

        System.out.println("\nAverage waste after " + numberOfTrials + " runs for Second Model: " + (totalWaste2/((double) numberOfTrials))/2);
        System.out.println("Average number of hours after " + numberOfTrials + " runs for Second Model: " + totalTime2/((double) numberOfTrials));
        System.out.println("Average number of Cogs Produced " + numberOfTrials + " runs for Second Model: " + (totalCogsProduced2/((double) numberOfTrials))/2);
        System.out.println("Average worker waste/produced ratio " + numberOfTrials + " runs for Second Model: " + (totalRatio2/((double) numberOfTrials))/2);

        long t1 = System.nanoTime();

        System.out.println("\nTime to run: " + ((double) (t1-t0)/1_000_000_000.0) + " seconds");
    }

    static int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}

package Cog_Factory;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        double totalWaste1 = 0;
        double totalTime1 = 0;

        double totalWaste2 = 0;
        double totalTime2 = 0;

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
                cph = actualProductionRate = Math.max(minProductionRate, Math.min(maxProductionRate, actualProductionRate));

                workers.add(new Worker(j+"", cph));
            }

            for (int j = 0; j < numberOfOrders; j++) {
                Random random = new Random();
                double randomValue = random.nextDouble();

                int cogs = 0;

                if (randomValue < targetPercentageOrders) {
                    int randomNumber = getRandomNumberInRange(20, 100);
                    System.out.println("Chosen number: " + randomNumber);
                } else {
                    int randomNumber;
                    do {
                        randomNumber = random.nextInt(); // You can also use nextInt(max) to limit the range
                    } while (randomNumber >= 20 && randomNumber <= 100);
                }

                cogOrders.add(cogs);
            }

            FactorySimulator sim1 = new FactorySimulator(workers, cogOrders);
            FactorySimulator sim2 = new FactorySimulator(workers, cogOrders);

            double[] result = sim1.runRegular();
            totalWaste1 += result[0];
            totalTime1 += result[1];

            double[] result2 = sim2.run2();
            totalWaste2 += result2[0];
            totalTime2 += result2[1];
        }

        System.out.println("\nAverage waste after " + numberOfTrials + " runs for Base Model: " + totalWaste1/((double) numberOfTrials));
        System.out.println("Average number of hours after " + numberOfTrials + " runs for Base Model: " + totalTime1/((double) numberOfTrials));

        System.out.println("\nAverage waste after " + numberOfTrials + " runs for Second Model: " + totalWaste2/((double) numberOfTrials));
        System.out.println("Average number of hours after " + numberOfTrials + " runs for Second Model: " + totalTime2/((double) numberOfTrials));
    }

    static int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}

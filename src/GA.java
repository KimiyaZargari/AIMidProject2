import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class GA {
    private Individual[] population;
    private ArrayList<Point> points;
    private Individual[] parents;
    private double mutationRate;
    private int t;
    private double sigma;

    public GA(int populationSize, int tornumentSize, ArrayList<Point> points, double mutationRate, double sigma) {
        this.points = points;
        this.mutationRate = mutationRate;
        population = new Individual[populationSize];
        parents = new Individual[populationSize];
        this.sigma = sigma;
        t = tornumentSize;
    }

    public void setInitialPopulation() {

        for (int i = 0; i < population.length; i++) {
            population[i] = new Individual(points);
        }
    }

    public void tornumentSelection() {
        for (int i = 0; i < parents.length; i++) {
            double k = 0.0;
            int x = 0;
            for (int j = 0; j < t; j ++){
                int l = (int)( Math.random() * population.length);
                if( population[l].getFitness() > k)
                    k = population[l].getFitness();
                    x = l;
            }
            parents[i] = population[x];
           // System.out.println(parents[i].getA3());
        }

    }

    public Individual[] crossover() {
        int k1, k2, cnt = 0;
        double a0, a1, a2, a3;
        Individual[] children = new Individual[(parents.length * (parents.length - 1))/2];
        for (int i = 0; i < parents.length; i++) {
            for (int j = i + 1; j < parents.length; j++) {
                k1 = (int) (Math.random() * 4);
                do {
                    k2 = (int) (Math.random() * 4);
                } while (k2 == k1);
                if (k1 == 0)
                    a0 = parents[i].getA0();
                else
                    a0 = parents[j].getA0();
                if (k1 == 1)
                    a1 = parents[i].getA1();
                else
                    a1 = parents[j].getA1();
                if (k1 == 2)
                    a2 = parents[i].getA2();
                else
                    a2 = parents[j].getA2();
                if (k1 == 3)
                    a3 = parents[i].getA3();
                else
                    a3 = parents[j].getA3();

                if (k2 == 0)
                    a0 = parents[i].getA0();
                if (k2 == 1)
                    a1 = parents[i].getA1();
                if (k2 == 2)
                    a2 = parents[i].getA2();
                if (k2 == 3)
                    a3 = parents[i].getA3();
                children[cnt] = new Individual(points, a0, a1, a2, a3);
                cnt ++;
            }
        }
        return children;
    }

    public Individual[] Mutate(Individual[] individuals) {
        for (Individual i : individuals) {
            int p = (int) (Math.random() * (1.0/mutationRate));
            if (p == 0)
                i.setA0(i.getA0() + gaussianNumber());
            p = (int) (Math.random() * (1.0/mutationRate));
            if (p == 0)
                i.setA1(i.getA1() + gaussianNumber());
            p = (int) (Math.random() * (1.0/mutationRate));
            if (p == 0)
                i.setA2(i.getA2() + gaussianNumber());
            p = (int) (Math.random() * (1.0/mutationRate));
            if (p == 0)
                i.setA3(i.getA3() + gaussianNumber());
        }
        return individuals;
    }

    public void newPopulation(Individual[] individuals){
        Individual[] allPopulation = new Individual[individuals.length + population.length];
        System.arraycopy(population, 0, allPopulation, 0, population.length);
        System.arraycopy(individuals, 0, allPopulation, population.length, individuals.length);
        Arrays.sort(allPopulation, Comparator.comparing(Individual :: getFitness));

        for (int i = 0; i < population.length; i++) {
            population[i] = allPopulation[allPopulation.length - i - 1];
        }
       // System.out.println(population[0].getFitness());

    }


    boolean contains(Individual[] individuals, Individual individual) {
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i] == individual) {
                return true;
            }

        }
        return false;
    }

    private double gaussianNumber(){
        int a = (int)(Math.random() * 2);
        double i = Math.random() * sigma;
        if (a == 0)
            return i;
        else
            return  -1 * i;

    }

    public Individual[] getPopulation() {
        return population;
    }

    public double avgFitness(){
        double sum = 0;
        for (int i = 0; i < population.length; i++) {
            sum += population[i].getFitness();
        }
        return sum / population.length;
    }
}

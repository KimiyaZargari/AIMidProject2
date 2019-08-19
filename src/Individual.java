import java.util.ArrayList;

public class Individual {
    private double a0, a1, a2, a3;
    private double fitness;


    public Individual(ArrayList<Point> points){

        a0 = rand(100);
        a1 = rand(100);
        a2 = rand(100);
        a3 = rand(100);
        fitness = fitnessFunction(points);
    }

    public Individual(ArrayList<Point> points, double a0, double a1, double a2, double a3){
        setA0(a0);
        setA1(a1);
        setA2(a2);
        setA3(a3);
        fitness = fitnessFunction(points);
    }

    public double getA0() {
        return a0;
    }

    public double getA1() {
        return a1;
    }

    public double getA2() {
        return a2;
    }

    public double getA3() {
        return a3;
    }

    public void setA0(double a0) {
        this.a0 = a0;
    }

    public void setA2(double a2) {
        this.a2 = a2;
    }

    public void setA1(double a1) {
        this.a1 = a1;
    }
    public void setA3(double a3) {
        this.a3 = a3;
    }


    public double getY(double x) {
        return (a0 + (a1 * x) + (a2 * x * x)  + (a3 * x * x * x));
    }


    private double fitnessFunction(ArrayList<Point> points){
        return  1.0 / (1.0 + MSE(points));
    }

    private double MSE (ArrayList<Point> points){
        double mse = 0;
        for (int i = 1; i <= points.size(); i++) {
            mse += Math.pow(points.get(i - 1).getY() - getY(i), 2);
        }
        return (1.0 / points.size()) * mse;

    }

    public double getFitness() {
        return fitness;
    }

    private double rand(int max){
        int a = (int)(Math.random() * 2);
        double res = Math.random() * max;
        if (a == 1)
            return res;
        else
            return -1.0 * res;
    }


}

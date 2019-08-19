
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String csvFile = "/Users/kimiyazargari/IdeaProjects/project1_9531906/AI1.2/src/csv.txt";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<Point> points = new ArrayList();
        try {
            br = new BufferedReader(new FileReader(new File(csvFile)));
            while ((line = br.readLine()) != null) {
                String[] point = line.split(cvsSplitBy);
                points.add(new Point(Double.parseDouble(point[0]), Double.parseDouble(point[1])));


            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
GA ga = new GA(100,2,  points, 0.5, 5);
        ga.setInitialPopulation();
        //while (ga.getPopulation()[0].getFitness() < 0.0001) {
        for (int i = 0; i < 5000; i++) {
            ga.tornumentSelection();
            ga.newPopulation(ga.Mutate(ga.crossover()));
        }
        System.out.println("a0 =" + ga.getPopulation()[0].getA0());
       // ga.getPopulation()[0].setA0(-80);
        System.out.println("a1 =" +ga.getPopulation()[0].getA1());
        System.out.println("a2 =" +ga.getPopulation()[0].getA2());
        System.out.println("a3 =" +ga.getPopulation()[0].getA3());
        System.out.println("max fitness =" +ga.getPopulation()[0].getFitness());
        System.out.println("min fitness =" +ga.getPopulation()[49].getFitness());
        System.out.println("average fitness =" +ga.avgFitness());
        ArrayList<Point> ans = new ArrayList();

        for (int i = 0; i < 100; i++) {
            Point p = new Point(i, ga.getPopulation()[0].getY(i));
            ans.add(p);
           // System.out.println(p.getY());
        }

        num.hm(points, ans);


    }


}

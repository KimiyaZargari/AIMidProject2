import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyPlotter extends JPanel{


    private static final long serialVersionUID = 1L;
    private int labelPadding = 12;
    /**change the line color to the best you want;*/
    private Color lineColor = new Color(255,0,255);
    private Color pointColor = new Color(255, 0, 255);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private static int pointWidth = 10;
    private int numberYDivisions = 6;
    private List<Double> scores;
    private List<Double> ansr;
    private int padding = 20;


    private static ArrayList<Point> points;
    private static ArrayList<Point> answer;
    /**
     * Math_Graph is a constructor method
     * @returns List scores;
     */
    public MyPlotter(List<Double> scores, List<Double> ans) {
        this.scores = scores;
        this.ansr = ans;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double  xScale = ((double) getWidth() - (3 * padding) - labelPadding) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());
        double yScale1 = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore1() - getMinScore1());

        List<java.awt.Point> graphPoints = new ArrayList<>();
        List<java.awt.Point> ans = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
            graphPoints.add(new java.awt.Point(x1, y1));
        }
        for (int i = 0; i < ansr.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore1() - ansr.get(i)) * yScale1 + padding);
            ans.add(new java.awt.Point(x1, y1));
        }

        g2.setColor(Color.WHITE);
        //fill the rect
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) -
                labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLUE);

        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 -
                    labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (scores.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) *
                        ((i * 8.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 6, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        for (int i = 0; i < scores.size(); i++) {
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((scores.size() / 8.0)) + 3)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }


        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() -
                padding, getHeight() - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }


        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }

        g2.setColor(new Color(255, 255, 0));
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < ans.size() - 1; i++) {
            int x1 = ans.get(i).x;
            int y1 = ans.get(i).y;
            int x2 = ans.get(i + 1).x;
            int y2 = ans.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }
        g2.setStroke(oldStroke);
        g2.setColor(new Color(255, 255, 0));
        for (int i = 0; i < ans.size(); i++) {
            int x = ans.get(i).x - pointWidth / 2;
            int y = ans.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    /*
     *  getting the min score using Math();
     * getMinScore is an accessor method
     * @Return the minScore
     */


    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }


    private double getMinScore1() {
        double minScore = Double.MAX_VALUE;
        for (Double score : ansr) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }
    /*
     *  getting the max score using Math();
     * getMaxScore is an accessor method
     * @Return the maxScore;
     */

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    private double getMaxScore1() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : ansr) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }
    /* setting scores */
    public void setScores(List<Double> scores) {
        this.scores = scores;
        invalidate();
        this.repaint();
    }

    public List<Double> getScores() {
        return scores;
    }
    /* creating the method createAndShowGui in the main method, where we create the frame too and pack it in the panel*/
    private static void createAndShowGui() {
        List<Double> scores = new ArrayList<>();
        List <Double> ans = new ArrayList<>();
        Random random = new Random();
        int maxDataPoints = 100;
        int maxScore = 8;
        for (int i = 0; i < maxDataPoints; i++) {
            scores.add(points.get(i).getY());
            ans.add(answer.get(i).getY());
        }
        /* Main panel */
        MyPlotter mainPanel = new MyPlotter(scores, ans);
        mainPanel.setPreferredSize(new Dimension(1000, 2000));
        /* creating the frame */
        JFrame frame = new JFrame("Sample Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    /*the main method runs createAndShowGui*/
    public static void hm(ArrayList<Point> p, ArrayList a) {
        points = p;
        answer = a;

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });}
}
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class CelestialBody {
    public double[] x;
    public double[] v;
    public double mass;
    private double totalForce;
    private double[] forces;
    private double[] acceleration;

    private double[][] prevX;
    private int countPrev = 0;
    private final int SAVE_EVERY = 100000;
    private int saveEveryCurrent = SAVE_EVERY;

    // Animation variables
    public int size;
    public String name;
    public Color color;

    public CelestialBody() {

    }

    public CelestialBody(double[] x, double[] v, double mass, int size, String name, Color color) {
        this.x = x;
        this.v = v;
        this.mass = mass;

        this.size = size;
        this.name = name;
        this.color = color;

        this.prevX = new double[84][3];
    }

    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;

        // saveValuesToArray(x);
    }

    private void saveValuesToArray(double x[]) {
        this.saveEveryCurrent--;

        if (saveEveryCurrent == 0) {
            this.saveEveryCurrent = SAVE_EVERY;
            double[] temp = new double[3];
            temp[0] = x[0];
            temp[1] = x[1];
            temp[2] = x[2];

            this.prevX[countPrev] = temp;
            countPrev++;

            System.out.println("This happened: " + countPrev + " times.");
            if (countPrev == 84) {
                System.out.println("Saving " + name + " to file");
                try {
                    saveToFile(prevX, this.name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveToFile(double[][] values, String name) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(name));
        for (int i = 0; i < values.length; i++) {
            writer.write(values[i][0] + ";" + values[i][1] + ";" + values[i][2] + "\n");
        }

        writer.close();
    }

    public void setX(double x, double y) {
        this.x[0] = x;
        this.x[1] = y;
    }

    public double[] getV() {
        return v;
    }

    public void setV(double[] v) {
        this.v = v;
    }

    public double getTotalForce() {
        return totalForce;
    }

    public void setTotalForce(double totalForce) {
        this.totalForce = totalForce;
    }

    public double[] getForces() {
        return forces;
    }

    public void setForces(double[] forces) {
        this.forces = forces;
    }

    public double[] getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double[] acceleration) {
        this.acceleration = acceleration;
    }

    public double getMass() {
        return mass;
    }

    public void updatePosition(double timeStep) {
        x[0] += v[0] * timeStep;
        x[1] += v[1] * timeStep;
        x[2] += v[2] * timeStep;
    }

    // Animation methods
    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}

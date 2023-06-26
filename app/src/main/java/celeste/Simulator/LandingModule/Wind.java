package celeste.Simulator.LandingModule;
/*
 * A class that cointains the Wind module
 */
import java.util.Random;

public class Wind {
    private double strengthX;//wind strength for the X coordinate
    private double strengthY;//Wind strength for the Y coordinate
    private Random random;

    //Constructor
    public Wind() {
        this.random = new Random();
        updateStrength();
    }

    public void updateStrength() {
        // Generates a random direction for the wind
        double angle = random.nextDouble() * 2 * Math.PI;

        // Generates a random strength between 10 and 20 m/s
        double strength = 0.02 + random.nextDouble() * 0.05;

        // Calculates the X and Y components of the wind
        this.strengthX = strength * Math.cos(angle);
        this.strengthY = strength * Math.sin(angle);
    }

    //Getters

    public double getStrengthX() {
        return this.strengthX;
    }

    public double getStrengthY() {
        return this.strengthY;
    }
}

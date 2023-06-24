package celeste.Simulator.LandingModule;

import java.util.Random;

public class Wind {
    private double strengthX;
    private double strengthY;
    private Random random;

    public Wind() {
        this.random = new Random();
        updateStrength();
    }

    public void updateStrength() {
        // Choose a random direction for the wind
        double angle = random.nextDouble() * 2 * Math.PI;

        // Choose a random strength between 10 and 20
        double strength = 0.02 + random.nextDouble() * 0.05;

        // Calculate the X and Y components of the wind
        this.strengthX = strength * Math.cos(angle);
        this.strengthY = strength * Math.sin(angle);
    }

    public double getStrengthX() {
        return this.strengthX;
    }

    public double getStrengthY() {
        return this.strengthY;
    }
}

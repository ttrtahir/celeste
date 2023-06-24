package Simulator.LandingModule;

/*
 * A class that contains the values of the Environment of the Titan's surface.
 */
public class Environment {
    public static final double GRAVITY = 1.352 * Math.pow(10, -3);// Gravity of Titan
    public static final double UMAX = 10 * GRAVITY;// Max acceleration from main thruster
    public static final double VMAX = 1;// Max torque from side thrusters

    private LandingModule landingModule;

    // Constructor
    public Environment(LandingModule landingModule) {
        this.landingModule = landingModule;
    }

    // Method for checking if the Module has landed safely
    public boolean isSafeLanding(Spaceship spaceship) {
        return landingModule.isSafeLanding(spaceship);
    }
}
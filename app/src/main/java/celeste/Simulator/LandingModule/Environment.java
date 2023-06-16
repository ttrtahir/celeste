package celeste.Simulator.LandingModule;

public class Environment {
    public static final double GRAVITY = 1.352 * Math.pow(10, -3);
    public static final double UMAX = 10 * GRAVITY;
    public static final double VMAX = 1;

    private LandingModule landingPad;

    public Environment(LandingModule landingPad) {
        this.landingPad = landingPad;
    }

    public boolean isSafeLanding(Spaceship spaceship) {
        return landingPad.isSafeLanding(spaceship);
    }
}
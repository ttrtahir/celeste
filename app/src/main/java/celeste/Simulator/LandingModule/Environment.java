package celeste.Simulator.LandingModule;

public class Environment {
    public static final double GRAVITY = 1.352 * Math.pow(10, -3);
    public static final double UMAX = 10 * GRAVITY;
    public static final double VMAX = 1;

    private LandingModule landingModule;

    public Environment(LandingModule landingModule) {
        this.landingModule = landingModule;
    }

    public boolean isSafeLanding(Spaceship spaceship) {
        return landingModule.isSafeLanding(spaceship);
    }
}
package celeste.Simulator.LandingModule;

public class SimulateLanding {
    public static void main(String[] args) {
        Engine2 engine = new Engine2(10 * Environment.GRAVITY, 1);
        Spaceship spaceship = new Spaceship(0, 10000, 0, 0, 0, 0, 0, 0, engine);

        LandingModule landingModule = new LandingModule();

        double dt = 0.1;
        double simulationTime = 2000000;
        int steps = (int)(simulationTime / dt);

        for (int i = 0; i < steps; i++) {
        
            spaceship.controlMainEngine();
            spaceship.controlSideEngine();

            spaceship.updateState(dt);
            System.out.println(spaceship.getY());
            if (spaceship.getY() <= 10) {
                if (landingModule.isSafeLanding(spaceship)) {
                    System.out.println("Safe landing achieved at time " + i * dt + " seconds.");
                } else {
                    System.out.println("Unsafe landing at time " + i * dt + " seconds.");
                }
                return;
            }
        }

        System.out.println("No landing achieved within simulation time.");
    }
}

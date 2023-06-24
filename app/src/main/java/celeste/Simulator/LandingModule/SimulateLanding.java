package celeste.Simulator.LandingModule;

/*
 * A class that is used to simulate the landing without the GUI
 */
public class SimulateLanding {

    public static void main(double x, double y, double vX, double vY) {
        int safe = 0;
        for (int j = 0; j <= 100; j++){
        Wind wind = new Wind();
        Engine2 engine = new Engine2(10 * Environment.GRAVITY, 1);
        Spaceship spaceship = new Spaceship(x, y, 0, 0, 0, vX, vY, 0, engine, wind);

        LandingModule landingModule = new LandingModule();

        double dt = 0.1;// Time step
        double simulationTime = 2000000;// Total simulation time
        int steps = (int) (simulationTime / dt);// Calculate the total number of steps for the simulation
        
        // Simulate each step
        
            for (int i = 0; i < steps; i++) {

            // Control the engines
            spaceship.controlMainEngine();
            spaceship.controlSideEngine();

            // Update the states
            spaceship.updateState(dt);

            // Prints out the current spaceship's positions and orientation
            System.out.println("x: " + spaceship.getX() + ", y: " + spaceship.getY() + ", theta: "
                    + Math.abs(spaceship.getTheta() % (2 * Math.PI)));

            // Check if the spaceship has landed and if the landing was safe
            if (spaceship.getY() <= 0) {
                if (landingModule.isSafeLanding(spaceship)) {
                    System.out.println("Safe landing achieved at time " + i * dt + " seconds.");
                    safe++;
                } else {
                    System.out.println("Unsafe landing at time " + i * dt + " seconds.");
                }
                break;
            }
        }
        // Check if spaceship has not landed in the given total simulation time
        if (spaceship.getY() > 0.001) {

            System.out.println("No landing achieved within simulation time.");
        }
        System.out.println("Safe landings: " + safe);
        System.out.println("Test ladnings: " + j);
    }
}
}

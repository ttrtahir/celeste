package celeste.Simulator.LandingModule;

public class SimulateLanding {
 
    public static void main(String[] args) {
        Engine2 engine = new Engine2(10 * Environment.GRAVITY, 1);
        Spaceship spaceship = new Spaceship(10000, 10000, 0, 0, 0, 0, 0, 0, engine);
        
        LandingModule landingModule = new LandingModule();

        double dt = 0.1;
        double simulationTime = 20000;
        int steps = (int)(simulationTime / dt);

        for (int i = 0; i < steps; i++) {
        
            spaceship.controlMainEngine();
            spaceship.controlSideEngine();

            spaceship.updateState(dt);

            System.out.println("x: " + spaceship.getX() + ", y: " + spaceship.getY());
          
            if (spaceship.getY() <= 0) {            
                if (landingModule.isSafeLanding(spaceship)) {
                    System.out.println("Safe landing achieved at time " + i * dt + " seconds.");
                } else {
                     System.out.println("Unsafe landing at time " + i * dt + " seconds.");
                 }
                 break;
            }
        }
        if (spaceship.getY() > 0.001) {
                   
            System.out.println("No landing achieved within simulation time.");
        }
       
        
    }
}

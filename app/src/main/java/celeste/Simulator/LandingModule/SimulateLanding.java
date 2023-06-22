package celeste.Simulator.LandingModule;
/*
 * A class that is used to simulate the landing
 */
public class SimulateLanding {
 
    public static void main(String[] args) {
        Engine2 engine = new Engine2(10 * Environment.GRAVITY, 1);
        Spaceship spaceship = new Spaceship(100, 10000, 1, 10, 25,30, 30, 4, engine);
        
        LandingModule landingModule = new LandingModule();

        double dt = 0.1;//Time step
        double simulationTime = 20000;//Total simulation time
        int steps = (int)(simulationTime / dt);//Calculate the total number of steps for the simulation

        //Simulate each step
        for (int i = 0; i < steps; i++) {
            //Control the engines
            spaceship.controlMainEngine();
            spaceship.controlSideEngine();

            //Update the states
            spaceship.updateState(dt);

            //Prints out the current spaceship's positions and orientation
            System.out.println("x: " + spaceship.getX() + ", y: " + spaceship.getY()+ ", theta: " + Math.abs(spaceship.getTheta() % (2 * Math.PI)));
          
            //Check if the spaceship has landed and if the landing was safe
            if (spaceship.getY() <= 0) {            
                if (landingModule.isSafeLanding(spaceship)) {
                    System.out.println("Safe landing achieved at time " + i * dt + " seconds.");
                } else {
                     System.out.println("Unsafe landing at time " + i * dt + " seconds.");
                 }
                 break;
            }
        }
        //Check if spaceship has not landed in the given total simulation time
        if (spaceship.getY() > 0.001) {
                   
            System.out.println("No landing achieved within simulation time.");
        }
       
        
    }
}

package celeste.Simulator.LandingModule;

public class LandingModule {
    private double dx = Math.pow(10, -4);
    private double dtheta = 0.02;
    private double ex = Math.pow(10, -4);
    private double ey = Math.pow(10, -4);
    private double etheta = 0.01;

    public boolean isSafeLanding(Spaceship spaceship) {
        //Check if spaceship is on the ground
        if (spaceship.getY() != 0) {
            return false;
        }
        
        //Check coditions from project manual
        boolean isXSafe = Math.abs(spaceship.getX()) <= dx;
        boolean isThetaSafe = Math.abs(spaceship.getTheta() % (2 * Math.PI)) <= dtheta;
        boolean isVXSafe = Math.abs(spaceship.getVX()) <= ex;
        boolean isVYSafe = Math.abs(spaceship.getVY()) <= ey;
        boolean isVThetaSafe = Math.abs(spaceship.getVTheta()) <= etheta;
       
        
        return isXSafe && isThetaSafe && isVXSafe && isVYSafe && isVThetaSafe;
    }
}

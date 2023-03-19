public class SolarSystem {

    private static double[][] positions = { { 0, 0, 0 }, { 7.83e6, 4.49e7, 2.87e6 }, { -2.82e7, 1.04e8, 3.01e6 },
            { -1.48e8, -2.78e7, 3.37e4 }, { -1.48e8, -2.75e7, 7.02e4 }, { -1.59e8, 1.89e8, 7.87e6 },
            { 6.93e8, 2.59e8, -1.66e7 }, { 1.25e9, -7.60e8, -3.67e7 }, { 1.25e9, 7.61e8, -3.63e7 },
            { 4.45e9, -3.98e8, -9.45e7 }, { 1.96e9, 2.19e9, -1.72e7 } };
    private static double[][] velocity = { { 0, 0, 0 }, { -5.75e1, 1.15e1, 6.22e0 }, { -3.40e1, -8.97e0, 1.84e0 },
            { 5.05e0, -2.94e1, 1.71e-3 }, { 4.34e0, -3.00e1, -1.16e-2 }, { -1.77e1, -1.35e1, 1.52e-1 },
            { -4.71e0, 1.29e1, 5.22e-2 }, { 4.47e0, 8.24e0, -3.21e-1 }, { 9.00e0, 1.11e1, -2.25e0 },
            { 4.48e-1, 5.45e0, -1.23e-1 }, { -5.13e0, 4.22e0, 8.21e-2 } };
    private static double[][] mass = { { 1.99e30 }, { 3.30e23 }, { 4.87e24 }, { 5.97e24 }, { 7.35e22 }, { 6.42e23 },
            { 1.90e27 }, { 5.68e26 }, { 1.35e23 }, { 1.02e26 }, { 8.68e25 } };

    private CelestialBody[] celestialBody;
    private int celestialBodyCount;
    private final double GRAVITATIONAL_CONSTANT = 6.6743E-11; // km^3/(kg s^2)
    private double timeStep;

    public SolarSystem(){

        // Amount of celestial entities in a solar system (including space probes)
        this.celestialBodyCount = 11;
        this.celestialBody = new CelestialBody[celestialBodyCount];
        this.timeStep = 0.1;

        // Without probe
        for(int i = 0 ; i < celestialBodyCount ; i++)
            this.celestialBody[i] = new CelestialBody(positions[i], velocity[i], mass[i][0]);

//        calculateForce();
//        updateAcceleration();
//        updateVelocity();
//        updatePosition();
    }

    /**Calculates the force on each celestial unit and updates the force on them in each call using their position, mass, and gravitational constant. Newtons formula. <br>
     * Fx = (G * m1 * m2 * dx) / r^3
     * */
    private void calculateForce(){

        for(int i = 1; i < celestialBodyCount; i++){
            double totalForceOnIth = 0;
            double distance = 0;
            double[] tempF = new double[3];
            for(int j = 0; j < celestialBodyCount ; j++){
                if(j == i) continue;
                distance = calculateDistanceBetweenCelestials(i,j);
                totalForceOnIth += -(GRAVITATIONAL_CONSTANT * celestialBody[i].getMass() * (celestialBody[j].getMass())  / (distance * distance));
                for( int k = 0 ; k < 3 ; k++ ){
                    tempF[k] = totalForceOnIth * (celestialBody[j].getX()[k] - celestialBody[i].getX()[k]) / distance;
                }
            }
            celestialBody[i].setTotalForce(totalForceOnIth);
            celestialBody[i].setForces(tempF);
//            System.out.println("Total Force on "+ i + " is : " + totalForceOnIth + " with distance : " +distance + " acceleration: " + totalForceOnIth / celestialBody[i].getMass());
        }
    }

    /**
    * Helper method for calculateTheForceForEach() method. Calculates the distance between celestials with this formula:<br>
     * ||u - v|| = sqrt((u1 - v1)^2 + (u2 - v2)^2 + (u3 - v3)^2)
    * */
    private double calculateDistanceBetweenCelestials(int i, int j){
        double x1[] = getCelestialBody()[i].getX();
        double x2[] = getCelestialBody()[j].getX();
        double totalNeedsSquared = 0;

        for(int k=0;k<=2;k++)
            totalNeedsSquared= Math.pow((x1[k] - x2[k]), 2);

        return Math.sqrt(totalNeedsSquared);
    }

    /**A simple method for updating the acceleration using mass and force on the celestials.
     * */
    private void updateAcceleration() {
        for( int i = 1 ; i < celestialBodyCount ; i++) {
            double[] tempF = celestialBody[i].getForces();
            double[] tempA = new double[3];
            double mass = celestialBody[i].getMass();
            for( int j = 0 ; j < 3 ; j++) {
                double forceOnJ = tempF[j];
                tempA[j] = forceOnJ / mass;
            }
            celestialBody[i].setAcceleration(tempA);
        }
    }

    private void updatePosition(){
        for (int i = 1; i < celestialBodyCount; i++) {
            double[] tempV = celestialBody[i].getV();
            double[] tempP = celestialBody[i].getX();
            for( int j = 0 ; j < 3 ; j++ )
                tempP[j] += tempV[j] * timeStep;
            celestialBody[i].setX(tempP);
        }
    }
    private void updateVelocity(){
        for (int i = 1; i < celestialBodyCount; i++) {
            double[] tempA = celestialBody[i].getAcceleration();
            double[] tempV = celestialBody[i].getV();
            for( int j = 0 ; j < 3 ; j++ )
                tempV[j] += tempA[j] * timeStep;
            celestialBody[i].setV(tempV);
        }
    }


    public CelestialBody[] getCelestialBody() {
        return celestialBody;
    }
}

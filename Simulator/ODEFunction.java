package Simulator;

import Interface.IAccelerationRate;
import Interface.IODEFunction;
import Interface.IState;

public class ODEFunction implements IODEFunction {
    // Gravitational constant
    public static final double G = 6.6743E-20;
    public static int nBodies = CelestialBody.celestialBodies.length;
    public AccelerationRate acceleration = new AccelerationRate();

    /**
     * F = G* mi *mj * (pos_i - pos_j)/|pos_i - pos_j|^3
     *
     * @param t
     * @param y state of the Solar System
     * @return acceleration for each planet
     */

    @Override
    public IAccelerationRate call(double t, IState y) {
        acceleration.initialize(nBodies);
        //System.out.println("acceleration before update " + acceleration.toString());

        //get dx = v

        for(int i = 0; i < nBodies; i++){
            Vector3 velocity = (Vector3) ((State) y).getVelocity(i);  
            acceleration.addVelocity(i, velocity);
        }
        // get dx = acceleration
        for (int i = 0; i < nBodies; i++) {
            double distance = 0;
            for (int j = 0; j < nBodies; j++) {
                if (i != j) {
                    // calculate the distance 
                    // a(i) = G * m(j) * pos(i) - pos(j) / (euler distance(pos(i) - pos(j))) ^ 3 
                    distance = Math.pow(((State) y).getPosition(i).euclideanDist(((State) y).getPosition(j)), 3);
                    Vector3 positionalDifference = (Vector3)((((State) y).getPosition(j).subtract(((State) y).getPosition(i))));
                    double aThing = G*CelestialBody.celestialBodies[j].getMass()/distance;
                    Vector3 tempAcc = (Vector3) (positionalDifference.multiply(aThing));
                    // add the temporary acceleration to total acceleration of a planet
                    acceleration.addAcceleration(i, tempAcc);
                }
            }
        }
        //System.out.println("Acceleration after update " + acceleration.toString());
        return acceleration;
    }
}

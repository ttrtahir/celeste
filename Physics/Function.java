package Physics;

import Interface.AccelerationInterface;
import Interface.FunctionInterface;
import Interface.StateInterface;

public class Function implements FunctionInterface {
    //Gravitational constant
    public static final double G = 6.6743E-20;
    public Acceleration acceleration = new Acceleration();

    /**
     * F = G* mi *mj * (pos_i - pos_j)/|pos_i - pos_j|^3
     */

    @Override
    public AccelerationInterface motion(double t, StateInterface y) {
        acceleration.initialize(CelestialBody.celestialBodies.length);
        System.out.println("acceleration before update "+ acceleration.toString());

        for(int i = 0; i < CelestialBody.celestialBodies.length; i++){
            double distance = 0;
            for (int j = 0; j < CelestialBody.celestialBodies.length; j++){
                if(i != j){
                    distance = Math.pow(((State) y).getPosition(i).euclideanDist(((State) y).getPosition(j)), 3);
                    Vector3 tempAcc = (Vector3) (((State) y).getPosition(j).subtract( ((State) y).getPosition(i))).multiply(1/distance);
                    acceleration.add(i, tempAcc);
                }
            }
        }
        System.out.println("Acceleration after update " + acceleration.toString());
        return (AccelerationInterface) acceleration;
    }

}

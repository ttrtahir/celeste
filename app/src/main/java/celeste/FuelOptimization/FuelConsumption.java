package celeste.FuelOptimization;

import celeste.Interface.IVector3;
import celeste.Simulator.Vector3;

public class FuelConsumption {
    public static double calculateFuelConsumed(IVector3 oldVelocity, IVector3 newVelocity) {
        return oldVelocity.subtract(newVelocity).multiply(50000).euclideanDist(new Vector3(0, 0, 0));

    }
}

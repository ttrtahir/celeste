package celeste.FuelOptimization;

import celeste.Interface.IVector3;
import celeste.Simulator.Vector3;

public class FuelConsumption {
    /*
     * @param oldVelocity: the velocity of the ship before the burn
     * 
     * @param newVelocity: the velocity of the ship after the burn
     * 
     * @return the amount of fuel consumed by the burn
     * 
     * This method calculates the amount of fuel consumed by a burn
     * using the formula described in the paper:
     * fuelConsumed = (oldVelocity - newVelocity) * 50000
     */
    public static double calculateFuelConsumed(IVector3 oldVelocity, IVector3 newVelocity) {
        return oldVelocity.subtract(newVelocity).multiply(50000).euclideanDist(new Vector3(0, 0, 0));
    }
}

package Simulator;

import Interface.IAccelerationRate;
import Interface.IVector3;

public class AccelerationRate implements IAccelerationRate {
    /**
     * class to store the acceleration accRate for all celestial bodies in the system
     * update the state of the system
     *
     * [i][0] = dx = velocity of object i
     * [i][1] = dv = acceleration of object i
     */
    public Vector3[][] accRate;
    static int nBodies = CelestialBody.celestialBodies.length;

    public AccelerationRate() {

    }

    /**
     * initializes empty accRate array with size
     * @param size
     */
    public void initialize(int size) {
        accRate = new Vector3[size][size];
        for (int i = 0; i < size; i++) {
            accRate[i][0] = new Vector3(0, 0, 0);
            accRate[i][1] = new Vector3(0,0,0);
        }
    }

    /**
     * adds to array storing values for dx
     * @param i
     * @param anotherVec
     */
    public void addVelocity(int i, Vector3 anotherVec) {
        accRate[i][0]= (Vector3) accRate[i][0].add(anotherVec);
    }
    /**
     * adds to array storing values for dv
     * @param i
     * @param anotherVec
     */
    public void addAcceleration(int i, Vector3 anotherVec){
        accRate[i][1] = (Vector3) accRate[i][1].add(anotherVec);
    }

    /**
     * adds two accRates
     * @param anotherVec
     * @return sum
     */
    public AccelerationRate add(AccelerationRate anotherVec){

        // return addMultiply(1, anotherVec); Alternatively
        AccelerationRate newRate = new AccelerationRate();
        newRate.initialize(nBodies);

        for(int i = 1; i < accRate.length; i++){
            newRate.accRate[i][0] = (Vector3) this.accRate[i][0].add(anotherVec.getVelocity(i));
            newRate.accRate[i][1] = (Vector3) this.accRate[i][1].add(anotherVec.getAcceleration(i));
        }
        return newRate;
    }

    public AccelerationRate addMultiply(double stepsize, AccelerationRate anotherVec){
        
        AccelerationRate newRate = new AccelerationRate();
        newRate.initialize(nBodies);

        for(int i = 1; i < accRate.length; i++){
            newRate.accRate[i][0] = (Vector3) this.accRate[i][0].add(anotherVec.getVelocity(i).multiply(stepsize));
            newRate.accRate[i][1] = (Vector3) this.accRate[i][1].add(anotherVec.getAcceleration(i).multiply(stepsize));
        }
        return newRate;
    }

    public Vector3 getVelocity(int i) {
        return accRate[i][0];
    }
    public Vector3 getAcceleration(int i){
        return accRate[i][1];
    }


    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < accRate.length; i++) {
            string += i + " " + accRate[i];
        }
        return string;
    }
}

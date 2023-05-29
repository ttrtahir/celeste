package Simulator;

import Interface.IVector3;

/*
 * Contains methods required for the Engine.
 */

public class Engine {

    private double startVelocity;
    private double endVelocity;
    private double dTime;
    private double Gravity = ODEFunction.G;
    private double rocketMass = 50000;
    private double specificImpulse;
    private double fuelConsumed;

    private int thrustCounter = 0;

    public Engine(double sVelocity, double eVelocity, double dTime, double specificImpulse) {
        this.startVelocity = sVelocity;
        this.endVelocity = eVelocity;
        this.dTime = dTime;
        this.specificImpulse = specificImpulse;
    }

    public Engine() {
    }

    public double getMomentum() {
        double momentum = rocketMass * endVelocity - rocketMass * startVelocity;
        return momentum;
    }

    public double getThrust() {
        double thrust = getMomentum() / dTime - Gravity * rocketMass;
        return thrust;
    }

    public double fuelConsumed() {
        double thrust = getThrust();
        double fuelConsumed = Math.abs(thrust) / (Gravity * specificImpulse) * dTime;
        return fuelConsumed;
    }

    public double getVelocity(double t, double deltaTime) {
        double integral = 0.0;
        double time = t;
        double upperBound = t + deltaTime;
        double step = 0.001; // Step size for numerical integration (adjust)

        while (time <= upperBound) {
            double force = calculateForce(time);
            integral += force;
            time += step;
        }

        double impulse = integral * step;
        double velocity = impulse / rocketMass + startVelocity;
        return velocity;
    }

    private double calculateForce(double time) {
        return 0.0;
    }

    private double getFuelConsumed(Vector3 oldV, Vector3 newV) {
        return oldV.subtract(newV).multiply(50000).euclideanDist(new Vector3(0, 0, 0));
    }

    public Vector3 initiateThrust(IVector3 inputVelocity) {
        this.startVelocity = inputVelocity.euclideanDist(new Vector3(0, 0, 0));
        if (thrustCounter == 0) {
            thrustCounter++;
            /* Velocity to enter Titan's orbit */
            Vector3 newVelocity = new Vector3(-0.25, 10.94, -0.86);

            this.endVelocity = newVelocity.euclideanDist(new Vector3(0, 0, 0));

            System.out.println("Fuel consumed: " + getFuelConsumed((Vector3) inputVelocity, newVelocity));

            return newVelocity;
        } else {
            /* Velocity to start journey towards Earth */
            Vector3 newVelocity = new Vector3(-60, 23.447, -0.86);

            this.endVelocity = newVelocity.euclideanDist(new Vector3(0, 0, 0));

            System.out.println("Fuel consumed: " + getFuelConsumed((Vector3) inputVelocity, newVelocity));

            return newVelocity;
        }
    }
}
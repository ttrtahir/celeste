package Simulator;

/*
 * Contains methods required for the Engine.
 */

public class Engine {

    private double startVelocity;
    private double endVelocity;
    private double dTime;
    private double Gravity;
    private double rocketMass = 50000;
    private double specificImpulse;
    private double fuelConsumed;

    public Engine(double sVelocity, double eVelocity, double dTime, double Gravity, double specificImpulse) {
        this.startVelocity = sVelocity;
        this.endVelocity = eVelocity;
        this.dTime = dTime;
        this.Gravity = Gravity;
        this.specificImpulse = specificImpulse;
    }

    public double getMomentum() {
        double momentum = rocketMass * endVelocity - startVelocity;
        return momentum;
    }

    public double getThrust() {
        double thrust = getMomentum() / dTime - Gravity * rocketMass;
        return thrust;
    }

    public double getSpecificImpulse(){
        specificImpulse = getThrust() / ((rocketMass / dTime) * 9.80665);
        return specificImpulse;
    }

    public double fuelConsumed() {
        double thrust = getThrust();
        double fuelConsumed = Math.abs(thrust) / (Gravity * specificImpulse) * dTime;
        return fuelConsumed;
    }
}
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
}
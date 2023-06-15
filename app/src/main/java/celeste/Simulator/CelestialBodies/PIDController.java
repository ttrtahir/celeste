package celeste.Simulator.CelestialBodies;

public class PIDController {
    private double kp; //proportional Gain
    private double ki; //integral Gain
    private double kd; //derivative Gain

    private double integral = 0;
    private double prevError = 0;
    private double setPoint = 0;

    public PIDController(double kp, double ki, double kd) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
    }

    public void setSetPoint(double setPoint) {
        this.setPoint = setPoint;
    }

    public double calculate(double current) {
        double error = setPoint - current;
        integral += error;
        double derivative = error - prevError;
        prevError = error;
        return kp * error + ki * integral + kd * derivative;
    }
}

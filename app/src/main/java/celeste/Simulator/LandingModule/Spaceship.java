package celeste.Simulator.LandingModule;

/*
 * A class that contains a feedback controller with a MainEngine and SideEngine
 */
public class Spaceship {

    private double x, y, theta, u, v, vX, vY, vTheta;// Position, orientation, and state variables
    private Engine2 engine;// Spaceship's engine
    private double targetX = 0, targetY = 0, targetVX = 0, targetVY = 0, targetTheta = 0, targetVTheta = 0;// Target
                                                                                                           // landing
                                                                                                           // coordinates
                                                                                                           // and states

    // Desired acceleration in x-axis and Cross-Track correction constant
    private double desiredAccelX = 0;
    private double K_crossTrack = 8;

    private double Kp = 0.01, Ki = 0.01, Kd = 0.05; // PID control constants

    // Spaceship constructor
    public Spaceship(double x, double y, double theta, double u, double v, double vX, double vY, double vTheta,
            Engine2 engine) {
        this.x = x;
        this.y = y;
        this.theta = theta;
        this.u = u;
        this.v = v;
        this.vX = vX;
        this.vY = vY;
        this.vTheta = vTheta;
        this.engine = engine;
    }

    // Controls the thrust of the main engine using a PID controller
    public void controlMainEngine() {

        double errorY = targetY - y;
        double errorVY = targetVY - vY;

        // PID Controller for the vertical position and velocity
        double integralPart = Ki * (errorY + errorVY);
        double derivativePart = Kd * (errorY - errorVY);
        this.u = Kp * errorY + integralPart + derivativePart;
        this.u = Math.min(this.u, engine.getUMax()); // Limit the thrust to the engine's maximum

        // Reduce thrust when aproaching to the ground
        if (this.y < 30) {
            this.u *= this.y;
        }
    }

    // Controls the side engine using a PID controller and cross-track correction
    public void controlSideEngine() {
        double errorX = targetX - x;
        double errorVX = targetVX - vX;
        double errorTheta = targetTheta - theta;
        double errorVTheta = targetVTheta - vTheta;
        double crossTrackError = x;

        targetTheta = 0;

        // Control the rotation to aim the thrust in the direction that will correct the
        // errors in the horizontal position
        double integralPartTheta = 0.1 * Ki * (errorTheta + errorVTheta);
        double derivativePartTheta = 0.1 * Kd * (errorTheta - errorVTheta);
        this.v = Kp * errorTheta + integralPartTheta + derivativePartTheta;
        this.v = Math.min(this.v, engine.getVMax());// Limit the torque to the engine's maximum

        // Control the lateral position
        double integralPartX = 0.01 * Ki * (errorX + errorVX);
        double derivativePartX = 0.01 * Kd * (errorX - errorVX);
        double controlX = Kp * errorX + integralPartX + derivativePartX; // PID control for lateral position

        // Cross-Track Correction
        double correction = -K_crossTrack * crossTrackError;
        controlX += correction;

        this.desiredAccelX = controlX;

        // Modify control action based on proximity to target
        if (Math.abs(this.x - targetX) < 50) {
            controlX *= Math.pow(Math.abs(this.x - targetX) / 50, 2);
        }

        this.vX = controlX;
    }

    // Updates the spaceship's state based on the control inputs and elapsed time
    public void updateState(double dt) {
        controlMainEngine();
        controlSideEngine();

        // Update state variables
        this.vX += desiredAccelX * dt;
        this.vY = u * Math.cos(theta) - Environment.GRAVITY;
        this.vTheta = v;
        this.x += vX * dt;
        this.y += vY * dt;
        this.theta += vTheta * dt;

        // Stop movement when hitting the ground
        if (this.y < 0) {
            this.y = 0;
            this.vY = 0;
        }
    }

    // Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getTheta() {
        return theta;
    }

    public double getU() {
        return u;
    }

    public double getV() {
        return v;
    }

    public double getVX() {
        return vX;
    }

    public double getVY() {
        return vY;
    }

    public double getVTheta() {
        return vTheta;
    }

    // Setters
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public void setU(double u) {
        this.u = u;
    }

    public void setV(double v) {
        this.v = v;
    }

    public void setVX(double vX) {
        this.vX = vX;
    }

    public void setVY(double vY) {
        this.vY = vY;
    }

    public void setVTheta(double vTheta) {
        this.vTheta = vTheta;
    }
}

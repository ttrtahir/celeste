package celeste.Simulator.LandingModule;

public class Spaceship {
    private double x, y, theta, u, v, vX, vY, vTheta;
    private Engine2 engine;
    private double targetX = 0, targetY = 0, targetVX = 0, targetVY = 0, targetTheta = 0, targetVTheta = 0;
    private double Kp = 0.1, Ki = 0.05, Kd = 0.05; //PID control constants

    public Spaceship(double x, double y, double theta, double u, double v, double vX, double vY, double vTheta, Engine2 engine) {
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

    public void controlMainEngine() {
        double errorY = targetY - y;
        double errorVY = targetVY - vY;
        double integralPart = Ki * (errorY + errorVY);
        double derivativePart = Kd * (errorY - errorVY);
        this.u = Kp * errorY + integralPart + derivativePart; //PID control for vertical position and velocity
        this.u = Math.min(this.u, engine.getUMax()); //Limit the thrust to the engine's maximum
    }

    public void controlSideEngine() {
        double errorX = targetX - x;
        double errorVX = targetVX - vX;
        double errorY = targetY - y;
        double errorTheta = targetTheta - theta;
        double errorVTheta = targetVTheta - vTheta;

        //Adjust the target thrust angle based on the errors in the horizontal position and velocity
        targetTheta = Math.atan2(errorX + errorVX, errorY);

        //Control the rotation to aim the thrust in the direction that will correct the errors in the horizontal and vertical positions
        double integralPart = Ki * (errorTheta + errorVTheta);
        double derivativePart = Kd * (errorTheta - errorVTheta);
        this.v = Kp * errorTheta + integralPart + derivativePart; //PID control for rotation and rotational velocity
        this.v = Math.min(this.v, engine.getVMax()); //Limit the torque to the engine's maximum
    }

    public void updateState(double dt) {
        controlMainEngine();
        controlSideEngine();
        this.vX = u * Math.sin(theta);
        this.vY = u * Math.cos(theta) - Environment.GRAVITY;
        this.vTheta = v;
        this.x += vX * dt;
        this.y += vY * dt;
        this.theta += vTheta * dt;
    }


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

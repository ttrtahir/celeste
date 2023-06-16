package celeste.Simulator.LandingModule;

public class Engine2 {
    private double uMax;//Max acceleration from main thruster
    private double vMax;//Max torque from side thrusters

    public Engine2(double uMax, double vMax) {
        this.uMax = uMax;
        this.vMax = vMax;
    }

    public double getUMax() {
        return uMax;
    }

    public void setUMax(double uMax) {
        this.uMax = uMax;
    }

    public double getVMax() {
        return vMax;
    }

    public void setVMax(double vMax) {
        this.vMax = vMax;
    }
}

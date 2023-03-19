public class CelestialBody {
    private double[] x;
    private double[] v;
    private double mass;
    private double totalForce;
    private double[] forces;
    private double[] acceleration;

    public CelestialBody(double[] x, double[] v, double mass){
        this.x = x;
        this.v = v;
        this.mass = mass;
    }

    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public double[] getV() {
        return v;
    }

    public void setV(double[] v) {
        this.v = v;
    }

    public double getTotalForce() {
        return totalForce;
    }

    public void setTotalForce(double totalForce) {
        this.totalForce = totalForce;
    }

    public double[] getForces() {
        return forces;
    }

    public void setForces(double[] forces) {
        this.forces = forces;
    }

    public double[] getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double[] acceleration) {
        this.acceleration = acceleration;
    }

    public double getMass() {
        return mass;
    }

}

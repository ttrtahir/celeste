public interface EulerInterface {
    public void calculateForce();
    public double calculateDistanceBetweenCelestials(double[] x1, double[] x2);
    public void updateAcceleration();
    public void updatePosition();
    public void updateVelocity();
}

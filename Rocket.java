public class Rocket implements EulerInterface {
    double[] rocketPosition = new double[3];
    double[] rocketVelocity = new double[3];
    double rocketMass = 0;
    double rocketTotalForce;
    double[] rocketForces;
    double[] rocketAcceleration;
    CelestialBody[] celestialBody = SolarSystem.celestialBody;

    @Override
    public void calculateForce() {
        for (int i = 1; i < celestialBodyCount; i++) {
            double totalForceOnIth = 0;
            double distance = 0;
            double[] tempF = new double[3];
            for (int j = 0; j < celestialBodyCount; j++) {
                if (j == i)
                    continue;
                double x1[] = rocketPosition;
                double x2[] = getCelestialBody()[j].getX();
                distance = calculateDistanceBetweenCelestials(x1, x2);
                for (int k = 0; k < 3; k++) {
                    tempF[k] += (celestialBody[j].getX()[k]  - celestialBody[i].getX()[k]) * (GRAVITATIONAL_CONSTANT * celestialBody[i].getMass() * (celestialBody[j].getMass())
                            / (distance * distance * distance )) ;
                }
            }
            rocketTotalForce = totalForceOnIth;
            rocketForces = tempF;
        }
    }

    @Override
    public double calculateDistanceBetweenCelestials(double[] x1, double[] x2) {
        double totalNeedsSquared = 0;

        for (int k = 0; k <= 2; k++)
            totalNeedsSquared += Math.pow((x1[k] - x2[k]), 2);

        return Math.sqrt(totalNeedsSquared);
    }

    @Override
    public void updateAcceleration() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAcceleration'");
    }

    @Override
    public void updatePosition() {

    }

    @Override
    public void updateVelocity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateVelocity'");
    }

    @Override
    public CelestialBody[] getCelestialBody() {
        return SolarSystem.celestialBody;
    }
    
}
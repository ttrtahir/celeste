import javax.swing.text.Position;

public class Rocket implements EulerInterface {
    double[] rocketPosition = new double[3];
    double[] rocketVelocity = new double[3];
    double rocketMass = 0;
    double totalForce;
    double[] forces;
    double[] acceleration;

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
            celestialBody[i].setTotalForce(totalForceOnIth);
            celestialBody[i].setForces(tempF);
        }
    }

    @Override
    public double calculateDistanceBetweenCelestials(double[] x1, double[] x2) {
        double x1[] = rocketPosition;
        double x2[] = getCelestialBody()[j].getX();
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
}
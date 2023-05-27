package Simulator;

import Interface.ISpaceProbe;
import Interface.IVector3;

public class SpaceProbe implements ISpaceProbe {
    public State y0;
    public Vector3[] trajectory;
    public Vector3[] earthPos;
    public Vector3 earthPosOneYear;
    public Vector3 titanPosOneYear;
    public Vector3[] titanPos;
    public State[] states;

    // Vector3[] positions;
    // Vector3[] velocities;
    // Vector3[] gravValues;

    public EulerSolver eulerSolver;
    public RK4 rungeKuttaSolver;
    public ODEFunction f = new ODEFunction();

    /**
     * calculate trajectory of a probe
     *
     * @param p0       starting position
     * @param v0       starting velocity
     * @param timeStep time steps at which position is being updated
     * @return positions of the probe over a given time period
     */

    @Override
    public IVector3[] trajectory(IVector3 p0, IVector3 v0, double[] timeStep) {
        CelestialBody.celestialBodies[11].posVec = p0;
        CelestialBody.celestialBodies[11].veloVec = v0;

        y0 = new State();
        y0.inputState();

        eulerSolver = new EulerSolver();
        states = (State[]) eulerSolver.solve(f, y0, timeStep);

        // earthPos = eulerSolver.earthPosition;
        // earthPosOneYear = eulerSolver.earthPosOneYear;
        // titanPos = eulerSolver.titanPosition;
        // titanPosOneYear = eulerSolver.titanPosOneYear;

        trajectory = new Vector3[timeStep.length];
        for (int i = 0; i < trajectory.length; i++) {
            trajectory[i] = (Vector3) states[i].getPosition(11);
        }

        // eulerSolver = null;
        // System.gc();
        return trajectory;
    }

    @Override
    public IVector3[] trajectory(IVector3 p0, IVector3 v0, double timefinal, double h) {
        CelestialBody.celestialBodies[11].posVec = p0;
        CelestialBody.celestialBodies[11].veloVec = v0;

        y0 = new State();
        y0.inputState();

        /*
         * eulerSolver = new EulerSolver();
         * states = (State[]) eulerSolver.solve(f, y0, timefinal, h);
         * earthPos = eulerSolver.earthPosition;
         * earthPosOneYear = eulerSolver.earthPosOneYear;
         * titanPos = eulerSolver.titanPosition;
         * titanPosOneYear = eulerSolver.titanPosOneYear;
         */

        trajectory = new Vector3[(int) Math.round((timefinal / h) + 1)];

        for (int i = 0; i < trajectory.length; i++) {
            trajectory[i] = (Vector3) states[i].getPosition(11);
        }

        // eulerSolver = null;
        // System.gc();
        return trajectory;
    }

    public static void main(String[] args) {
        SpaceProbe probe = new SpaceProbe();

        // TODO: change the parameters
        probe.trajectory(new Vector3(1, 1, 1), new Vector3(1, 1, 1), 1000, 10);
    }
}

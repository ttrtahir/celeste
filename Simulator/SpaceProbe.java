package Simulator;

import Interface.ISpaceProbe;
import Interface.IVector3;

public class SpaceProbe implements ISpaceProbe {
    public State y0;
    public Vector3[] trajectory;
    public State[] states;

    public ODESolver calculator;
    public ODEFunction f;

    @Override
    public IVector3[] trajectory(IVector3 p0, IVector3 v0, double timefinal, double h) {

        return trajectory;
    }

    @Override
    public IVector3[] trajectory(IVector3 p0, IVector3 v0, double[] timestep) {
        // remember to change the index to get the probe
        CelestialBody.celestialBodies[0].posVec = p0;
        CelestialBody.celestialBodies[0].veloVec = v0;

        y0 = new State();
        y0.inputState();

        calculator = new ODESolver();
        f = new ODEFunction();
        states = (State[]) calculator.solve(f, y0, timestep);

        trajectory = new Vector3[timestep.length];

        for (int i = 0; i < trajectory.length; i++) {
            trajectory[i] = (Vector3) states[i].getPosition(11);
        }
        System.out.println("Titan at position 0: ");

        return trajectory;
    }
}

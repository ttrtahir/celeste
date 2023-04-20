package Physics;

import Interface.SpaceProbeInterface;
import Interface.Vector3Interface;

public class SpaceProbe implements SpaceProbeInterface {
    public State y0;
    public Vector3[] trajectory;
    public State[] states;

    public Calculation calculator;
    public Function f;
    @Override
    public Vector3Interface[] trajectory(Vector3Interface p0, Vector3Interface v0, double timefinal, double h) {

        return new Vector3Interface[0];
    }

    @Override
    public Vector3Interface[] trajectory(Vector3Interface p0, Vector3Interface v0, double[] timestep) {
        CelestialBody.celestialBodies[11].posVec = p0;
        CelestialBody.celestialBodies[11].veloVec = v0;

        y0 = new State();
        y0.inputState();

        calculator = new Calculation();
        f = new Function();
        states = (State[]) calculator.solve(f,y0,timestep);

        trajectory = new Vector3[timestep.length];

        for(int i =0 ; i < trajectory.length; i++){
            trajectory[i] = (Vector3) states[i].getPosition(11);
        }
        System.out.println("Titan at position 0: ");

        return trajectory;
    }
}

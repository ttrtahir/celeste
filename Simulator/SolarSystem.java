package Simulator;

import FileReader.ReadFile;
import GUI.GlobalState;
import Interface.IODESolver;
import Interface.IVector3;
import Simulator.CelestialBodies.CelestialBody;
import Simulator.CelestialBodies.ODEFunction;
import Simulator.Solvers.ODESolver;

import java.io.OptionalDataException;

public class SolarSystem {

    public OptionalDataException celestialBody;
    public State[] states;

    private static double daySec = 60 * 24 * 60;

    public long timeFinal = 60 * 24 * 60 * 365 * 2; // 2 years
    private static double h = GlobalState.STEP_MULTIPLIER * daySec;

    public SolarSystem() {
        // Read values from file into celestial bodies
        ReadFile.updateCelestialBodyValues();

        states = new State[((int) Math.round((timeFinal / h)) + 1)];
        int length = states.length;

        for (int i = 0; i < length; i++)
            states[i] = new State();
    }

    public void initialProcess() {
        states[0].inputState();
        IODESolver solver = new ODESolver();
        states = (State[]) solver.solve(new ODEFunction(), (State) states[0], h, timeFinal);

        // This updates the positions of the space probe
        IVector3 probeVelocity = CelestialBody.spaceProbe.veloVec;
        states = (State[]) solver.solveProbe(new ODEFunction(), h, timeFinal, states, probeVelocity);
    }

    public State[] initialProcessOptimization(IVector3 probeVelocity) {
        states[0].inputState();
        IODESolver solver = new ODESolver();
        states = (State[]) solver.solve(new ODEFunction(), (State) states[0], h, timeFinal);

        // This updates the positions of the space probe
        states = (State[]) solver.solveProbe(new ODEFunction(), h, timeFinal, states, probeVelocity);

        return states;
    }

    // For genetic algorithm
    public void initialProcess(Vector3 velocity) {
        states[0].inputState();
        states[0].state[11][1] = velocity;
        IODESolver solver = new ODESolver();
        states = (State[]) solver.solve(new ODEFunction(), (State) states[0], h, timeFinal);
    }

    public void setStates(State[] states) {
        this.states = states;
    }

    public State[] getStates() {
        return states;
    }

    public CelestialBody[] getCelestialBodies() {
        return CelestialBody.celestialBodies;
    }

    public static void main(String[] args) {
        SolarSystem ss = new SolarSystem();
        ss.initialProcess();
        System.out.println(ss.states[1].state[1][0]);
    }
}

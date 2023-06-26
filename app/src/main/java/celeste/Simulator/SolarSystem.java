package celeste.Simulator;

import celeste.FileReader.ReadFile;
import celeste.GUI.GlobalState;
import celeste.Interface.IODESolver;
import celeste.Interface.IVector3;
import celeste.Simulator.CelestialBodies.CelestialBody;
import celeste.Simulator.CelestialBodies.ODEFunction;
import celeste.Simulator.Solvers.ODESolver;

import java.io.OptionalDataException;

/**
 * Eepresents a simulation of a solar system.
 */
public class SolarSystem {

    public OptionalDataException celestialBody;
    public State[] states;

    private static double daySec = 60 * 24 * 60;

    public long timeFinal = 60 * 24 * 60 * 365 * 2; // 2 years
    private static double h = GlobalState.STEP_MULTIPLIER * daySec;

    /**
     * Constructs a SolarSystem object.
     * Reads values from a file into celestial bodies and initializes the states array.
     */
    public SolarSystem() {
        // Read values from file into celestial bodies
        ReadFile.updateCelestialBodyValues();

        states = new State[((int) Math.round((timeFinal / h)) + 1)];
        int length = states.length;

        for (int i = 0; i < length; i++)
            states[i] = new State();
    }

    /**
     * Performs the initial processing of the simulation.
     * It inputs the initial state, solves the ordinary differential equations (ODEs)
     * for the celestial bodies, and updates the positions of the space probe.
     */
    public void initialProcess() {
        states[0].inputState();
        IODESolver solver = new ODESolver();
        states = (State[]) solver.solve(new ODEFunction(), (State) states[0], h, timeFinal);

        // This updates the positions of the space probe
        IVector3 probeVelocity = CelestialBody.spaceProbe.veloVec;
        states = (State[]) solver.solveProbe(new ODEFunction(), h, timeFinal, states, probeVelocity);
    }

    /**
     * Performs the initial processing of the simulation with optimization.
     * It inputs the initial state, solves the ordinary differential equations (ODEs)
     * for the celestial bodies, and updates the positions of the space probe.
     * used for optimization
     *
     * @param probeVelocity the velocity of the space probe as an IVector3 object.
     * @return the array of states representing the simulation.
     */
    public State[] initialProcessOptimization(IVector3 probeVelocity) {
        states[0].inputState();
        IODESolver solver = new ODESolver();
        states = (State[]) solver.solve(new ODEFunction(), (State) states[0], h, timeFinal);

        // This updates the positions of the space probe
        states = (State[]) solver.solveProbe(new ODEFunction(), h, timeFinal, states, probeVelocity);

        return states;
    }

    /**
     * Performs the initial processing of the simulation with optimization.
     * It inputs the initial state, solves the ordinary differential equations (ODEs)
     * for the celestial bodies, and updates the positions of the space probe.
     * used for genetic algorithm
     *
     * @param probeVelocity the velocity of the space probe as an IVector3 object.
     */
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

    /**
     * The main method of the SolarSystem class.
     * It creates a SolarSystem object, performs the initial processing of the simulation,
     * used in the earlier version to test, still kept for testing purposes
     */
    public static void main(String[] args) {
        SolarSystem ss = new SolarSystem();
        ss.initialProcess();
    }
}

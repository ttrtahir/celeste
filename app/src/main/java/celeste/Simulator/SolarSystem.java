package celeste.Simulator;

import celeste.FileReader.ReadFile;
import celeste.GUI.GlobalState;
import celeste.Interface.IODESolver;
import celeste.Simulator.CelestialBodies.CelestialBody;
import celeste.Simulator.CelestialBodies.ODEFunction;
import celeste.Simulator.Solvers.ODESolver;

import java.io.OptionalDataException;

public class SolarSystem {

    public OptionalDataException celestialBody;
    private State[] states;

    private static double daySec = 60 * 24 * 60;

    public long timeFinal = 60 * 24 * 60 * 365 * 2; // 2 years
    private static double h = GlobalState.STEP_MULTIPLIER * daySec;

    public SolarSystem() {
        readValuesFromFile();

        states = new State[((int) Math.round((timeFinal / h)) + 1)];
        int length = states.length;

        for (int i = 0; i < length; i++)
            states[i] = new State();
    }

    public void readValuesFromFile() {
        // Read values from file
        System.out.println("Lol");
        ReadFile.updateCelestialBodyValues();
    }

    public void initialProcess() {
        /*
         * rewrite this method to initialize the solar system
         */
        states[0].inputState();
        IODESolver solver = new ODESolver();
        states = (State[]) solver.solve(new ODEFunction(), (State) states[0], h, timeFinal);
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

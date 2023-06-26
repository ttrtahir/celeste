package celeste.Simulator;

import celeste.Interface.IAccelerationRate;
import celeste.Interface.IState;
import celeste.Interface.IVector3;
import celeste.Simulator.CelestialBodies.AccelerationRate;
import celeste.Simulator.CelestialBodies.CelestialBody;

/**
 * Represents the state of the simulation, including positions and velocities of celestial bodies.
 */
public class State implements IState {
    public int size; // The size of the state array
    public IVector3[][] state; // The state array containing positions and velocities of celestial bodies

    public State() {
        /* + 1 for space probe */
        state = new Vector3[CelestialBody.celestialBodies.length + 1][2];
        this.size = state.length;
    }

    /**
     * Sets the state array with initial positions and velocities of celestial bodies.
     */
    public void inputState() {
        for (int i = 0; i < CelestialBody.celestialBodies.length; i++) {
            state[i][0] = CelestialBody.celestialBodies[i].posVec;
            state[i][1] = CelestialBody.celestialBodies[i].veloVec;
        }
    }

    /**
     * Adds the product of a step size and acceleration rate to the positions and velocities of celestial bodies and returns the resulting state.
     *
     * @param step     The step size.
     * @param accRate  The acceleration rate.
     * @return The resulting state after addition and multiplication.
     */
    @Override
    public IState addmultiply(double step, IAccelerationRate accRate) {
        State newState = new State();

        for (int i = 0; i < CelestialBody.celestialBodies.length; i++) {
            newState.addPosition(i, this.state[i][0].addmultiply(step, ((AccelerationRate) accRate).getVelocity(i)));
            newState.addVelocity(i,
                    this.state[i][1].addmultiply(step, ((AccelerationRate) accRate).getAcceleration(i)));
        }
        return newState;
    }

    /**
     * Adds the product of a step size and acceleration rate to the position and velocity of the probe and returns the resulting state.
     *
     * @param step     The step size.
     * @param accRate  The acceleration rate.
     * @return The resulting state after addition and multiplication for the probe.
     */
    @Override
    public IVector3[] addmultiplyProbe(double step, IAccelerationRate accRate) {
        IVector3[] newState = new Vector3[2];

        newState[0] = this.state[11][0].addmultiply(step, ((AccelerationRate) accRate).getVelocity(0));
        newState[1] = this.state[11][1].addmultiply(step, ((AccelerationRate) accRate).getAcceleration(0));

        return newState;
    }

    /**
     * Sets the position of a celestial body.
     */
    public void addPosition(int i, IVector3 position) {
        state[i][0] = position;
    }

    /**
     * Sets the velocity of a celestial body.
     */
    public void addVelocity(int i, IVector3 velocity) {
        state[i][1] = velocity;
    }

    /**
     * Gets the position of a celestial body.
     */
    public IVector3 getPosition(int i) {
        return state[i][0];
    }

    /**
     * Gets the velocity of a celestial body.
     */
    public IVector3 getVelocity(int i) {
        return state[i][1];
    }

}

package Simulator;

import Interface.IAccelerationRate;
import Interface.IState;
import Interface.IVector3;
import Simulator.CelestialBodies.AccelerationRate;
import Simulator.CelestialBodies.CelestialBody;

public class State implements IState {
    public int size;
    public IVector3[][] state;

    public State() {
        /* + 1 for space probe */
        state = new Vector3[CelestialBody.celestialBodies.length + 1][2];
        this.size = state.length;
    }

    public void inputState() {
        for (int i = 0; i < CelestialBody.celestialBodies.length; i++) {
            state[i][0] = CelestialBody.celestialBodies[i].posVec;
            state[i][1] = CelestialBody.celestialBodies[i].veloVec;

            // System.out.println("Initial position of " +
            // CelestialBody.celestialBodies[i].name + " " + state[i][0]);
            // System.out.println("Initial velocity of " +
            // CelestialBody.celestialBodies[i].name + " " + state[i][1]);
        }
    }

    // update position and velocity
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

    /* Only updates the position and velocity of the probe */
    @Override
    public IVector3[] addmultiplyProbe(double step, IAccelerationRate accRate) {
        IVector3[] newState = new Vector3[2];

        newState[0] = this.state[11][0].addmultiply(step, ((AccelerationRate) accRate).getVelocity(0));
        newState[1] = this.state[11][1].addmultiply(step, ((AccelerationRate) accRate).getAcceleration(0));

        return newState;
    }

    public void addPosition(int i, IVector3 position) {
        state[i][0] = position;
    }

    public void addVelocity(int i, IVector3 velocity) {
        state[i][1] = velocity;
    }

    public IVector3 getPosition(int i) {
        return state[i][0];
    }

    public IVector3 getVelocity(int i) {
        return state[i][1];
    }

}

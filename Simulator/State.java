package Simulator;

import Interface.IAccelerationRate;
import Interface.IState;
import Interface.IVector3;

public class State implements IState {
    public int size;
    public IVector3[][] state;

    public State() {
        state = new Vector3[CelestialBody.celestialBodies.length][2];
        this.size = state.length;
    }

    public void inputState() {
        for (int i = 0; i < state.length; i++) {
            state[i][0] = CelestialBody.celestialBodies[i].posVec;
            state[i][1] = CelestialBody.celestialBodies[i].veloVec;

            //System.out.println("Initial position of " + CelestialBody.celestialBodies[i].name + " " + state[i][0]);
            //System.out.println("Initial velocity of " + CelestialBody.celestialBodies[i].name + " " + state[i][1]);
        }

    }
    //update position and velocity
    @Override
    public IState addmultiply(double step, IAccelerationRate accRate) {
        State newState = new State();

        for (int i = 0; i < this.state.length; i++) {

            newState.addPosition(i, this.state[i][0].addmultiply(step, ((AccelerationRate) accRate).getVelocity(i)));
            newState.addVelocity(i, this.state[i][1].addmultiply(step, ((AccelerationRate) accRate).getAcceleration(i)));
        }
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

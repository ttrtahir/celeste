package Physics;

import Interface.IAcceleration;
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
            state[i][0] = CelestialBody.celestialBodies[i].veloVec; //
            state[i][1] = CelestialBody.celestialBodies[i].posVec;
        }
    }

    @Override
    public IState addmultiply(double step, IAcceleration accRate) {
        State newState = new State();

        for (int i = 0; i < state.length; i++) {
            // update velocity
            newState.addVelocity(i, state[i][0].addmultiply(step, ((Acceleration) accRate).get(i)));
            // update position
            newState.addPosition(i, state[i][1].addmultiply(step, newState.getVelocity(i)));
        }

        int i = 11;
        System.out.println(
                "position after update " + CelestialBody.celestialBodies[i].name + " " + newState.getPosition(i));
        System.out.println(
                "velocity after update" + CelestialBody.celestialBodies[i].name + " " + newState.getVelocity(i));
        return newState;
    }

    public void addPosition(int i, IVector3 position) {
        state[i][1] = position;
    }

    public void addVelocity(int i, IVector3 velocity) {
        state[i][0] = velocity;
    }

    public IVector3 getPosition(int i) {
        return state[i][1];
    }

    public IVector3 getVelocity(int i) {
        return state[i][0];
    }
}

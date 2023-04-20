package Physics;

import Interface.AccelerationInterface;
import Interface.StateInterface;
import Interface.Vector3Interface;

public class State implements StateInterface {
    public int size;
    public Vector3Interface[][] state;

    public State(){
        state = new Vector3[CelestialBody.celestialBodies.length][2];
        this.size = state.length;
    }

    public void inputState(){
        for(int i = 0; i < state.length; i++){
            state[i][0] = CelestialBody.celestialBodies[i].veloVec; //
            state[i][1] = CelestialBody.celestialBodies[i].posVec;
        }
    }

    @Override
    public StateInterface addmultiply(double step, AccelerationInterface accRate) {
        State newState = new State();

        for(int i = 0; i< state.length; i++){
            //update velocity
            newState.addVelocity(i, state[i][0].addmultiply(step, ((Acceleration) accRate).get(i)));
            //update position
            newState.addPosition(i, state[i][1].addmultiply(step, newState.getVelocity(i)));
        }

        int i = 11;
        System.out.println("position after update " + CelestialBody.celestialBodies[i].name +" " + newState.getPosition(i));
        System.out.println("velocity after update" + CelestialBody.celestialBodies[i].name + " " + newState.getVelocity(i));
        return newState;
    }

    public void addPosition(int i, Vector3Interface position){
        state[i][1] = position;
    }
    public void addVelocity(int i, Vector3Interface velocity){
        state[i][0] = velocity;
    }
    public Vector3Interface getPosition(int i){
        return state[i][1];
    }
    public Vector3Interface getVelocity(int i){
        return state[i][0];
    }
}

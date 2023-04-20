package Physics;

import Interface.CalculationInterface;
import Interface.FunctionInterface;
import Interface.StateInterface;

public class Calculation implements CalculationInterface {
    public State[] states;
    @Override
    public StateInterface[] solve(FunctionInterface f, StateInterface y0, double[] timestep) {

        states = new State[timestep.length];
        states[0] = (State) y0;

        for(int i =1; i < states.length; i++){
            states[i] = (State) step(f,timestep[i], states[i-1], (timestep[i] - timestep[i-1]));
        }
        return states;
    }

    @Override
    public StateInterface[] solve(FunctionInterface f, StateInterface y0,double timefinal, double h) {
        double[] timeStep = new double[(int) (Math.round((timefinal/h)+1))];
        timeStep[0] =0;
        for (int i = 1; i < timeStep.length; i++){
            timeStep[i] = timeStep[i-1]+h;
        }
        states = new State[(int) (Math.round(timefinal/h)+1)];
        states[0] = (State) y0;

        for(int i = 1; i < states.length; i++){
            states[i] =(State) step(f, timeStep[i], states[i-1], (timeStep[i]-timeStep[i-1]));

        }
        return states;
    }

    @Override
    public StateInterface step(FunctionInterface f, double t, StateInterface y, double h) {
        State newState = (State) y.addmultiply(h, f.motion(h,y));
        return newState;
    }
}

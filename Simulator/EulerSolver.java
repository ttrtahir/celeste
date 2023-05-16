package Simulator;

import Interface.IODEFunction;
import Interface.IODESolver;
import Interface.IState;

/*
* class implementing Euler's method to calculate motion of objects in the solar system
* */

public class EulerSolver extends ODESolver {
    public State[] states;

    public Vector3[] earthPosition;
    public Vector3 earthPosOneYear;
    public Vector3[] titanPosition;
    public Vector3 titanPosOneYear;

/*
    /**
     * solve ODE
     *
     * @param f  a Function calculating force
     * @param y0 initial state
     * @param timeStep updated time steps
     * @return states of Solar System at different time
     
    @Override
    public IState[] solve(IODEFunction f, IState y0, double[] timeStep) {
        //create array storing states at different time steps
        states = new State[timeStep.length];
        states[0] = (State) y0;

        //create array to store positions of titan
        titanPosition = new Vector3[timeStep.length];
        titanPosition[0] = (Vector3) states[0].getPosition(8);

        //create array to store positions of earth
        earthPosition = new Vector3[timeStep.length];
        earthPosition[0] = (Vector3) states[0].getPosition(3);

        //updating positions for 1 step
        updateState(f, timeStep);
        return states;
    }*/
    /*
    @Override
    public IState[] solve(IODEFunction f, IState y0, double timeFinal, double h) {

        //get array to store separate time step
        double[] timeStep = new double[(int) (Math.round((timeFinal/h))+ 1)];
        timeStep[0] = 0;
        for(int i = 1; i < timeStep.length; i++){
            timeStep[i] = timeStep[i-1] + h;
            if(i == timeStep.length -1){
                timeStep[i] = timeFinal;
            }
        }
        //create array to store states at different time step
        states = new State[(int) (Math.round(timeFinal/h)+1)];
        states[0] = (State) y0;

        //create array to store positions of the titan
        titanPosition = new Vector3[timeStep.length];
        titanPosition[0] = (Vector3) states[0].getPosition(8);

        //create array to store positions of earth

        //update positions for 1 step
        updateState(f, timeStep);

        return states;
    }

    private void updateState(IODEFunction f, double[] timeStep) {
        for(int i = 1; i < states.length; i++){
            states[i] = (State) step(f, timeStep[i],states[i-1], (timeStep[i] - timeStep[i-1]));
            //if(i == 1){
                //states[i].addVelocity(11, );
            //}
            titanPosition[i] = (Vector3) states[i].getPosition(8);
            earthPosition[i] = (Vector3) states[i].getPosition(3);
        }
        titanPosOneYear = (Vector3) states[states.length -1].getPosition(8);
        earthPosOneYear = (Vector3) states[states.length-1].getPosition(3);
    }
    */
    /**
     * calculating one step
     *
     * @param f simulator.ODEFunction implementing Newton's law
     * @param t time
     * @param y state
     * @param h step size
     * @return new state of the system after one update step
     */
    @Override
    public IState step(IODEFunction f, double t, IState y, double h) {
        State newState = (State) y.addmultiply(h, f.call(t,y));
        return newState;
    }


}

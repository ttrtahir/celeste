package celeste.Simulator.CelestialBodies;

import celeste.Simulator.Solvers.*;
import celeste.Simulator.State;
import celeste.Interface.IODEFunction;
import celeste.Interface.IODESolver;
import celeste.Interface.IState;

public class LandingModule {
    
    private IODESolver solver;
    private IODEFunction function;
    private State currentState;
    private PIDController[] controllers;

    public LandingModule(IODEFunction function) {
        this.solver = new RK2();
        this.function = function;
        this.currentState = new State();

        this.controllers = new PIDController[3];
        for (int i = 0; i < 3; i++) {
            this.controllers[i] = new PIDController(0,0,0);
        }
    }

    public void stimulateMotion(double h, double timefinal) {
        // timefinal - total time of simulation, h - step size
        
        IState[] states = solver.solve(function, currentState, h, timefinal);
        
        for (IState state : states) {
            //Resulting states
            System.out.println(state.toString());
        }
    }

    public double[] openLoopController(double t) {
       
        double[] control = new double[3];
        control[0] = 0;/* control for x */
        control[1] = 0;/* control for y */
        control[2] = 0;/* control for z */
        return control;
    }

    public double[] feedbackController(State currentState, State desiredState, double dt) {
        
        double[] control = new double[3];

        for (int i = 0; i < 3; i++) {
            controllers[i].setSetPoint(desiredState.state[i][0]);
            control[i] = controllers[i].calculate(currentState.state[i][0]);
        }

        return control;
    }

    public void windModel() {
        //TO-DO
    }

    public boolean checkLanding() {
        //TO-DO
        return false;
    }
}

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

    public LandingModule(IODEFunction function) {
        this.solver = new RK2();
        this.function = function;
        this.currentState = new State(); 
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
        return control;
    }

  
    public double[] feedbackController(State currentState, State desiredState) {

        double[] control = new double[3]; 
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


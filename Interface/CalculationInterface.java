package Interface;

public interface CalculationInterface{
    /*
    *  class to solve differential equation dy/dt = f(t,y)
    *       y(t): state of the system at time t
    *       f(t,y(t)): derivative from y(t) at time t
    * */
    public StateInterface[] solve(FunctionInterface f, StateInterface y0, double[] timestep);
    public StateInterface[] solve(FunctionInterface f, StateInterface y0, double h, double timefinal);
    public StateInterface step(FunctionInterface f, double t, StateInterface st, double h);
}

package Interface;

public interface CalculationInterface{
    public StateInterface[] solve(FunctionInterface f, StateInterface y0, double[] timestep);
    public StateInterface[] solve(FunctionInterface f, StateInterface y0, double timefinal, double h);
    public StateInterface step(FunctionInterface f, double t, StateInterface st, double h);
}

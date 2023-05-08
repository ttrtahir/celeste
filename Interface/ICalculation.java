package Interface;

public interface ICalculation {

    public IState[] solve(IODEFunction f, IState y0, double[] timestep);

    public IState[] solve(IODEFunction f, IState y0, double h, double timefinal);

    public IState step(IODEFunction f, double t, IState st, double h);
}

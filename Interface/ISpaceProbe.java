package Interface;

public interface ISpaceProbe {

    /**
     *
     * */
    IVector3[] trajectory(IVector3 p0, IVector3 v0, double[] timestep);

    /**
     *
     * */
    IVector3[] trajectory(IVector3 p0, IVector3 v0, double timefinal, double h);

}

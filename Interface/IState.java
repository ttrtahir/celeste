package Interface;

public interface IState {
    // state of Solar System implemented by a differential equation

    /**
     * update a new state
     *
     * @param step    time step of update
     * @param accRate acceleration rate over time-step
     */
    public IState addmultiply(double step, IAccelerationRate accRate);

    public IVector3[] addmultiplyProbe(double step, IAccelerationRate accRate);
}

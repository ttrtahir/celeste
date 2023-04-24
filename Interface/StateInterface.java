package Interface;

public interface StateInterface {
    //state of Solar System implemented by a differential equation

    /**
     * update a new state
     *
     * @param step time step of update
     * @param accRate acceleration rate over time-step
     * */
    public StateInterface addmultiply(double step, AccelerationInterface accRate);
}

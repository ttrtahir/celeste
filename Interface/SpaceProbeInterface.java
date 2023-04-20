package Interface;

public interface SpaceProbeInterface {
    Vector3Interface[] trajectory(Vector3Interface p0, Vector3Interface v0, double timefinal, double h);
    Vector3Interface[] trajectory(Vector3Interface p0, Vector3Interface v0, double[] timestep);

}

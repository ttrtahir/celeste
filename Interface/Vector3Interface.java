package Interface;

public interface Vector3Interface {
    public double getX();
    public void setX(double x);
    public double getY();
    public void setY(double y);
    public double getZ();
    public void setZ(double z);

    public Vector3Interface add(Vector3Interface anotherVector);
    public Vector3Interface subtract(Vector3Interface anotherVector);
    public Vector3Interface multiply(double scalar);

    public double euclideanDist(Vector3Interface anotherVector);

    /**
     * Multiply and then add
     *eg: Vector3 a, Vector3 b, double h
     *    a+ hb = a.addmultiply(h,b)
     * */
    public Vector3Interface addmultiply(double scalar, Vector3Interface anotherVector);

    public String toString();
}

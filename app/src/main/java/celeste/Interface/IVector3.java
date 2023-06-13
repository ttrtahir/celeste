package app.src.main.java.celeste.Interface;

public interface IVector3 {
    public double getX();

    public void setX(double x);

    public double getY();

    public void setY(double y);

    public double getZ();

    public void setZ(double z);

    public IVector3 add(IVector3 anotherVector);

    public IVector3 subtract(IVector3 anotherVector);

    public IVector3 multiply(double scalar);

    // Euclidean distance between two vectors
    public double euclideanDist(IVector3 anotherVector);

    /**
     * Multiply and then add
     * eg: Vector3 a, Vector3 b, double h
     * a+ hb = a.addmultiply(h,b)
     */
    public IVector3 addmultiply(double scalar, IVector3 anotherVector);

    public double getMagnitude();

    public String toString();
}

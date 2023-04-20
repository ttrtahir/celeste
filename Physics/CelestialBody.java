package Physics;

import Interface.Vector3Interface;

public class CelestialBody {
    public String name;
    public int step;

    public double mass;
    public double size;
    public double gravity;

    //velocity
    public double veloX;
    public double veloY;
    public double veloZ;


    //position
    public double posX;
    public double posY;
    public double posZ;


    //array to store all celestial bodies
    public static CelestialBody[] celestialBodies;

    //vectors
    public Vector3Interface posVec;
    public Vector3Interface veloVec;
    public Vector3Interface accelVec;


    public CelestialBody(String celestialName){
        name = celestialName;
        step = 0;
    }

    public void createVectors(){
        posVec = new Vector3(posX, posY, posZ);
        veloVec = new Vector3(veloX, veloY, veloZ);
        accelVec = new Vector3(0, 0, 0);
    }
}

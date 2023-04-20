package Physics;

public class CelestialBodyValues {
    public CelestialBody[] celestialBodies;
    public CelestialBodyValues(){
        celestialBodies = new CelestialBody[12];

        //sun
        CelestialBody sun = new CelestialBody("Sun");
        sun.mass = 1.9885e+30;
        sun.size = 2;
        sun.posX = 0;
        sun.posY = 0;
        sun.posZ = 0;
        sun.veloX = 0;
        sun.veloY = 0;
        sun.veloZ = 0;
        sun.createVectors();
        celestialBodies[0] = sun;

        //mercury

        //venus

        //earth

        //moon

        //
    }
}

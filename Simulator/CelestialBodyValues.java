package Simulator;

public class CelestialBodyValues {
    public CelestialBody[] celestialBodies;
    public CelestialBodyValues(){
        celestialBodies = new CelestialBody[12];
        //sun
        CelestialBody sun = new CelestialBody("Sun");
        sun.mass = 1.9885e+30;
        //sun.size = 2;
        sun.posX = 0;
        sun.posY = 0;
        sun.posZ = 0;
        sun.veloX = 0;
        sun.veloY = 0;
        sun.veloZ = 0;
        sun.createVectors();
        celestialBodies[0] = sun;

        //mercury
        CelestialBody mercury = new CelestialBody("Mercury");
        mercury.mass = 3.302e+23;
        mercury.posX = 7.833268e+06;
        mercury.posY = 4.4885949e+07;
        mercury.posZ = 2.867693e+06;
        mercury.veloX = -5.754967480139828e+01;
        mercury.veloY = 1.152095127176+01;
        mercury.veloZ = 6.21695374334136e+00;
        mercury.createVectors();
        celestialBodies[1] = mercury;

        //venus
        CelestialBody venus = new CelestialBody("Venus");
        venus.mass = 4.8685e+23;
        venus.posX = -2.8216773e+07;
        venus.posY = 1.03994008e+08;
        venus.posZ = 3.012326e+06;
        venus.veloX = -3.40236737e+01;
        venus.veloY = -8.96521274e+00;
        venus.veloZ = 1.840617353e+00;
        venus.createVectors();
        celestialBodies[2] = venus;


        //earth
        CelestialBody earth = new CelestialBody("Earth");
        earth.mass = 5.97219e+24;
        earth.posX = -1.48186906e+08;
        earth.posY = -2.7823158e+07;
        earth.posZ = 3.3746898e+04;
        earth.veloX = 5.052515e+00;
        earth.veloY = -2.939266e+01;
        earth.veloZ = 1.70974274e-03;
        earth.createVectors();
        celestialBodies[3] = earth;


        //moon
        CelestialBody moon = new CelestialBody("Moon");
        moon.mass = 7.349e+22;
        moon.posX = -1.48458048e+08;
        moon.posY = -2.7823158e+07;
        moon.posZ = 7.0233e+04;
        moon.veloX = 4.34032634e+00;
        moon.veloY = -3.00480834e+01;
        moon.veloZ = -1.16103535e-02;
        moon.createVectors();
        celestialBodies[4] = moon;

        //mars
        CelestialBody mars = new CelestialBody("Mars");
        mars.mass = 6.4171e+23;
        mars.posX = -1.59116303e+08;
        mars.posY = 1.89235671e+08;
        mars.posZ = 7.870476e+06;
        mars.veloX = -1.76954469e+01;
        mars.veloY = -1.34635253e+01;
        mars.veloZ = 1.523319282e-01;
        mars.createVectors();
        celestialBodies[5] = mars;

        //jupiter
        CelestialBody jupiter = new CelestialBody("Jupiter");
        jupiter.mass = 1.89818722e+27;
        jupiter.posX = 6.92722875e+08;
        jupiter.posY = 2.58560760e+08;
        jupiter.posZ = -1.6570817e+07;
        jupiter.veloX = -4.7144306e+00;
        jupiter.veloY = 1.285550969e+01;
        jupiter.veloZ = 5.221181269e-02;
        jupiter.createVectors();
        celestialBodies[6] = jupiter;

        //saturn
        CelestialBody saturn = new CelestialBody("Saturn");
        saturn.mass = 5.6834e+26;
        saturn.posX = 1.254501624e+09;
        saturn.posY = -7.60453007e+08;
        saturn.posZ = -3.6697431e+07;
        saturn.veloX = 4.4678134e+00;
        saturn.veloY = 8.2398954e+00;
        saturn.veloZ = -3.207454e-01;
        saturn.createVectors();
        celestialBodies[7] = saturn;


        //titan
        CelestialBody titan = new CelestialBody("Titan");
        titan.mass = 1.34552e+23;
        titan.posX = 1.254501624e+09;
        titan.posY = -7.61340299e+08;
        titan.posZ = -3.6309613e+07;
        titan.veloX = 8.9959323e+00;
        titan.veloY = 1.1108571e+01;
        titan.veloZ = -2.251309e+00;
        titan.createVectors();
        celestialBodies[8] = titan;

        //neptune
        CelestialBody neptune = new CelestialBody("Neptune");
        neptune.mass = 1.02409e+26;
        neptune.posX = 4.454487349e+09;
        neptune.posY = -3.97895128e+08;
        neptune.posZ = -9.4464151e+07;
        neptune.veloX = 4.47991656e-01;
        neptune.veloY = 5.44610698e+00;
        neptune.veloZ = -1.22638125e-01;
        neptune.createVectors();
        celestialBodies[9] = neptune;

        //uranus
        CelestialBody uranus = new CelestialBody("Uranus");
        uranus.mass = 8.6813e+24;
        uranus.posX = 1.958732435e+09;
        uranus.posY = 2.191808553e+09;
        uranus.posZ = -1.7235283e+07;
        uranus.veloX = -5.127662163e+00;
        uranus.veloY = 4.220553472e+00;
        uranus.veloZ = 8.211903364e-02;
        uranus.createVectors();
        celestialBodies[10] = uranus;




        //space probe
        CelestialBody spaceProbe = new CelestialBody("Space Probe");
        spaceProbe.mass = 50000;
        spaceProbe.posX = -148186906.893642;
        spaceProbe.posY = -27814158.5715694;
        spaceProbe.posZ = 33746.8987977113;
        spaceProbe.veloX = 47;
        spaceProbe.veloY = -43.49;
        spaceProbe.veloZ = -3.3;
        spaceProbe.createVectors();
        celestialBodies[11] = spaceProbe;

        //celestialBodies[i].color = Values.COLORS[i];
        //celestialBodies[i].size = Values.SIZES[i];
        //celestialBodies[i].name = Values.NAMES[i];
    }
}

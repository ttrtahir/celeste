package celeste.Simulator.CelestialBodies;

/**
* Earlier version, currently a redundant class
* Contains initial celestial body values
*/
public class CelestialBodyValues {
    // public CelestialBody[] celestialBodies;
    public CelestialBodyValues() {
        CelestialBody.celestialBodies = new CelestialBody[12];
        // sun
        CelestialBody sun = new CelestialBody("Sun");
        sun.mass = 1.9885e+30;
        // sun.size = 2;
        sun.posX = 0;
        sun.posY = 0;
        sun.posZ = 0;
        sun.veloX = 0;
        sun.veloY = 0;
        sun.veloZ = 0;
        sun.createVectors();
        CelestialBody.celestialBodies[0] = sun;

        // mercury
        CelestialBody mercury = new CelestialBody("Mercury");
        mercury.mass = 3.302e+23;
        mercury.posX = 7833268.43923962;
        mercury.posY = 44885949.3703908;
        mercury.posZ = 2867693.20054382;
        mercury.veloX = -57.4967480139828;
        mercury.veloY = 11.52095127176;
        mercury.veloZ = 6.21695374334136;
        mercury.createVectors();
        CelestialBody.celestialBodies[1] = mercury;

        // venus
        CelestialBody venus = new CelestialBody("Venus");
        venus.mass = 48.685e23;
        venus.posX = -28216773.9426889;
        venus.posY = 103994008.541512;
        venus.posZ = 3012326.64296788;
        venus.veloX = -34.0236737066136;
        venus.veloY = -8.96521274688838;
        venus.veloZ = 1.84061735279188;
        venus.createVectors();
        CelestialBody.celestialBodies[2] = venus;

        // earth
        CelestialBody earth = new CelestialBody("Earth");
        earth.mass = 5.97219e24;
        earth.posX = -148186906.893642;
        earth.posY = -27823158.5715694;
        earth.posZ = 33746.8987977113;
        earth.veloX = 5.05251577575409;
        earth.veloY = -29.3926687625899;
        earth.veloZ = 0.00170974277401292;
        earth.createVectors();
        CelestialBody.celestialBodies[3] = earth;

        // moon
        CelestialBody moon = new CelestialBody("Moon");
        moon.mass = 7.349e+22;
        moon.posX = -148458048.395164;
        moon.posY = -27524868.1841142;
        moon.posZ = 70233.6499287411;
        moon.veloX = 4.34032634654904;
        moon.veloY = -30.0480834180741;
        moon.veloZ = -0.0116103535014229;
        moon.createVectors();
        CelestialBody.celestialBodies[4] = moon;

        // mars
        CelestialBody mars = new CelestialBody("Mars");
        mars.mass = 6.4171e+23;
        mars.posX = -159116303.422552;
        mars.posY = 189235671.561057;
        mars.posZ = 7870476.08522969;
        mars.veloX = -17.6954469224752;
        mars.veloY = -13.4635253412947;
        mars.veloZ = 0.152331928200531;
        mars.createVectors();
        CelestialBody.celestialBodies[5] = mars;

        // jupiter
        CelestialBody jupiter = new CelestialBody("Jupiter");
        jupiter.mass = 189818722e19;
        jupiter.posX = 692722875.928222;
        jupiter.posY = 258560760.813524;
        jupiter.posZ = -16570817.7105996;
        jupiter.veloX = -4.71443059866156;
        jupiter.veloY = 12.8555096964427;
        jupiter.veloZ = 0.0522118126939208;
        jupiter.createVectors();
        CelestialBody.celestialBodies[6] = jupiter;

        // saturn
        CelestialBody saturn = new CelestialBody("Saturn");
        saturn.mass = 5.6834e+26;
        saturn.posX = 1253801723.95465;
        saturn.posY = -760453007.810989;
        saturn.posZ = -36697431.1565206;
        saturn.veloX = 4.46781341335014;
        saturn.veloY = 8.23989540475628;
        saturn.veloZ = -0.320745376969732;
        saturn.createVectors();
        CelestialBody.celestialBodies[7] = saturn;

        // titan
        CelestialBody titan = new CelestialBody("Titan");
        titan.mass = 1.34552e+23;
        titan.posX = 1254501624.95946;
        titan.posY = -761340299.067828;
        titan.posZ = -36309613.8378104;
        titan.veloX = 8.99593229549645;
        titan.veloY = 11.1085713608453;
        titan.veloZ = -2.25130986174761;
        titan.createVectors();
        CelestialBody.celestialBodies[8] = titan;

        // neptune
        CelestialBody neptune = new CelestialBody("Neptune");
        neptune.mass = 1.02409e+26;
        neptune.posX = 4454487339.09447;
        neptune.posY = -397895128.763904;
        neptune.posZ = -94464151.3421107;
        neptune.veloX = 0.447991656952326;
        neptune.veloY = 5.44610697514907;
        neptune.veloZ = -0.122638125365954;
        neptune.createVectors();
        CelestialBody.celestialBodies[9] = neptune;

        // uranus
        CelestialBody uranus = new CelestialBody("Uranus");
        uranus.mass = 8.6813e+25;
        uranus.posX = 1958732435.99338;
        uranus.posY = 2191808553.21893;
        uranus.posZ = -17235283.8321992;
        uranus.veloX = -5.12766216337626;
        uranus.veloY = 4.22055347264457;
        uranus.veloZ = 0.0821190336403063;
        uranus.createVectors();
        CelestialBody.celestialBodies[10] = uranus;

        // space probe
        CelestialBody spaceProbe = new CelestialBody("Space Probe");
        spaceProbe.mass = 50000;
        spaceProbe.posX = -148186906.893642;
        spaceProbe.posY = -27814158.5715694;
        spaceProbe.posZ = 33746.8987977113;
        spaceProbe.veloX = 47;
        spaceProbe.veloY = -43.49;
        spaceProbe.veloZ = -3.3;
        spaceProbe.createVectors();
        CelestialBody.celestialBodies[11] = spaceProbe;

        // celestialBodies[i].color = Values.COLORS[i];
        // celestialBodies[i].size = Values.SIZES[i];
        // celestialBodies[i].name = Values.NAMES[i];
    }
}

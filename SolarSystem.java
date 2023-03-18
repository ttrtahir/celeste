public class SolarSystem {
    CelestialBody Sun;
    CelestialBody Earth;
    CelestialBody Jupiter;
    CelestialBody Titan;
    public SolarSystem(){
        this.Sun = new CelestialBody("Sun", 0,0,0,0,0,0,1.9885E30);
        this.Earth = new CelestialBody("Earth", -148186906.893642,-27823158.5715694,33746.8987977113,5.05251577575409,-29.3926687625899,0.00170974277401292,5.97219E24);
        this.Jupiter = new CelestialBody("Jupiter", 692722875.928222,258560760.813524,-16570817.7105996,-4.71443059866156,12.8555096964427,0.0522118126939208,189818722E19);
        this.Titan = new CelestialBody("Titan", 1254501624.95946, -761340299.067828, -36309613.8378104, 8.99593229549645, 11.1085713608453, -2.25130986174761, 13455.3E19);
    }
//    public static void initWithExcel(){
//
//    }
}

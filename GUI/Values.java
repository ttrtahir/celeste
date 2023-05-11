package GUI;
/*
 * Contains the values required for the project
 */
import java.awt.Color;

public class Values {
        //Contains the positions of the planets
        public static double[][] positions = {
                        { 0, 0, 0 }, // 1
                        { 7833268.43923962, 44885949.3703908, 2867693.20054382 }, // 2
                        { -28216773.9426889, 103994008.541512, 3012326.64296788 }, // 3
                        { -148186906.893642, -27823158.5715694, 33746.8987977113 }, // 4
                        { -148458048.395164, -27524868.1841142, 70233.6499287411 }, // 5
                        { -159116303.422552, 189235671.561057, 7870476.08522969 }, // 6
                        { 692722875.928222, 258560760.813524, -16570817.7105996 }, // 7
                        { 1253801723.95465, -760453007.810989, -36697431.1565206 }, // 8
                        { 1254501624.95946, -761340299.067828, -36309613.8378104 }, // 9
                        { 4454487339.09447, -397895128.763904, -94464151.3421107 }, // 10
                        { 1958732435.99338, 2191808553.21893, -17235283.8321992 }, // 11
                        { -148186906.893642, -27814158.5715694, 33746.8987977113 } }; // 12
        //Contains the veolcity of the planets
        public static double[][] velocity = {
                        { 0, 0, 0 }, // 1
                        { -57.4967480139828, 11.52095127176, 6.21695374334136 }, // 2
                        { -34.0236737066136, -8.96521274688838, 1.84061735279188 }, // 3
                        { 5.05251577575409, -29.3926687625899, 0.00170974277401292 }, // 4
                        { 4.34032634654904, -30.0480834180741, -0.0116103535014229 }, // 5
                        { -17.6954469224752, -13.4635253412947, 0.152331928200531 }, // 6
                        { -4.71443059866156, 12.8555096964427, 0.0522118126939208 }, // 7
                        { 4.46781341335014, 8.23989540475628, -0.320745376969732 }, // 8
                        { 8.99593229549645, 11.1085713608453, -2.25130986174761 }, // 9
                        { 0.447991656952326, 5.44610697514907, -0.122638125365954 }, // 10
                        { -5.12766216337626, 4.22055347264457, 0.0821190336403063 }, // 11
                        { 47, -43.49, -3.3 }, }; // 12
        //Contains the mass of the planets
        public static double[][] mass = {
                        { 1.9885e30 }, // 1
                        { 3.302e23 }, // 2
                        { 48.685e23 }, // 3
                        { 5.97219e24 }, // 4
                        { 7.349e22 }, // 5
                        { 6.4171e23 }, // 6
                        { 189818722e19 }, // 7
                        { 5.6834e26 }, // 8
                        { 13455e19 }, // 9
                        { 102.409e24 },
                        { 86.813e24 },
                        { 50000 } };
        //Contains the colors of the planets
        public static final Color[] COLORS = { new Color(0xffde3b), new Color(0x009973), new Color(0xffad33),
                        new Color(0x6fa8dc), new Color(0xa1a2a9), new Color(0xcc7a00), new Color(0xbcafb2),
                        new Color(0x99ccff), new Color(0xa1a2a9), new Color(0x0000ff), new Color(0x99d6ff),
                        new Color(0xFF0000) };
        //Contains the sizes of the planets
        public static final int[] SIZES = {
                        30,
                        12,
                        18,
                        20,
                        4,
                        16,
                        43,
                        36,
                        4,
                        34,
                        28,
                        12 };
        //Contains the names of the planets
        public static final String[] NAMES = { "Sun", "Mercury", "Venus", "Earth", "Moon", "Mars", "Jupiter", "Saturn",
                        "Titan", "Uranus", "Neptune", "Missile" };
        //Contains the orbit names of the planets
        public static final String[] ORBIT_NAMES = {
                        "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn"
        };
        //Contains the months of the year
        public static final String[] MONTHS = {
                        "January",
                        "February",
                        "March",
                        "April",
                        "May",
                        "June",
                        "July",
                        "August",
                        "September",
                        "October",
                        "November",
                        "December"
        };
        //Contains the total days in each month
        public static final int[] DAYS_IN_MONTH = {
                        31,
                        28,
                        31,
                        30,
                        31,
                        30,
                        31,
                        31,
                        30,
                        31,
                        30,
                        31
        };
        //Contains the focus index
        public static final int[] focusIndex = {
                        0,
                        3,
                        8,
                        11
        };

        public static final int[] YEAR_LENGTHS = {
                        -1,
                        88,
                        285,
                        406,
                        -1,
                        758,
                        4640,
                        11782,
                        -1,
                        30985,
                        61190,
                        -1
        };
}

package GUI;

import GUI.Drawables.PlanetStats;
import GUI.Drawables.Text;
import GUI.Resources.StyleValues;
import GUI.Drawables.Drawable;
import GUI.Drawables.Planet;

import java.util.ArrayList;

public class HelperFunctions {
    public static void addPlanetsToDrawables(ArrayList<PlanetStats> planetStats, ArrayList<Drawable> drawables) {
        for (int i = 0; i <= 11; i++) {
            planetStats.add(new PlanetStats(StyleValues.NAMES[i], StyleValues.SIZES[i], StyleValues.COLORS[i]));

            drawables.add(new Planet(planetStats.get(i)));
        }
    }

    public static void createUIText(ArrayList<Drawable> drawables, ArrayList<Text> uiTexts) {
        Text currentDateText = new Text(GlobalState.getCenter()[0], 40, "center");
        Text daysText = new Text(GlobalState.getCenter()[0], 75, "center");
        Text simulationSpeed = new Text("left", "bottom");
        Text nameFocusedPlanet = new Text("right", "bottom");
        Text currentIteration = new Text("right", "top");
        currentDateText.setBigFont(true);

        uiTexts.add(currentDateText);
        uiTexts.add(daysText);
        uiTexts.add(simulationSpeed);
        uiTexts.add(nameFocusedPlanet);
        uiTexts.add(currentIteration);

        for (Text text : uiTexts) {
            drawables.add(text);
        }
    }
}

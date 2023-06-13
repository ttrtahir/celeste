package celeste.GUI.Resources;

/*
 * Contains the values required for the project
 */
import java.awt.Color;

public class StyleValues {
	// Contains the colors of the planets
	public static final Color[] COLORS = { new Color(0xffde3b), new Color(0x009973), new Color(0xffad33),
			new Color(0x6fa8dc), new Color(0xa1a2a9), new Color(0xcc7a00), new Color(0xbcafb2),
			new Color(0x99ccff), new Color(0xa1a2a9), new Color(0x0000ff), new Color(0x99d6ff),
			new Color(0xFF0000) };
	// Contains the sizes of the planets
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
			20 };
	// Contains the names of the planets
	public static final String[] NAMES = { "Sun", "Mercury", "Venus", "Earth", "Moon", "Mars", "Jupiter", "Saturn",
			"Titan", "Uranus", "Neptune", "Missile" };
	// Contains the orbit names of the planets
	public static final String[] ORBIT_NAMES = {
			"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn"
	};
	// Contains the months of the year
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
	// Contains the total days in each month
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
	// Contains the focus index
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

	public static int[] daysPassedToDate(int daysPassed) {
		/* Start Date: 1st of April 2023 */
		int[] startDate = { 2023, 4, 1 };

		// Calculate what date is it if daysPassed days have passed
		int[] date = { startDate[0], startDate[1], startDate[2] };

		// Add the days to the date
		date[2] += daysPassed;
		// If the days exceed the days in the month
		while (date[2] > DAYS_IN_MONTH[date[1] - 1]) {
			// Subtract the days in the month from the days
			date[2] -= DAYS_IN_MONTH[date[1] - 1];
			if (date[1] == 12) {
				// Increment the year
				date[0]++;
				// Reset the month
				date[1] = 1;
			} else {
				// Increment the month
				date[1]++;
			}
		}

		// Return the date
		return date;
	}
}

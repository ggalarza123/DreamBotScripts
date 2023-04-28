package agilitycontrollers;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;

import agilitymodel.AgilityCourse;
import agilitymodel.TileAreaLocations;

public class CourseRunner {

	GameObject[] listOfObstacles;
	int[] restTimes;

	public CourseRunner(AgilityCourse agilityCourse) {
		listOfObstacles = agilityCourse.getListOfObstacles();
		restTimes = agilityCourse.getRestTimes();
	}

	public void runCourse(double sleepMultiplier) {
		int obst = checkWhichObstacleToRun();
		if (obst == -1) {
			Sleep.sleep(1000);
		}
		// OLD METHOD HERE ONLY REMOVING FOR VARROCK AND NEED TO CHECK ALL OTHERS
//		else if (listOfObstacles[obst].canReach()) {
//			listOfObstacles[obst].interact();
//			Sleep.sleep((long) (Utility.randomNum(restTimes[obst], (int) (restTimes[obst] * 1.1)) * sleepMultiplier));
//		}
		else {
			listOfObstacles[obst].interact();
			// This one for testing*************
//			Sleep.sleep(restTimes[obst]);

			// This one for nontesting************
			Sleep.sleep((long) (Utility.randomNum(restTimes[obst], (int) (restTimes[obst] * 1.05)) * sleepMultiplier));
		}

	}

	private int checkWhichObstacleToRun() {

		if (RuthlessAgilityMain.course.equals("Draynor Village Rooftop Course")) {

			if (TileAreaLocations.draynorObst5GapArea.contains(Players.getLocal())) {

				return 5;
			}
		}
		if (RuthlessAgilityMain.course.equals("Al Kharid Rooftop Course")

				&& TileAreaLocations.alKharidObst4Area.contains(Players.getLocal())) {
			return 4;
		}

		if (RuthlessAgilityMain.course.equals("Gnome Stronghold Agility Course")) {

			if (TileAreaLocations.gnomeLastObstacleArea.contains(Players.getLocal())) {
				return listOfObstacles.length - 1;
			}
			if (TileAreaLocations.gnomeObst1Area.contains(Players.getLocal())) {
				return 1;
			}
			if (TileAreaLocations.gnomeObst5Area.contains(Players.getLocal())) {
				return 5;
			}
			for (int i = 0; i < listOfObstacles.length; i++) {
				if (listOfObstacles[i] != null && listOfObstacles[i].canReach()) {
					return i;
				}
			}
		}

		if (RuthlessAgilityMain.course.equals("Varrock Rooftop Course")) {
			if (TileAreaLocations.varrockObst3Area.contains(Players.getLocal())) {
				return 3;
			}

			if (TileAreaLocations.varrockObst5Area.contains(Players.getLocal())) {
				Walking.walk(TileAreaLocations.varrockObst5Tile);
				Sleep.sleep(1500);
				return 5;
			}
			if (TileAreaLocations.varrockObst8Area.contains(Players.getLocal())) {
				return 8;
			}

		}

		if (RuthlessAgilityMain.course.equals("Canifis Rooftop Course")) {
			if (TileAreaLocations.canifisObst2Area.contains(Players.getLocal())) {
				return 2;
			}
		}

		if (RuthlessAgilityMain.course.equals("Falador Rooftop Course")) {
			if (TileAreaLocations.faladorObst9Area.contains(Players.getLocal())) {
				return 9;
			}

			if (TileAreaLocations.faladorObst10Area.contains(Players.getLocal())) {
				return 10;
			}
			if (TileAreaLocations.faladorObst11Area.contains(Players.getLocal())) {
				return 11;
			}
			if (TileAreaLocations.faladorObst12Area.contains(Players.getLocal())) {
				return 12;
			}

		}

		if (RuthlessAgilityMain.course.equals("Pollnivneach Rooftop Course")) {
			if (TileAreaLocations.pollnivneachObst1Area.contains(Players.getLocal())) {
				return 1;
			}
			if (TileAreaLocations.pollnivneachObst2Area.contains(Players.getLocal())) {
				return 2;
			}
			if (TileAreaLocations.pollnivneachObst8Area.contains(Players.getLocal())) {
				return 8;
			}

		}

		if (!RuthlessAgilityMain.course.equals("Gnome Stronghold Agility Course")) {
			for (int i = 0; i < listOfObstacles.length; i++) {
				if (listOfObstacles[i] != null && listOfObstacles[i].canReach()) {
					return i;
				}
			}
		}
		return -1;
	}

}

package agilitycontrollers;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.Players;

import agilitymodel.AgilityCourse;
import agilitymodel.TileAreaLocations;

public class StateManager {

	public enum State {
		RUN_TO_COURSE_START, RUNNING_COURSE, GETTING_FOOD, END_SCRIPT

	}

	public State getState(AgilityCourse agilityCourse, String food) {

		String course = agilityCourse.getCourseName();

		if (agilityCourse.getCourseStartArea().contains(Players.getLocal())) {

			return State.RUNNING_COURSE;
		}

		if (course.equals("Gnome Stronghold Agility Course")
				&& TileAreaLocations.gnomewholeArea.contains(Players.getLocal())) {
			return State.RUNNING_COURSE;
		}

		if (!agilityCourse.getCourseStartArea().contains(Players.getLocal())
				&& agilityCourse.getInsideStartAreaTile().canReach()) {
			if (food != null && !Inventory.contains(food)) {
				return State.GETTING_FOOD;
			} else {
				return State.RUN_TO_COURSE_START;
			}

		}

		if (!agilityCourse.getCourseStartArea().contains(Players.getLocal())
				&& !agilityCourse.getInsideStartAreaTile().canReach() && Players.getLocal().getZ() == 0) {
			return State.RUN_TO_COURSE_START;
		}

		if (!agilityCourse.getCourseStartArea().contains(Players.getLocal())
				&& !agilityCourse.getInsideStartAreaTile().canReach()) {
			return State.RUNNING_COURSE;
		}

		if (!agilityCourse.getCourseStartArea().contains(Players.getLocal())
				&& agilityCourse.getInsideStartAreaTile().distance() > 300) {
			return State.RUN_TO_COURSE_START;
		}
		// return this if no conditions are met/ something is wrong
		return State.END_SCRIPT;
	}

}

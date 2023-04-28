package agilitycontrollers;

import java.awt.Graphics;

import javax.swing.SwingUtilities;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.script.ScriptManifest;
import gui.AgilityGUI;
import agilitymodel.AgilityCourse;

import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.SkillTracker;

@ScriptManifest(author = "Ruthless", category = Category.AGILITY, name = "RuthlessAgility", version = 0.2)
public class RuthlessAgilityMain extends AbstractScript {

	Area startArea;

	Tile startTile;

	public static String food, course, state;

	public static boolean startLoop, takeBreaks, eatFood, collectMarks, scriptIsTimed;

	public static int time;
	public static long startTime, startXp, currentXp;
	long currentTime;
	double sleepMultiplier = 1.0;
	StateManager stateManager = new StateManager();

	@Override
	public void onStart() {
		SkillTracker.start(Skill.AGILITY);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				AgilityGUI gui = new AgilityGUI();
				gui.setVisible(true);
			}
		});
	}

	@Override
	public int onLoop() {
		if (startLoop) {
			AgilityCourse agilityCourse = new AgilityCourse(course);
			if (takeBreaks == true) {
				// takes a break from 15seconds to 240seconds(4mins) on average every 30 loops
				Utility.randomBreak(15, 240, 30);
			}
			if (eatFood == true) {
				Utility.checkToEat(food);
			}

			if (Walking.getRunEnergy() > 40 && !Walking.isRunEnabled()) {
				Walking.toggleRun();
			}
			if (Walking.isRunEnabled()) {
				sleepMultiplier = 1.0;
			} else {
				sleepMultiplier = 2.0;
			}

			switch (stateManager.getState(agilityCourse, food)) {

			case RUN_TO_COURSE_START:
				state = "Running to course start.";
				agilityCourse.runToCourseStart();
				break;
			case RUNNING_COURSE:
				state = "Running course.";
				currentTime = System.nanoTime();
				if (scriptIsTimed) {
					if ((currentTime - startTime) / 1000000000 >= time * 60) {
						log("Since script is timed, ended script");
						ScriptManager.getScriptManager().stop();
					}
				}
				if (collectMarks) {
					Utility.pickUpMarkIfPossible(sleepMultiplier);
				}
				agilityCourse.runCourse(sleepMultiplier);
				break;
			case GETTING_FOOD:
				state = "Getting food.";
				Utility.getFood(food, agilityCourse.getInsdeBankAreaTile(), agilityCourse.getInsideBankArea(),
						agilityCourse.getBnkLoc());
				break;
			case END_SCRIPT:
				state = "Ending script.";
				ScriptManager.getScriptManager().stop();
				break;
			}
		}
		return 1000;
	}

	@Override
	public void onPaint(Graphics g) {
		g.drawString("Xp gained: " + String.valueOf(SkillTracker.getGainedExperience(Skill.AGILITY)), 5, 30);
		g.drawString("Levels increased: " + SkillTracker.getGainedLevels(Skill.AGILITY), 5, 45);
		g.drawString("Currently: " + state, 5, 60);
	}
}

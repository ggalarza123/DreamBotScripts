package agilityscripts;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.items.GroundItem;

import org.dreambot.api.methods.item.GroundItems;


import javax.swing.SwingUtilities;

import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.methods.walking.impl.Walking;

@ScriptManifest(author = "Ruthless", category = Category.AGILITY, name = "SearsAgilityWGUI", version = 3.0)
public class SeersAgilityRevampingCodeAddingWalk extends AbstractScript {
	State state;
	// starting area for Seers Course
	Area seersStartArea = new Area(2732, 3489, 2726, 3483);
	// tile inside seersStartArea
	Tile startTile = new Tile(2729, 3487);
	Tile seersBank = new Tile(2727, 3493);
	Area insideSeersBank = new Area(2721, 3495, 2730, 3489);
	int randomBreakLap;
	int randomObstacle;
	int randomBreakTime;
	// percentage of heath to eat at
	static int healthPercent = 50;
	// four booleans added for start button settings
	// start loop done
	static boolean startLoop;
	// take breaks done
	static boolean takeBreaks;
	// collect marks done
	static boolean collectMarks;
	// timer is done
	static boolean scriptIsTimed;
	// get food done
	static boolean eatFood;
	static String food;
	static int time;
	static long startTime;
	long currentTime;
	double sleepMultiplier = 1.0;

	public SeersAgilityRevampingCodeAddingWalk() {
		this.startLoop = false;
	}

	@Override
	public int onLoop() {
		
		if (startLoop) {
			// *************This code will execute every time before getting a
			// state***********
			// pass in (int minSecondsToRest, int maxSecondsTORest, int numOfObstacles)

			if (takeBreaks == true) {
				createRandomLapBreaks(15, 120, 6);
				
			}
			if (eatFood == true) {
				checkToEat();
			}
			// doubles the amount of sleep time while player is walking
			checkRunningAdjustSleep();
			switch (getState()) {
			
			case RUN_TO_COURSE_START:
				sleep(1000);
				Walking.walk(startTile);
				break;
			case OBSTACLE1:
				currentTime = System.nanoTime();
				log("time elapsed in seconds is: " + (currentTime - startTime) / 1000000000);
				if (scriptIsTimed) {
					if ((currentTime - startTime) / 1000000000 >= time * 60) {
						log("Since script is timed, ended script");
						ScriptManager.getScriptManager().stop();
					}
				}

				// close to tile (2729, 3489)
				GameObject wallObstacle1 = GameObjects.closest(14927);
				if (wallObstacle1 != null && wallObstacle1.canReach()) {
					log("Running course 1");
					log("randomObstacle: " + randomObstacle + " randomBreakLap: " + randomBreakLap);
					if (randomObstacle == 1 && randomBreakLap == 1) {
						log("Taking a " + randomBreakTime / 1000 + " second break.");
						sleep(randomBreakTime);
					}
					
					wallObstacle1.interact();
					sleep((int) (5300 * sleepMultiplier));
				}
				break;

			case OBSTACLE2:
				// close to tile (2721, 3493)
				GameObject obstacle2 = GameObjects.closest(14928);
				if (collectMarks == true) {
					pickUpMarkIfPossible();
				}
				if (obstacle2 != null && obstacle2.canReach()) {
					log("Running course 2");
					if (randomObstacle == 2 && randomBreakLap == 1) {
						log("Taking a " + randomBreakTime / 1000 + " second break.");
						sleep(randomBreakTime);
					}
					obstacle2.interact();
					sleep((int) (randomNum(8000, 8400) * sleepMultiplier));
				}
				break;
			case OBSTACLE3:
				// close to tile tightrope (2710,3490)
				GameObject obstacle3 = GameObjects.closest(14932);
				if (collectMarks == true) {
					pickUpMarkIfPossible();
				}
				if (obstacle3 != null && obstacle3.canReach()) {
					log("Running course 3");
					if (randomObstacle == 3 && randomBreakLap == 1) {
						log("Taking a " + randomBreakTime / 1000 + " second break.");
						sleep(randomBreakTime);
					}
					obstacle3.interact();
					sleep((int) (randomNum(7500, 8090) * sleepMultiplier));
				}
				break;

			case OBSTACLE4:
				// close to (2712, 3477)
				GameObject obstacle4 = GameObjects.closest(14929);
				if (collectMarks == true) {
					pickUpMarkIfPossible();
				}
				if (obstacle4 != null && obstacle4.canReach()) {
					log("Running course 4");
					if (randomObstacle == 4 && randomBreakLap == 1) {
						log("Taking a " + randomBreakTime / 1000 + " second break.");
						sleep(randomBreakTime);
					}
					obstacle4.interact();
					sleep((int) (randomNum(4500, 4550) * sleepMultiplier));
				}
				break;
			case OBSTACLE5:
				// close to tile (2702, 3470)
				GameObject obstacle5 = GameObjects.closest(14930);
				if (collectMarks == true) {
					pickUpMarkIfPossible();
				}
				if (obstacle5 != null && obstacle5.canReach()) {
					log("Running course 5");
					if (randomObstacle == 5 && randomBreakLap == 1) {
						log("Taking a " + randomBreakTime / 1000 + " second break.");
						sleep(randomBreakTime);
					}
					obstacle5.interact();
					sleep((int) (randomNum(5500, 5600) * sleepMultiplier));
				}
				break;
			case OBSTACLE6:
				// right ont to this tile (2702, 3465)
				GameObject obstacle6 = GameObjects.closest(14931);
				if (collectMarks == true) {
					pickUpMarkIfPossible();
				}
				if (obstacle6 != null && obstacle6.canReach()) {
					log("Running course 6");
					if (randomObstacle == 6 && randomBreakLap == 1) {
						log("Taking a " + randomBreakTime / 1000 + " second break.");
						sleep(randomBreakTime);
					}
					obstacle6.interact();
					sleep((int) (randomNum(3200, 3300) * sleepMultiplier));
				}
				break;
			case GETTING_FOOD:
				if (food != null) {
					Walking.walk(seersBank);
					sleep(1000);
					if (insideSeersBank.contains(Players.getLocal())) {
						Bank.open(BankLocation.SEERS);
						sleep(1000);
						if (!Bank.contains(food)) {
							log("unable to withdraw food required");
							sleep(1000);
							ScriptManager.getScriptManager().stop();
						}
						Bank.withdraw(food, 5);
						sleep(2000);
						Bank.close();
						sleep(1000);
					}

				} else {
					log("Getting food but something went wrong");
					ScriptManager.getScriptManager().stop();
				}

				break;

			}
		}
		return 1000;

	}

	// takes the min amount of seconds to rest, the max, and the number of obstacles
	private void createRandomLapBreaks(int secondsMin, int secondsMax, int numOfObstacles) {
		// random sleep/rest time will be between 15seconds and 2minutes
		randomBreakTime = randomNum(secondsMin * 1000, secondsMax * 1000);
		// this will be the random lap where the player will stop, laps 1-6
		randomObstacle = randomNum(1, numOfObstacles);
		// this will be an on average break once every 25 laps
		randomBreakLap = randomNum(1, 25);
	}

	public void checkToEat() {
		if (food != null && Combat.getHealthPercent() <= healthPercent && Inventory.contains(food)) {
			log("passed by checkToEat(), healthPercent: " + Combat.getHealthPercent());
			Inventory.get(food).interact();
			sleep(1000);
		}
	}

	private void pickUpMarkIfPossible() {
		GroundItem mark = GroundItems.closest("Mark of grace");
		if (mark != null && mark.canReach()) {
			mark.interact();
			sleep((int) (2800 * sleepMultiplier));
		}

	}

	@Override
	public void onStart() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				AgilityGUIMain2 gui = new AgilityGUIMain2();
				gui.setVisible(true);
				
			}
		});
	}

	private void checkRunningAdjustSleep() {
		// threshold is set to 46 by dreambot, can be changed but will be leave as is.
		// When running is enabled sleep multiplier will be 1, when player is not
		// running, set the sleep time between obstacles to double to allow for more
		// time to get to next obstacle
		if (Walking.isRunEnabled()) {
			setSleepMultiplier(1);
		} else {
			setSleepMultiplier(2);
		}
	}

	private void setSleepMultiplier(double sleepMultiplier) {
		if (sleepMultiplier == 1.0) {
			this.sleepMultiplier = sleepMultiplier;
		} else
			this.sleepMultiplier = sleepMultiplier * 1.5;
	}

	private enum State {
		RUN_TO_COURSE_START, OBSTACLE1, OBSTACLE2, OBSTACLE3, OBSTACLE4, OBSTACLE5, OBSTACLE6, GETTING_FOOD

	}

	private int checkWhichObstacleToRun() {
		GameObject wallObstacle1 = GameObjects.closest(14927);
		GameObject obstacle2 = GameObjects.closest(14928);
		GameObject obstacle3 = GameObjects.closest(14932);
		GameObject obstacle4 = GameObjects.closest(14929);
		GameObject obstacle5 = GameObjects.closest(14930);
		GameObject obstacle6 = GameObjects.closest(14931);

		if (wallObstacle1 != null && wallObstacle1.canReach()) {
			return 1;
		}
		if (obstacle2 != null && obstacle2.canReach()) {
			return 2;
		}
		if (obstacle3 != null && obstacle3.canReach()) {
			return 3;
		}
		if (obstacle4 != null && obstacle4.canReach()) {
			return 4;
		}
		if (obstacle5 != null && obstacle5.canReach()) {
			return 5;
		}
		if (obstacle6 != null && obstacle6.canReach()) {
			return 6;
		}

		return 0;
	}

	private State getState() {
		// if the player is in the starting area, then we are on the ground and we can
		// start the course at obstacle1 the wall
		if (seersStartArea.contains(Players.getLocal())) {
			return state = State.OBSTACLE1;
		}
		// if the player is not in the starting area, but we can reach(no obstructions)
		// the starting tile, then we are on the ground and thus run to the starting
		// area
		if (!seersStartArea.contains(Players.getLocal()) && startTile.canReach()) {
			if (food != null && !Inventory.contains(food)) {
				log("inventory must be empty, getting food");
				return state = State.GETTING_FOOD;
			} else {
				log("Inentory must contain food and food is not null, running to course start");
				return state = State.RUN_TO_COURSE_START;
			}

		}
		// if we are not in the starting area, and we cannot reach the starting tile,
		// then we must be on a roof
		if (!seersStartArea.contains(Players.getLocal()) && !startTile.canReach()) {
			int obstacle = checkWhichObstacleToRun();
			if (obstacle == 1) {
				return state = State.OBSTACLE1;
			}
			if (obstacle == 2) {
				return state = State.OBSTACLE2;
			}
			if (obstacle == 3) {
				return state = State.OBSTACLE3;
			}
			if (obstacle == 4) {
				return state = State.OBSTACLE4;
			}
			if (obstacle == 5) {
				return state = State.OBSTACLE5;
			}
			if (obstacle == 6) {
				return state = State.OBSTACLE6;
			}
		}
		// temp measure need to verify THIS CODE_______________-------------------
		if (!seersStartArea.contains(Players.getLocal()) && startTile.distance() > 300) {
			log("Long run distance called");
			return state = State.RUN_TO_COURSE_START;
		}

		return state;
	}

	public int randomNum(int i, int k) {
		int num = (int) (Math.random() * (k - i + 1)) + i;
		return num;
	}
}

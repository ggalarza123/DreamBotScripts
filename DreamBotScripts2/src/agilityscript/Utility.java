package agilityscript;

import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.GroundItem;

public class Utility {

	static int healthPercent = 50;
	static int randomBreakLap;
	static int randomObstacle;
	static int randomBreakTime;

	public static void getFood(String food, Tile insideBankTile, Area bankArea, BankLocation bankLoc) {
		if (food != null) {
			Walking.walk(insideBankTile);
			Sleep.sleep(1000);
			if (bankArea.contains(Players.getLocal())) {
//				Bank.open(bankLoc); Removing and testing generic Bank.open
				Bank.open();
				Sleep.sleep(1000);
				if (!Bank.contains(food)) {
					Logger.log("unable to withdraw food required");
					Sleep.sleep(1000);
					ScriptManager.getScriptManager().stop();
				}
				Bank.withdraw(food, 5);
				Sleep.sleep(2000);
				Bank.close();
				Sleep.sleep(1000);
			}

		} else {
			Logger.log("Getting food but something went wrong");
			ScriptManager.getScriptManager().stop();
		}
	}

	// takes the min amount of seconds to rest, the max, and the number of obstacles
	private static void createRandomLapBreaks(int secondsMin, int secondsMax, int avgLoops) {
		// random sleep/rest time will be between 1*A minutes and 1*B minutes
		randomBreakTime = randomNum(secondsMin * 1000, secondsMax * 1000);
		// this will be an on average break once every avgLoops
		randomBreakLap = randomNum(1, avgLoops);
	}

	public static void randomBreak(int secondsMin, int secondsMax, int avgLoops) {
		createRandomLapBreaks(secondsMin, secondsMax, avgLoops);
		if (randomBreakLap == 1) {
			Logger.log("Taking a break");
			Sleep.sleep(randomBreakTime);
		}
	}

	public static void checkToEat(String food) {
		if (food != null && Combat.getHealthPercent() <= healthPercent && Inventory.contains(food)) {
			Logger.log("passed by checkToEat(), healthPercent: " + Combat.getHealthPercent());
			Inventory.get(food).interact();
			Sleep.sleep(1000);
		}
	}

	public static int randomNum(int i, int k) {
		int num = (int) (Math.random() * (k - i + 1)) + i;
		return num;
	}

	public static void pickUpMarkIfPossible(double sleepMultiplier) {
		GroundItem mark = GroundItems.closest("Mark of grace");
		if (mark != null && mark.canReach()) {
			Logger.log("State: Running course, collecting mark.");
			mark.interact();
			Sleep.sleep((int) (2800 * sleepMultiplier));
		}

	}

}

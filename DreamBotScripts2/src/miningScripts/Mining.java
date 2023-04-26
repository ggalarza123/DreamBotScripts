package miningScripts;

import java.util.Iterator;
import java.util.List;

import javax.swing.SwingUtilities;

import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.Animations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.world.World;
import org.dreambot.api.methods.world.Worlds;
import org.dreambot.api.methods.worldhopper.WorldHopper;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;

import agilityscripts.AgilityGUIMain2;

@ScriptManifest(author = "Ruthless", category = Category.MINING, name = "RuthlessMiner", version = 2.5)
public class Mining extends AbstractScript {

	State state;

	Area alKharidMine = new Area(3305, 3272, 3292, 3318);
	Area lumbridgeMine = new Area(3221, 3152, 3230, 3143);
	Area alKharidPowerMine = new Area(3293, 3312, 3298, 3308);
	Area piscatorisMine;
	Area[] miningAreas = { lumbridgeMine, alKharidMine, alKharidPowerMine };
	// Organized from best to worst axe
	String[] axes = { "Crystal pickaxe", "Infernal pickaxe", "Dragon pickaxe", "Rune pickaxe", "Adamant pickaxe",
			"Mithril pickaxe", "Black pickaxe", "Steel pickaxe", "Iron pickaxe", "Bronze pickaxe", };
	String[] ores = { "Copper", "Tin", "Iron", "Silver ore", "Coal", "Gold Ore", "Mithril ore", "Adamantite" };

	String[] rocks = { "Copper rocks", "Tin rocks", "Iron rocks", "Silver rocks", "Coal rocks", "Gold rocks",
			"Mithril rocks", "Adamantite rocks" };

	BankLocation[] banks = { BankLocation.LUMBRIDGE, BankLocation.AL_KHARID, BankLocation.AL_KHARID };

	static boolean banking = false;
	static boolean pickaxeEquiped = false;
	static boolean pickaxeInInventory = false;
	static boolean keepGems = false;
	static boolean antiBot = true;
	static int axeIndex = -1;
	static int locationIndex = 0;
	static int rocksIndex = 0;
	static boolean scriptIsTimed;
	static int time;
	static boolean hopWorlds = true;
	static boolean membersOnly = true;
	int localOtherPlayers = 0;
	int numRestStateIn;
	int randomBreakTime;
	static boolean startLoop;
	long currentTime;
	static long startTime;

	@Override
	public int onLoop() {

		if (startLoop) {
			switch (getState(locationIndex)) {

			case EXITING:
				log("Exiting script");
				ScriptManager.getScriptManager().stop();
				break;
			case DROP_ORES:
				log("dropping ores");
				Inventory.open();
				if (keepGems == true) {
					Inventory.dropAllExcept(axes[axeIndex], "Uncut diamond", "Uncut ruby", "Uncut emerald",
							"Uncut sapphire");
				} else {
					Inventory.dropAllExcept(axes[axeIndex]);
				}

				sleep(randomNum(200, 600));
				break;
			case BANKING:
				log("banking");
				if (banks[locationIndex].getCenter().canReach()) {
					Bank.open(banks[locationIndex]);
					sleep(randomNum(1200, 1400));
					Bank.depositAllExcept(axes[axeIndex]);
					sleep(randomNum(500, 1200));
					Bank.close();
					sleep(randomNum(500, 700));
				}
				break;
			case RESTING:
				log("resting for: " + randomBreakTime);
				sleep(randomBreakTime);
				break;
			case HEADING_TO_BANK:
				log("heading to bank");
				Walking.walk(banks[locationIndex].getCenter());
				break;
			case HEADING_TO_MINE:
				log("heading to mine");
				Walking.walk(miningAreas[locationIndex].getCenter());
				sleep(2000);
				break;
			case MINING:
				if (!Players.getLocal().isAnimating()) {
					if (locationIndex == 2) {
						if (GameObjects.closest(rocks[rocksIndex]) != null
								&& GameObjects.closest(rocks[rocksIndex]).distance() < 5) {
							GameObjects.closest(rocks[rocksIndex]).interact();
						}
					} else
						GameObjects.closest(rocks[rocksIndex]).interact();
				}
				break;
			case HOPPING_WORLDS:
				// Gets a list of normalized Worlds Normalized meaning not: pvp, deadmode,
				// tournament, twisted leauge, last man standing, bounty hunter, or high risk.
				List<World> worlds = Worlds.getNormalizedWorlds();
				log(worlds.size());
				Iterator<World> iterator = worlds.iterator();
				while (iterator.hasNext()) {
					World world = iterator.next();
					// from the list above, only keeping worlds that have no min lvl, and are P2P,
					// removing f2p, if no min lvl, getMin == 0.
					if ((membersOnly == true && world.isF2P()) || (world.getMinimumLevel() > 0)) {
						iterator.remove();
					}

				}
				WorldHopper.hopWorld(Worlds.getRandomWorld(worlds));
				// sleep since after calling hopWorld, code moves on to next line after new
				// world has been clicked on but loading new world takes a few seconds
				sleep(5000);
				break;
			}
		}
		// check loop in 1.4 to 1.7 seconds
		return randomNum(1400, 1700);
	}

	private enum State {
		MINING, DROP_ORES, BANKING, RESTING, HEADING_TO_BANK, HEADING_TO_MINE, EXITING, HOPPING_WORLDS

	}

	// Checks if a certain condition is met, then return that state.
	private State getState(int location) {
		axeIndex = checkForPickaxe();
		currentTime = System.nanoTime();
		if (scriptIsTimed) {
			if ((currentTime - startTime) / 1000000000 >= time * 60) {
				return state = State.EXITING;
			}
		}
		if (antiBot == true) {
			createRandomBreaks(15, 185, 250);
		}

		if (numRestStateIn == 1) {
			return state = State.RESTING;
		}
		if (pickaxeInInventory == false && pickaxeEquiped == false) {
			log("did not contain axe and/or not eqiuped");
			return state = State.EXITING;
		}
		// check how many players are in the area

		if (miningAreas[location].contains(Players.getLocal()) && !Inventory.isFull()) {

			List<Player> playersInArea = Players.all();
			// amountInArea returns 1 if the only player near this area is myself
			int amountInArea = playersInArea.size();
			if (amountInArea == 1) {
				localOtherPlayers = 0;
			}
			if (location == 2 && amountInArea > 1) {
				localOtherPlayers++;
				// small time delay before making player switch worlds
				if (localOtherPlayers > 10) {
					localOtherPlayers = 0;
					return state = State.HOPPING_WORLDS;
				}
			}
			return state = State.MINING;
		}

		if (Inventory.isFull() && banking == true && banks[location].canReach()
				&& banks[location].getArea(5).contains(Players.getLocal())) {
			return state = State.BANKING;
		}
		if (Inventory.isFull() && banking == true) {
			return state = State.HEADING_TO_BANK;
		}
		if (Inventory.isFull() && banking == false) {
			return state = State.DROP_ORES;
		}
		if (!Inventory.isFull() && !miningAreas[location].contains(Players.getLocal())) {
			return state = State.HEADING_TO_MINE;
		} else
			return state = State.RESTING;
	}

	// When script start load this.
	@Override
	public void onStart() {
		super.onStart();
		axeIndex = checkForPickaxe();
		if (axeIndex == -1) {
			log("Ending script, axe not found in inv or equiped.");
			ScriptManager.getScriptManager().stop();
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MiningGUI gui = new MiningGUI();
				gui.setVisible(true);
			}

		});
		log("Bot Started");
	}

	// returns the index from list of axes if found, else -1
	public int checkForPickaxe() {
		// can be revered to equip best axe as well but leaving like this for now
		for (int i = 0; i < axes.length; i++) {
			if (Inventory.contains(axes[i])) {
				pickaxeInInventory = true;
				return i;
			}
		}
		// reaching here means pickaxe not in inventory, thus check if its equipped
		int index = checkAxeEquiped();
		// if it is not equiped -1 will be returned
		if (index == -1) {
			pickaxeInInventory = false;
			return -1;
		}
		// else it must have been found thus return the index of which axe was found
		else
			return index;

	}

	// returns the index from list of axes if found, else -1
	public int checkAxeEquiped() {
		for (int i = 0; i < axes.length; i++) {
			if (Equipment.contains(axes[i])) {
				pickaxeEquiped = true;
				return i;
			}
		}
		pickaxeEquiped = false;
		return -1;
	}

	// When script ends do this.
	public void onExit() {
		log("Bot Ended");
	}

	// takes the min amount of seconds to rest, the max, and the number of states
	// that player can take a break in
	private void createRandomBreaks(int secondsMin, int secondsMax, int numOfCycles) {
		// random sleep/rest time will be between 15seconds and 2minutes
		randomBreakTime = randomNum(secondsMin * 1000, secondsMax * 1000);
		// this will be state where the player will stop,
		numRestStateIn = randomNum(1, numOfCycles);
	}

	public int randomNum(int i, int k) {
		int num = (int) (Math.random() * (k - i + 1)) + i;
		return num;
	}
}

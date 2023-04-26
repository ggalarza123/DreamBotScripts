package herbloreScript;

import javax.swing.SwingUtilities;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.container.impl.bank.BankMode;
import org.dreambot.api.methods.grandexchange.GrandExchange;
import org.dreambot.api.methods.grandexchange.LivePrices;
import org.dreambot.api.methods.input.Keyboard;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManager;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.items.Item;

import miningScripts.MiningGUI;

@ScriptManifest(author = "Ruthless", category = Category.HERBLORE, name = "RuthlessHerblore", version = 2.2)
public class Herblore extends AbstractScript {

	private State state;
	String[] grimyHerbs = { "Grimy guam leaf", "Grimy marrentill", "Grimy tarromin", "Grimy harralander",
			"Grimy ranarr weed", "Grimy toadflax", "Grimy irit leaf", "Grimy avantoe", "Grimy kwuarm",
			"Grimy snapdragon", "Grimy cadentine", "Grimy lantadyme", "Grimy dwarf weed", "Grimy torstol",
			"Grimy Cadantine" };
	String[] cleanHerbs = { "Guam leaf", "Marrentill", "Tarromin", "Harralander", "Ranarr weed", "Toadflax",
			"Irit leaf", "Avantoe", "Kwuarm", "Snapdragon", "Cadentine", "Lantadyme", "Dwarf weed", "Torstol",
			"Cadantine" };
	String[] unfPotions = { "Guam potion (unf)", "Marrentill potion (unf)", "Tarromin potion (unf)",
			"Harralander potion (unf)", "Ranarr potion (unf)", "Toadflax potion (unf)", "Irit potion (unf)",
			"Avantoe potion (unf)", "Kwuarm potion (unf)", "Snapdragon potion (unf)", "Cadantine potion (unf)",
			"Lantadyme potion (unf)", "Dwarf weed potion (unf)", "Torstol potion (unf)",
			"Cadantine blood potion (unf)" };
	static boolean banking = false, antiBot = true, hopWorlds = true, membersOnly = true, scriptIsTimed = false,
			startLoop = false, useGe = false;
	static int time;
	int numRestStateIn;
	int randomBreakTime;
	static int inventoryCounter = 0;
	long currentTime;
	static long startTime;
	static String herbloreToDo;
	static String herbToClean;
	static String cleanHerb;
	static String unfPotion;
	static int herbCount;

	@Override // Infinite loop
	public int onLoop() {
		// Determined by which state gets returned by getState() then do that case.
		if (startLoop) {
			switch (getState()) {
			case WITHDRAWING_4M_BANK:
				log("withdrawing from bank");
				if (!Bank.contains(herbToClean) && !Inventory.contains(herbToClean)) {
					log("Bank is open, but selected herb is not available, closing script");
					sleep(1000);
					ScriptManager.getScriptManager().stop();
				}
				if (!Inventory.isEmpty()) {
					Bank.depositAllItems();
					sleep(randomNum(700, 1200));
				}
				if (herbloreToDo.equals("Make unfinished potions")) {
					Bank.withdraw(herbToClean, 14);
					sleep(randomNum(200, 210));
					Bank.withdraw("Vial of water", 14);
					Bank.close();
					inventoryCounter = 0;
					sleep(randomNum(400, 600));
				} else if (herbloreToDo.equals("Clean herbs")) {
					Bank.withdrawAll(herbToClean);
					sleep(randomNum(700, 1200));
					Bank.close();
					inventoryCounter = 0;
					sleep(randomNum(500, 700));
				} else if (herbloreToDo.equals("Make potions")) {
				}

				break;
			case GE_PURCHASE:
				// to start bank is already open so withdraw all coins
				if (!Inventory.isEmpty()) {
					Bank.depositAllItems();
					sleep(randomNum(700, 1200));
				}
				Bank.withdrawAll("Coins");
				sleep(randomNum(700, 1200));
				Bank.setWithdrawMode(BankMode.NOTE);
				sleep(randomNum(700, 1200));
				Bank.withdrawAll(unfPotion);
				sleep(randomNum(700, 1200));
				Bank.setWithdrawMode(BankMode.ITEM);
				sleep(randomNum(700, 1200));
				Bank.close();
				inventoryCounter = 0;
				sleep(randomNum(700, 1200));
				GrandExchange.open();
				if (GrandExchange.isReadyToCollect()) {
					GrandExchange.collect();
				}
				sleep(randomNum(700, 1200));
				// first put the unf potions for sale
				int currPrice = LivePrices.get(unfPotion);
				int offerPrice = (int) (currPrice * 1.4);
				if (GrandExchange.buyItem(unfPotion, 1, offerPrice)) {

					sleep(5000);
					log("current price 1: " + GrandExchange.getCurrentPrice());
					for (int i = 0; i < 8; i++) {
						if (GrandExchange.isReadyToCollect(i)) {
							GrandExchange.openSlotInterface(i);
							sleep(randomNum(700, 1200));
							log("current price 2: " + GrandExchange.getCurrentPrice());

						}
					}
					ScriptManager.getScriptManager().stop();

				}

				break;
			case LOGOUT:
				log("Exiting script");
				ScriptManager.getScriptManager().stop();
				break;
			case OPENING_BANK:
				log("opening bank");
				if (Inventory.isItemSelected()) {
					Inventory.deselect();
				}
				Bank.open(BankLocation.GRAND_EXCHANGE);
				sleep(1000);
				break;
			case RESTING:
				log("resting for: " + randomBreakTime / 1000 + "seconds");
				// Before resting, 2/3 chance of checking herblore skill // antibot
				if (randomNum(1, 3) != 1) {
					Skills.open();
					sleep(1000);
					Skills.hoverSkill(Skill.HERBLORE);
				}
				sleep(randomBreakTime);
				Inventory.open();
				break;
			case CLEANING_HERB:
				if (Inventory.getItemInSlot(inventoryCounter) != null
						&& Inventory.slotContains(inventoryCounter, herbToClean)) {
					Inventory.getItemInSlot(inventoryCounter).interact();
				}
				inventoryCounter++;
				if (herbloreToDo.equals("Make unfinished potions") && inventoryCounter == 15) {
					inventoryCounter = 0;
				}

				if (inventoryCounter == 28) {
					inventoryCounter = 0;
				}
				if (inventoryCounter == 14 && herbloreToDo.equals("Make unfinished potions")) {
					inventoryCounter = 0;
				}

				break;
			case MAKE_UNF_POTION:
				if (Inventory.isItemSelected()) {
					Inventory.deselect();
				}
				if (Inventory.getItemInSlot(inventoryCounter) != null
						&& Inventory.slotContains(inventoryCounter, cleanHerb)) {
					Inventory.getItemInSlot(inventoryCounter).interact();
					sleep(250);
					if (Inventory.getItemInSlot(inventoryCounter + 14) != null) {
						Inventory.getItemInSlot(inventoryCounter + 14).interact();
						sleep(randomNum(700, 750));
						Keyboard.type(" ");
						sleep(randomNum(7900, 8150));
					}
					// threw an erros in the past because itemslot was null, if it is null just
					// sleep, it will go around and see we don't have water and bank
					if (Inventory.getItemInSlot(inventoryCounter + 14) == null) {
						sleep(randomNum(400, 450));
					}

				} else if (!Inventory.slotContains(inventoryCounter, cleanHerb)) {
					inventoryCounter++;
					if (inventoryCounter == 14) {
						inventoryCounter = 0;
					}
				}

				break;

			}
		}
		return randomNum(120, 201);

	}

// State names
	private enum State {
		LOGOUT, RESTING, CLEANING_HERB, OPENING_BANK, GE_PURCHASE, WITHDRAWING_4M_BANK, MAKE_UNF_POTION,

	}

// Checks if a certain condition is met, then return that state.
	private State getState() {

		Item herb = Inventory.get(herbToClean);

		if (antiBot == true) {
			// On average, for every 1300 loops, rest between 15seconds - 267seconds
			createRandomBreaks(15, 267, 1300);
			if (numRestStateIn == 1) {
				return state = State.RESTING;
			}
		}

		if (Bank.isOpen()) {
			if (useGe) {
				if (Bank.count(herbToClean) < 500) {
					return state = State.GE_PURCHASE;
				}
			}
			if (herbloreToDo.equals("Clean herbs")) {
				if (Bank.contains(herbToClean) || Inventory.contains(herbToClean)) {
					return state = State.WITHDRAWING_4M_BANK;
				} else {
					return state = State.LOGOUT;
				}
			}
			if (herbloreToDo.equals("Make unfinished potions")) {
				if (Bank.contains(herbToClean) || Inventory.contains(herbToClean)) {
					if (Bank.contains("Vial of water") || Inventory.contains("Vial of water")) {
						return state = State.WITHDRAWING_4M_BANK;
					} else {
						return state = State.LOGOUT;
					}

				} else {
					return state = State.LOGOUT;
				}
			}
			if (herbloreToDo.equals("Make potions")) {

			}

		}
		if (Inventory.contains(herbToClean)) {
			if (herb != null && herb.isNoted()) {
				return state = State.OPENING_BANK;
			}
			if (herb != null && !herb.isNoted()) {
				return state = State.CLEANING_HERB;
			}
		}
		if (Inventory.contains(cleanHerb) && !Inventory.contains("Vial of water")
				&& herbloreToDo.equals("Make unfinished potions")) {
			return state = State.OPENING_BANK;
		}
		if (Inventory.contains(cleanHerb) && Inventory.contains("Vial of water")
				&& herbloreToDo.equals("Make unfinished potions")) {
			return state = State.MAKE_UNF_POTION;
		}
		if (!Inventory.contains(herbToClean) && !Bank.isOpen()) {
			return state = State.OPENING_BANK;
		}
		return state;
	}

// When script start load this.
	public void onStart() {
		super.onStart();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				HerbloreGUI gui = new HerbloreGUI();
				gui.setVisible(true);

			}

		});
		log("Bot Started");
	}

// When script ends do this.
	public void onExit() {
		log("Bot Ended");
	}

	public int randomNum(int i, int k) {
		int num = (int) (Math.random() * (k - i + 1)) + i;
		return num;
	}

	// takes the min amount of seconds to rest, the max, and the number of states
	// that player can take a break in
	private void createRandomBreaks(int secondsMin, int secondsMax, int numOfCycles) {
		// random sleep/rest time will be between 15seconds and 2minutes
		randomBreakTime = randomNum(secondsMin * 1000, secondsMax * 1000);
		// this will be state where the player will stop,
		numRestStateIn = randomNum(1, numOfCycles);
	}
}

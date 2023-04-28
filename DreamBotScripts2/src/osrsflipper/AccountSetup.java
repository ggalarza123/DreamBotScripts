package osrsflipper;

import org.dreambot.api.Client;
import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.grandexchange.GrandExchange;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;

import java.util.ArrayList;
import java.util.List;

public class AccountSetup {
	private static boolean bankIsChecked = false;

	// Does the essential things before trading can start (e.g walk to GE && get
	// cash)
	public static void SetupTrading() {
		if (!BotConfig.GRANDEXCHANGE_AREA.contains(Players.getLocal())) {
			TravelToGE();
		}

		if (GrandExchange.isOpen() && !bankIsChecked) {
			GrandExchange.close();
			Sleep.sleepUntil(() -> !GrandExchange.isOpen(), BotConfig.MAX_ACTION_TIMEOUT_MS);
		}

		if (!IsReadyToTrade() && Client.getGameState() == GameState.LOGGED_IN) {
			PrepareForTrading();
		}
	}

	public static boolean IsReadyToTrade() {
		// getFirstOpenSlot is only -1 if there are no slots available
		return bankIsChecked && GrandExchange.isOpen();
	}

	private static void PrepareForTrading() {
		try {
			if (!bankIsChecked) {
				if (NPCs.closest("Banker") != null) {
					NPCs.closest("Banker").interact("Bank");
				} else if (Bank.getClosestBankLocation() != null) {
					Bank.open();
				} else {
//					logError("No bank was found!");
				}

				Sleep.sleepUntil(Bank::isOpen, BotConfig.MAX_ACTION_TIMEOUT_MS);
				Sleep.sleep(500);

				// Items that shouldn't be deposited (that are actively flipped)
				List<String> flipItems = new ArrayList<>();
				flipItems.add("Coins");
				Flipper.activeFlips.forEach(flip -> flipItems.add(flip.item.item.itemName));

				///////////////////////////////////////////// NED TO FIX HERE
				///////////////////////////////////////////// ///////////////////////////

				Bank.depositAllExcept(flipItems.toString());

				Sleep.sleepUntil(() -> Inventory.onlyContains(flipItems.toString()) || Inventory.getEmptySlots() == 28,
						BotConfig.MAX_ACTION_TIMEOUT_MS);
				if (Bank.contains("Coins")) {
					Bank.withdrawAll("Coins");
					Sleep.sleep(500);
				}
				Sleep.sleepUntil(() -> Inventory.contains("Coins"), BotConfig.MAX_ACTION_TIMEOUT_MS);
				Bank.close();
				Sleep.sleepUntil(() -> !Bank.isOpen(), BotConfig.MAX_ACTION_TIMEOUT_MS);
				bankIsChecked = true;
				Main.startingCash = Inventory.get("Coins").getAmount();
			}

			if (!GrandExchange.isOpen()) {
				NPC clerk = NPCs.closest("Grand Exchange Clerk");
				if (clerk != null) {
					clerk.interact("Exchange");
					Sleep.sleepUntil(GrandExchange::isOpen, BotConfig.MAX_ACTION_TIMEOUT_MS);
					Sleep.sleep(2000);
				}
			}
		} catch (Exception ignored) {
		}
	}

	private static void TravelToGE() {
		int randomEnergyActivate = Calculations.random(BotConfig.MIN_RUNENERGY_ACTIVATE,
				BotConfig.MAX_RUNENERGY_ACTIVATE);
		while (!BotConfig.GRANDEXCHANGE_AREA.contains(Players.getLocal())) {
			if (!Walking.isRunEnabled() && Walking.getRunEnergy() >= randomEnergyActivate) {
				Walking.toggleRun();
				randomEnergyActivate = Calculations.random(BotConfig.MIN_RUNENERGY_ACTIVATE,
						BotConfig.MAX_RUNENERGY_ACTIVATE);
			}

			Walking.walk(BotConfig.GRANDEXCHANGE_AREA.getCenter());
			Sleep.sleepUntil(Walking::shouldWalk,
					Calculations.random(BotConfig.MIN_WALK_CLICK_DELAY_MS, BotConfig.MAX_WALK_CLICK_DELAY_MS));
		}
	}
}

package castingspells;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;

import org.dreambot.api.script.ScriptManifest;

import org.dreambot.api.wrappers.items.Item;

import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.input.Camera;
import org.dreambot.api.methods.magic.Magic;
import org.dreambot.api.methods.magic.Normal;

import org.dreambot.api.methods.tabs.Tab;

// example of scriptmanifest
@ScriptManifest(author = "Ruthless", category = Category.MAGIC, name = "EnchantEmeraldAmulets", version = 1.8)
public class EnchantingEmeraldAmulets extends AbstractScript {

	State state;
	int sapphireAmulet = 1694, emeraldAmulet = 1696, cosmicRune = 564, staffOfFire = 1387, staffOfAir = 1381,
			airRune = 556, staffOfWater = 1383, amuletOfMagic = 1727;
	int mainAmulet = emeraldAmulet;
	final int staff = staffOfAir;
	Item amulet = new Item(mainAmulet, 1);

	@Override // Infinite loop
	public int onLoop() {
		// Determined by which state gets returned by getState() then do that case.
		log("passing thru checking state");
		switch (getState()) {

		case CASTING_ENCHANT:
			log("Start enchant");
			Tab.MAGIC.open();
			if (Magic.canCast(Normal.LEVEL_2_ENCHANT) && Inventory.contains(mainAmulet)) {
				Magic.castSpellOn(Normal.LEVEL_2_ENCHANT, amulet);
				log("2 second rest");
				// make cast times variable, every 2 seconds
				sleep(randomNum(1200, 1501));
				// zoom out randomly after casting spell 1/20 chance
//				int time = randomNum(1, 100);
//				if (time <= 5) {
//					int zoom = randomNum(680, 2300);
//					Camera.setZoom(zoom);			
//					sleep(3000);			
//					if (zoom > 900) {
//						int pitch = randomNum(160, 240);
//						Camera.keyboardRotateToPitch(pitch);
//						sleep(1000);
//					}
//				}
				log("End one cast enchant");
				break;
			}
			break;
		case WITHDRAWING_FROM_BANK:
			Inventory.open();
			log("Start withdrawal");
			Bank.open(BankLocation.GRAND_EXCHANGE);
			Bank.depositAllExcept(cosmicRune, mainAmulet);
			if (!Bank.contains(mainAmulet)) {
				log("Out of amulets, closing script");

				sleep(1000);
				// returning -1 closes script successfully
				return -1;
			}
			Bank.withdrawAll(mainAmulet);

			Bank.close();
			sleep(2000);
			log("End withdrawal");
			break;
		case MOVING_SCREEN_RANDOMLY:
			break;
		case BREAK:
			break;
		case LOGOUT:
			return -1;

		default:
			break;
		}
		return 0;

	}

	// Checks if a certain condition is met, then return that state.
	private State getState() {
		log("staff check: " + Equipment.contains(staff));
		if (!Equipment.contains(staff)) {
			state = State.EQUIPING_STAFF;
			log("printing state 1" + state);
		}
		log("amulet check: " + Inventory.contains(mainAmulet));
		if (!Inventory.contains(mainAmulet)) {
			state = State.WITHDRAWING_FROM_BANK;
			log("printing state 2" + state);
		}
		log("cosmic rune check: " + Inventory.contains(cosmicRune) + " and saphire amulet check: "
				+ Inventory.contains(mainAmulet) + " ");
		if (Inventory.contains(mainAmulet) && Inventory.contains(cosmicRune)) {
			state = State.CASTING_ENCHANT;
			log("printing state 3" + state);
		}
		log("printing state" + state);
		if (!Inventory.contains(cosmicRune)) {
			log("No Cosmic runes available in inventory, closing script.");
			state = State.LOGOUT;
		}

		return state;
	}

	// State names
	private enum State {
		STOP, LOGOUT, CASTING_ENCHANT, WAITING, BREAK, WITHDRAWING_FROM_BANK, MOVING_SCREEN_RANDOMLY, EQUIPING_STAFF
	}

	// When script start load this.
	public void onStart() {
		log("Bot Started");
		getState();
	}

	// When script ends do this.
	public void onExit() {
		log("Bot Ended");
	}

	public int randomNum(int i, int k) {
		int num = (int) (Math.random() * (k - i + 1)) + i;
		return num;
	}

}

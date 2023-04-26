package castingspells;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;

import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.container.impl.equipment.Equipment;
import org.dreambot.api.methods.magic.Magic;
import org.dreambot.api.methods.magic.Normal;
import org.dreambot.api.methods.tabs.Tab;

// example of scriptmanifest
@ScriptManifest(author = "Ruthless", category = Category.MAGIC, name = "EnchantAmulets", version = 1.6)
public class EnchantingSapphireAmulets extends AbstractScript {

	State state;
	int sapphireAmulet = 1694, emeraldAmulet = 1696, cosmicRune = 564, staffOfFire = 1387, staffOfAir = 1381,
			airRune = 556, staffOfWater = 1383, amuletOfMagic = 1727;
	Item sappAmulet = new Item(sapphireAmulet, 1);

	@Override // Infinite loop
	public int onLoop() {
		// Determined by which state gets returned by getState() then do that case.
		log("passing thru checking state");
		switch (getState()) {

		case CASTING_ENCHANT:
			log("Start enchant");
			Tab.MAGIC.open();
			if (Magic.canCast(Normal.LEVEL_1_ENCHANT) && Inventory.contains(sapphireAmulet)) {
				Magic.castSpellOn(Normal.LEVEL_1_ENCHANT, sappAmulet);
				log("2.5 second rest");
				sleep(randomNum(2000, 3000));
				log("End one cast enchant");
				break;
			}
			break;
		case WITHDRAWING_FROM_BANK:
			log("Start withdrawal");
			Bank.open(BankLocation.GRAND_EXCHANGE);
			Bank.depositAllExcept(cosmicRune, sapphireAmulet);
			if (!Bank.contains(sapphireAmulet)) {
				log("Out of amulets, closing script");
				// Successfully close script.
				sleep(1000);
				return -1;
			}
			Bank.withdrawAll(sapphireAmulet);
			Bank.close();
			sleep(1000);
			log("End withdrawal");
			break;
		case MOVING_SCREEN_RANDOMLY:
			break;
		case BREAK:
			break;
		case LOGOUT:

			break;
		default:
			break;
		}
		return 0;

	}

	// Checks if a certain condition is met, then return that state.
	private State getState() {
		log("staff check: " + Equipment.contains(staffOfWater));
		if (!Equipment.contains(staffOfWater)) {
			state = State.EQUIPING_STAFF;
			log("printing state 1" + state);
		}
		log("amulet check: " + Inventory.contains(sapphireAmulet));
		if (!Inventory.contains(sapphireAmulet)) {
			state = State.WITHDRAWING_FROM_BANK;
			log("printing state 2" + state);
		}
		log("cosmic rune check: " + Inventory.contains(cosmicRune) + " and saphire amulet check: "
				+ Inventory.contains(sapphireAmulet) + " ");
		if (Inventory.contains(sapphireAmulet) && Inventory.contains(cosmicRune)) {
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

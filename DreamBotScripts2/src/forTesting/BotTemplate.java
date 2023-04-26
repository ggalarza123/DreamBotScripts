package forTesting;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.methods.skills.Skill;

// example of scriptmanifest
@ScriptManifest(author = "Ruthless", category = Category.AGILITY, name = "FaladorAgility", version = 1.0)
public class BotTemplate extends AbstractScript {

	State state;

	@Override // Infinite loop
	public int onLoop() {
		// Determined by which state gets returned by getState() then do that case.
		switch (getState()) {

//		case STOP:
//			Log("stop script");
//			stop();
//			break;
//
//		case LOGOUT:
//			Log("logout");
//			getTabs().logout();
//			break;
		}
		return 0;

	}

	private Object getTabs() {
		// TODO Auto-generated method stub
		return null;
	}

	private void Log(String string) {
		// TODO Auto-generated method stub
		
	}


// State names
	private enum State {
		STOP, LOGOUT

	}

// Checks if a certain condition is met, then return that state.
	private State getState() {
		return state;
	}

// When script start load this.
	public void onStart() {
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

}
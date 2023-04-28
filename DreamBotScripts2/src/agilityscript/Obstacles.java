package agilityscript;

import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.wrappers.interactive.GameObject;

public class Obstacles {

	// Seers
	GameObject wallObstacle1 = GameObjects.closest("Wall");
	GameObject obstacle2 = GameObjects.closest("Gap");
	GameObject obstacle3 = GameObjects.closest("Tightrope");
	GameObject obstacle4 = GameObjects.closest("Gap");
	GameObject obstacle5 = GameObjects.closest("Gap");
	GameObject obstacle6 = GameObjects.closest("Edge");
	GameObject[] seersObstacles = { wallObstacle1, obstacle2, obstacle3, obstacle4, obstacle5, obstacle6 };

	// Gnome Stronghold Agility Course
	GameObject logObst1 = GameObjects.closest("Log balance");
	GameObject netObst1 = GameObjects.closest("Obstacle net");
	GameObject treeObst1 = GameObjects.closest("Tree branch");
	GameObject ropeObst1 = GameObjects.closest("Balancing rope");
	GameObject treeObst2 = GameObjects.closest("Tree branch");
	GameObject netObst2 = GameObjects.closest("Obstacle net");
	GameObject pipeObst1 = GameObjects.closest("Obstacle pipe");
	GameObject[] gnomeObstacles = { logObst1, netObst1, treeObst1, ropeObst1, treeObst2, netObst2, pipeObst1 };

	// Draynor Village Rooftop Course
	GameObject roughWall1 = GameObjects.closest("Rough wall");
	GameObject tightRope1 = GameObjects.closest("Tightrope");
	GameObject tightRope2 = GameObjects.closest("Tightrope");
	GameObject narrowWall1 = GameObjects.closest("Narrow wall");
	GameObject wall1 = GameObjects.closest("Wall");
	GameObject gap1 = GameObjects.closest("Gap");
	GameObject crate1 = GameObjects.closest("Crate");
	GameObject[] draynorObstacles = { roughWall1, tightRope1, tightRope2, narrowWall1, wall1, gap1, crate1 };

	// Al Kharid Rooftop Course
	GameObject roughWall01 = GameObjects.closest("Rough wall");
	GameObject tightRope01 = GameObjects.closest("Tightrope");
	GameObject cable01 = GameObjects.closest("Cable");
	GameObject zipLine01 = GameObjects.closest("Zip line");
	GameObject tropicalTree01 = GameObjects.closest("Tropical tree");
	GameObject roofTopBeams01 = GameObjects.closest("Roof top beams");
	GameObject tightRope02 = GameObjects.closest("Tightrope");
	GameObject gap01 = GameObjects.closest("Gap");
	GameObject[] alKharidObstacles = { roughWall01, tightRope01, cable01, zipLine01, tropicalTree01, roofTopBeams01,
			tightRope02, gap01 };

	// Varrock Rooftop Course
	GameObject roughWall001 = GameObjects.closest("Rough wall");
	GameObject clothesLine001 = GameObjects.closest("Clothes line");
	GameObject gap001 = GameObjects.closest("Gap");
	GameObject wall02 = GameObjects.closest("Wall");
	GameObject gap002 = GameObjects.closest("Gap");
	GameObject gap003 = GameObjects.closest(14834);
	GameObject gap004 = GameObjects.closest(14835);
	GameObject ledge001 = GameObjects.closest("Ledge");
	GameObject edge001 = GameObjects.closest(14841);
	GameObject[] varrockObstacles = { roughWall001, clothesLine001, gap001, wall02, gap002, gap003, gap004, ledge001,
			edge001 };

	// Canifis Rooftop Course
	GameObject tallTree0001 = GameObjects.closest("Tall tree");
	GameObject gap0001 = GameObjects.closest(14844);
	GameObject gap0002 = GameObjects.closest(14845);
	GameObject gap0003 = GameObjects.closest(14848);
	GameObject gap0004 = GameObjects.closest(14846);
	GameObject poleVault0001 = GameObjects.closest("Pole-vault");
	GameObject gap0005 = GameObjects.closest(14847);
	GameObject gap0006 = GameObjects.closest(14897);
	GameObject[] canifisObstacles = { tallTree0001, gap0001, gap0002, gap0003, gap0004, poleVault0001, gap0005,
			gap0006 };

	// Falador Rooftop Course
	GameObject roughWall_1 = GameObjects.closest("Rough wall");
	GameObject tightrope_1 = GameObjects.closest("Tightrope");
	GameObject handHolds_1 = GameObjects.closest("Hand holds");
	GameObject gap_1 = GameObjects.closest(14903);
	GameObject gap_2 = GameObjects.closest(14904);
	GameObject tightrope_2 = GameObjects.closest("Tightrope");
	GameObject tightrope_3 = GameObjects.closest("Tightrope");
	GameObject gap_3 = GameObjects.closest("Gap");
	GameObject ledge_1 = GameObjects.closest(14920);
	GameObject ledge_2 = GameObjects.closest(14921);
	GameObject ledge_3 = GameObjects.closest(14922);
	GameObject ledge_4 = GameObjects.closest(14924);
	GameObject edge_1 = GameObjects.closest(14925);
	GameObject[] faladorObstacles = { roughWall_1, tightrope_1, handHolds_1, gap_1, gap_2, tightrope_2, tightrope_3,
			gap_3, ledge_1, ledge_2, ledge_3, ledge_4, edge_1 };

	// Pollnivneach Rooftop Course
	GameObject basket_01 = GameObjects.closest("Basket");
	GameObject marketStall_01 = GameObjects.closest("Market stall");
	GameObject banner_01 = GameObjects.closest("Banner");
	GameObject gap_01 = GameObjects.closest("Gap");
	GameObject tree_01 = GameObjects.closest("Tree");
	GameObject roughWall_01 = GameObjects.closest("Rough wall");
	GameObject monkeybars_01 = GameObjects.closest("Monkeybars");
	GameObject tree_02 = GameObjects.closest("Tree");
	GameObject dryingLine_01 = GameObjects.closest("Drying line");
	GameObject[] pollnivneachObstacles = { basket_01, marketStall_01, banner_01, gap_01, tree_01, roughWall_01,
			monkeybars_01, tree_02, dryingLine_01 };

	// Rellekka Rooftop Course
	GameObject roughWall_001 = GameObjects.closest("Rough wall");
	GameObject gap_001 = GameObjects.closest("Gap");
	GameObject tightRope_001 = GameObjects.closest("Tightrope");
	GameObject gap_002 = GameObjects.closest("Gap");
	GameObject gap_003 = GameObjects.closest("Gap");
	GameObject tightRope_002 = GameObjects.closest("Tightrope");
	GameObject pileOfFish_001 = GameObjects.closest("Pile of fish");
	GameObject[] rellekkaObstacles = { roughWall_001, gap_001, tightRope_001, gap_002, gap_003, tightRope_002,
			pileOfFish_001 };

	public GameObject[] getObstacles(String course) {

		if (course.equals("Seer's Agility Course")) {
			return seersObstacles;
		} else if (course.equals("Gnome Stronghold Agility Course")) {
			return gnomeObstacles;
		} else if (course.equals("Draynor Village Rooftop Course")) {
			return draynorObstacles;
		} else if (course.equals("Al Kharid Rooftop Course")) {
			return alKharidObstacles;
		} else if (course.equals("Varrock Rooftop Course")) {
			return varrockObstacles;
		} else if (course.equals("Canifis Rooftop Course")) {
			return canifisObstacles;
		} else if (course.equals("Falador Rooftop Course")) {
			return faladorObstacles;
		} else if (course.equals("Pollnivneach Rooftop Course")) {
			return pollnivneachObstacles;
		} else if (course.equals("Rellekka Rooftop Course")) {
			return rellekkaObstacles;
		}

		else
			return null;

	}

}

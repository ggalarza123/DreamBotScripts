package agilityscript;

import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.GameObject;

public class AgilityCourse {

	CourseRunner courseRunner;
	Area courseStartArea;

	Area bankArea;

	Tile insideStartAreaTile;

	Tile insideBankAreaTile;

	int numOfObstacles;

	BankLocation bnkLoc;

	GameObject[] listOfObstacles;
	String courseName;

	int[] restTimes;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public BankLocation getBnkLoc() {
		return bnkLoc;
	}

	public void setBnkLoc(BankLocation bnkLoc) {
		this.bnkLoc = bnkLoc;
	}

	public AgilityCourse(Area courseStartArea, Area bankArea, Tile insideStartAreaTile, Tile insdeBankAreaTile,
			int numberOfObstacles) {
		super();
		this.courseStartArea = courseStartArea;
		this.bankArea = bankArea;
		this.insideStartAreaTile = insideStartAreaTile;
		this.insideBankAreaTile = insdeBankAreaTile;
		this.numOfObstacles = numberOfObstacles;

	}

	public AgilityCourse(String course) {
		Obstacles list = new Obstacles();
		listOfObstacles = list.getObstacles(course);
		if (course.equals("Seer's Agility Course")) {
			this.courseStartArea = TileAreaLocations.seersStartArea;
			this.bankArea = TileAreaLocations.seersBankArea;
			this.insideStartAreaTile = TileAreaLocations.seersStartTile;
			this.insideBankAreaTile = TileAreaLocations.seersBankTile;
			this.numOfObstacles = 6;
			this.restTimes = ObstacleRestTimes.seersMinRestTimes;
			this.courseName = course;
			courseRunner = new CourseRunner(this);
		}

		if (course.equals("Gnome Stronghold Agility Course")) {
			this.courseStartArea = TileAreaLocations.gnomeStartArea;
			this.insideStartAreaTile = TileAreaLocations.gnomeStartTile;
			this.numOfObstacles = 7;
			this.restTimes = ObstacleRestTimes.gnomeMinRestTimes;
			this.courseName = course;
			courseRunner = new CourseRunner(this);
		}

		if (course.equals("Draynor Village Rooftop Course")) {
			this.courseStartArea = TileAreaLocations.draynorStartArea;
			this.insideStartAreaTile = TileAreaLocations.draynorStartTile;
			this.numOfObstacles = 7;
			this.insideBankAreaTile = TileAreaLocations.draynorBankTile;
			this.bankArea = TileAreaLocations.draynorBankArea;
			this.restTimes = ObstacleRestTimes.draynorMinRestTimes;
			this.courseName = course;
			courseRunner = new CourseRunner(this);
		}

		if (course.equals("Al Kharid Rooftop Course")) {
			this.courseStartArea = TileAreaLocations.alKharidStartArea;
			this.insideStartAreaTile = TileAreaLocations.alKharidStartTile;
			this.numOfObstacles = 8;
			this.insideBankAreaTile = TileAreaLocations.alKharidBankTile;
			this.bankArea = TileAreaLocations.alKharidBankArea;
			this.restTimes = ObstacleRestTimes.alKharidMinRestTimes;
			this.courseName = course;
			courseRunner = new CourseRunner(this);
		}

		if (course.equals("Varrock Rooftop Course")) {
			this.courseStartArea = TileAreaLocations.varrockStartArea;
			this.insideStartAreaTile = TileAreaLocations.varrockStartTile;
			this.numOfObstacles = 9;
			this.insideBankAreaTile = TileAreaLocations.varrockBankTile;
			this.bankArea = TileAreaLocations.varrockBankArea;
			this.restTimes = ObstacleRestTimes.varrockMinRestTimes;
			this.courseName = course;
			courseRunner = new CourseRunner(this);
		}
		if (course.equals("Canifis Rooftop Course")) {
			this.courseStartArea = TileAreaLocations.canifisStartArea;
			this.insideStartAreaTile = TileAreaLocations.canifisStartTile;
			this.numOfObstacles = 8;
			this.insideBankAreaTile = TileAreaLocations.canifisBankTile;
			this.bankArea = TileAreaLocations.canifisBankArea;
			this.restTimes = ObstacleRestTimes.canifisMinRestTimes;
			this.courseName = course;
			courseRunner = new CourseRunner(this);
		}

		if (course.equals("Falador Rooftop Course")) {
			this.courseStartArea = TileAreaLocations.faladorStartArea;
			this.insideStartAreaTile = TileAreaLocations.faladorStartTile;
			this.numOfObstacles = 13;
			this.insideBankAreaTile = TileAreaLocations.faladorBankTile;
			this.bankArea = TileAreaLocations.faladorBankArea;
			this.restTimes = ObstacleRestTimes.faladorMinRestTimes;
			this.courseName = course;
			courseRunner = new CourseRunner(this);
		}

		if (course.equals("Rellekka Rooftop Course")) {
			this.courseStartArea = TileAreaLocations.rellekaStartArea;
			this.insideStartAreaTile = TileAreaLocations.rellekaStartTile;
			this.numOfObstacles = 7;
			this.insideBankAreaTile = TileAreaLocations.rellekaBankTile;
			this.bankArea = TileAreaLocations.rellekaBankArea;
			this.restTimes = ObstacleRestTimes.rellekkaMinRestTimes;
			this.courseName = course;
			courseRunner = new CourseRunner(this);
		}
		if (course.equals("Pollnivneach Rooftop Course")) {
			this.courseStartArea = TileAreaLocations.pollnivneachStartArea;
			this.insideStartAreaTile = TileAreaLocations.pollnivneachStartTile;
			this.numOfObstacles = 9;
			this.insideBankAreaTile = TileAreaLocations.pollnivneachStartTile;
			this.bankArea = TileAreaLocations.pollnivneachBankArea;
			this.restTimes = ObstacleRestTimes.pollnivneachMinRestTimes;
			this.courseName = course;
			courseRunner = new CourseRunner(this);
		}

	}

	public int[] getRestTimes() {
		return restTimes;
	}

	public void setRestTimes(int[] restTimes) {
		this.restTimes = restTimes;
	}

	public Area getCourseStartArea() {
		return courseStartArea;
	}

	public void setCourseStartArea(Area courseStartArea) {
		this.courseStartArea = courseStartArea;
	}

	public Area getBankArea() {
		return bankArea;
	}

	public void setBankArea(Area bankArea) {
		this.bankArea = bankArea;
	}

	public Tile getInsideBankAreaTile() {
		return insideBankAreaTile;
	}

	public void setInsideBankAreaTile(Tile insideBankAreaTile) {
		this.insideBankAreaTile = insideBankAreaTile;
	}

	public int getNumOfObstacles() {
		return numOfObstacles;
	}

	public void setNumOfObstacles(int numOfObstacles) {
		this.numOfObstacles = numOfObstacles;
	}

	public GameObject[] getListOfObstacles() {
		return listOfObstacles;
	}

	public void setListOfObstacles(GameObject[] listOfObstacles) {
		this.listOfObstacles = listOfObstacles;
	}

	public Area getInsideBankArea() {
		return bankArea;
	}

	public void setInsideBankArea(Area insideBankArea) {
		this.bankArea = insideBankArea;
	}

	public Tile getInsideStartAreaTile() {
		return insideStartAreaTile;
	}

	public void setInsideStartAreaTile(Tile insideStartAreaTile) {
		this.insideStartAreaTile = insideStartAreaTile;
	}

	public Tile getInsdeBankAreaTile() {
		return insideBankAreaTile;
	}

	public void setInsdeBankAreaTile(Tile insdeBankAreaTile) {
		this.insideBankAreaTile = insdeBankAreaTile;
	}

	public int getNumberOfObstacles() {
		return numOfObstacles;
	}

	public void setNumberOfObstacles(int numberOfObstacles) {
		this.numOfObstacles = numberOfObstacles;
	}

	public void runToCourseStart() {
		Sleep.sleep(1000);
		Walking.walk(insideStartAreaTile);
	}

	public void runCourse(double sleepMultiplier) {
		courseRunner.runCourse(sleepMultiplier);
	}

}

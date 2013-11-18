package at.er.ytbattle.gui;

public enum TeamType {

	RED(1, "Red"), BLUE(2, "Blue"), GREEN(3, "Green"), YELLOW(4, "Yellow"), PURPLE(5, "Purple"), CYAN(6, "Cyan"), BLACK(7, "Black"), WHITE(8, "White");

	private int id;
	private String name;

	private TeamType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}

import java.awt.Color;

/**
 * Enum that contains every possible state for a grid cell.
 *
 * The user can assign certain costs to each cell. Depending on the
 * cost, the cell acquieres the color and cost of the state. These
 * properties are never copied to the button; they are always handled
 * within the state assigned to the cell.
 *
 * This class is also the data source for the combo box state selector.
 */

public enum State {

	/**
	 * States used in the program.
	 */
	NORMAL ("Normal", Color.WHITE, 1.0),
	EASY ("Easy", Color.ORANGE, 0.3),
	TOUGH ("Tough", Color.LIGHT_GRAY, 5.0),
	VERY_TOUGH ("Very Tough", Color.GRAY, 10.0),
	IMPOSSIBLE ("Impossible", Color.BLACK, 0.0),
	START ("Start Point", Color.GREEN, 0.0),
	END ("End Point", Color.RED, 1.0),
	ROUTE ("Route", Color.YELLOW, 0.0);

	/**
	 * Fields for each state, they are public final since they won't be
	 * changed at runtime.
	 */
	public final String name;
	public final Color color;
	public final double cost;

	State (String name, Color color, double cost) {
		this.name = name;
		this.color = color;
		this.cost = cost;
	}

	/**
	 * Overridden so that the combo box displays a clear and simple description.
	 * @return String
	 */
	@Override
	public String toString () {
		return name + (cost != 0 ? (" (" + cost + ")") : "");
	}
}

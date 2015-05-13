import java.awt.Color;

/**
 * Created by PeterCP on 4/24/15.
 */

public enum State /*implements ComboBoxModel<State>*/ {

	NORMAL ("Normal", Color.WHITE, 1.0),
	EASY ("Easy", Color.ORANGE, 0.3),
	TOUGH ("Tough", Color.LIGHT_GRAY, 5.0),
	VERY_TOUGH ("Very Tough", Color.GRAY, 10.0),
	IMPOSSIBLE ("Impossible", Color.BLACK, 0.0),
	START ("Start Point", Color.GREEN, 0.0),
	END ("End Point", Color.RED, 1.0),
	ROUTE ("Route", Color.YELLOW, 0.0);

	public final String name;
	public final Color color;
	public final double cost;

	State (String name, Color color, double cost) {
		this.name = name;
		this.color = color;
		this.cost = cost;
	}

	public static State get (int id) {
		return values ()[(id) % values ().length];
	}

	@Override
	public String toString () {
		return name + (cost != 0 ? (" (" + cost + ")") : "");
	}
}

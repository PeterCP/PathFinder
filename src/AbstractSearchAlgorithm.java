import java.awt.Point;
//import java.util.ArrayList;

public abstract class AbstractSearchAlgorithm {

	protected Grid grid;
	protected Point start, end;
	protected Tree root, route;

	protected AbstractSearchAlgorithm () {}

	public abstract void run ();

	public void init (Grid grid) {
		this.grid = grid;
		start = grid.getStart ();
		end = grid.getEnd ();
		root = null;
		route = null;
	}
}

import java.awt.Point;
//import java.util.ArrayList;

public abstract class AbstractSearchAlgorithm {

	//protected ArrayList<Point> route;
	protected Grid grid;
	protected Tree root, route;
	protected Point start, end;

	public AbstractSearchAlgorithm () {
		//route = new ArrayList<Point> ();
	}

	public abstract void run ();

	public void init (Grid grid) {
		this.grid = grid;
		start = grid.getStart ();
		end = grid.getEnd ();
	}

	//public ArrayList<Point> getRoute () {
	//	return route;
	//}
}

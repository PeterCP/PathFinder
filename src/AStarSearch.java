import java.awt.*;
import java.util.ArrayList;

public class AStarSearch extends AbstractSearchAlgorithm {

	@Override
	public void run () {
		Point p = grid.getStart ();
		//root = new Tree<Node> (new Node (p, 0));
	}

	// SECOND (INCOMPLETE)

	//@Override
	//public void run () {
	//	Node newNode,
	//			node = new Node (start, 0);
	//	root = node;
	//	//checked = new ArrayList<Node> ();
	//	addChildren (node);
	//}

	//private void addChildren (Node node) {
	//	Node newNode;
	//	ArrayList<Point> neighbors = grid.getNeighbors (node.point);
	//	for (Point p : neighbors) {
	//		System.out.println (p);
	//	}
	//	System.out.println ();
	//	int minDistanceIndex;
	//	while (!neighbors.isEmpty ()) {
	//		minDistanceIndex = 0;
	//		for (int i = 0; i < neighbors.size (); i++) {
	//			if (grid.distance (neighbors.get (i), end) <
	//					grid.distance (neighbors.get (minDistanceIndex), end))
	//				minDistanceIndex = i;
	//		}
	//		newNode = new Node (neighbors.get (minDistanceIndex), node.cost +
	//				grid.get (neighbors.get (minDistanceIndex)).getCost ());
	//		node.add (newNode);
	//
	//		//checked.add (newNode);
	//		neighbors.remove (minDistanceIndex);
	//	}
	//}

	// FIRST

	//public void run () {
	//	route.clear ();
	//	run (start);
	//}
	//
	//private boolean run (Point p) {
	//	boolean b;
	//	double ad = grid.distance (p, end);
	//	setChecked (p, true);
	//	if (p.equals (end)) {
	//		return true;
	//	}
	//
	//	ArrayList<Point> neighbors = grid.getNeighbors (p);
	//	ArrayList<Double> distances = new ArrayList<Double> ();
	//	for (Point n : neighbors) {
	//		distances.add (grid.distance (n, end));
	//	}
	//
	//	int mi; // Index of min
	//	Point n;
	//	while (!neighbors.isEmpty () && !distances.isEmpty ()) {
	//		mi = 0;
	//		for (int i = 0; i < distances.size (); i++) {
	//			if (distances.get (i) < distances.get (mi))
	//				mi = i;
	//		}
	//		n = neighbors.get (mi);
	//		neighbors.remove (mi);
	//		distances.remove (mi);
	//
	//		if (!isChecked (n) && grid.get (n).getState () != State.IMPOSSIBLE) {
	//			b = run (n);
	//			if (b) {
	//				if (!n.equals (end))
	//					route.add (n);
	//				return true;
	//			}
	//		}
	//	}
	//	return false;
	//}
}

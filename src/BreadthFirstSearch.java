import java.awt.*;
import java.util.*;

/**
* Created by PeterCP on 4/28/15.
*/

public class BreadthFirstSearch extends AbstractSearchAlgorithm {

	@Override
	public void run () {
		//route.clear ();
		root = new Tree (start, 0);
		ArrayList<Point> neighbors;
		Queue<Tree> q = new LinkedList<Tree> ();

		Tree node;
		q.add (root);

		while (!q.isEmpty ()) {
			node = q.remove ();
			System.out.println ("Checking node: " + node);

			if (node.getPoint ().equals (end)) {
				//while (node.getParent () != null) {
				//	route.add (node.getPoint ());
				//	node = node.getParent ();
				//}
				route = node;
				break;
			}

			neighbors = grid.getNeighbors (node.getPoint ());
			for (Point n : neighbors) {
				if (root.getTree (n) == null) {
					q.add (node.addLeaf (n, node.getCost () + grid.get (n).getCost ()));
				}
			}
		}

		System.out.println (root);
	}

	//private boolean run (Point p) {
	//	boolean b;
	//	setChecked (p, true);
	//	if (p.equals (end)) {
	//		return true;
	//	}
	//	ArrayList<Point> neighbors = grid.getNeighbors (p);
	//	for (Point n : neighbors) {
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

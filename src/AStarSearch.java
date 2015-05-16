import java.awt.Point;
import java.util.*;

/**
 * Unlike the others, this algorithm will take into account the cost
 * of each node. This means that it will find the path with the smallest cost.
 */

public class AStarSearch extends AbstractSearchAlgorithm {

	@Override
	public void run () {
		Tree node, newNode;
		ArrayList<Point> neighbors;
		ArrayList<Tree> q = new ArrayList<Tree> ();
		int min;

		root = new Tree (start, 0, grid.distance (start, end));
		q.add (root);

		while (!q.isEmpty ()) {
			min = 0;
			for (int i = 0; i < q.size (); i++) {
				if (q.get (i).heuristic () <= q.get (min).heuristic ()) {
					min = i;
				}
			}
			node = q.remove (min);
			System.out.println ("Checking node: " + node);

			if (node.getPoint ().equals (end)) {
				route = node;
				break;
			}

			neighbors = grid.getNeighbors (node.getPoint ());
			for (Point n : neighbors) {
				newNode = new Tree (n, node.getCost () + grid.get (n).getCost (),
						grid.distance (n, end));
				if (root.getTree (n) == null) {
					node.addLeaf (newNode);
					q.add (newNode);
				}
			}
		}

		System.out.println (root);
	}
}

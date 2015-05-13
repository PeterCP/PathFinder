import java.awt.*;
import java.util.*;

/**
* Created by PeterCP on 4/28/15.
*/

public class BreadthFirstSearch extends AbstractSearchAlgorithm {

	@Override
	public void run () {
		Tree node, newNode;
		ArrayList<Point> neighbors;
		Queue<Tree> q = new LinkedList<Tree> ();

		root = new Tree (start, 0);
		q.add (root);

		while (!q.isEmpty ()) {
			node = q.remove ();
			System.out.println ("Checking node: " + node);

			if (node.getPoint ().equals (end)) {
				route = node;
				break;
			}

			neighbors = grid.getNeighbors (node.getPoint ());
			for (Point n : neighbors) {
				newNode = new Tree (n, node.getCost () + grid.get (n).getCost ());
				if (root.getTree (n) == null) {
					node.addLeaf (newNode);
					q.add (newNode);
				}
			}
		}

		System.out.println (root);
	}
}

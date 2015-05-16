import java.awt.*;
import java.util.*;

/* This algorithm takes the grid as transparent. This means that it
 * will only work with the states Start Point, End Point, and Impossible.
 * It will virtually ignore any other states, but it will use their costs
 * when calculating the route.
 */

public class DepthFirstSearch extends AbstractSearchAlgorithm {

	@Override
	public void run () {
		root = new Tree (start, 0);
		ArrayList<Point> neighbors;
		Stack<Tree> s = new Stack<Tree> ();
		boolean found;

		Tree node = root;
		s.add (root);
		while (!s.isEmpty ()) {
			found = false;
			System.out.println ("Checking node: " + node);
			if (node.getPoint ().equals (end)) {
				route = node;
				break;
			}
			neighbors = grid.getNeighbors (node.getPoint ());
			for (Point n : neighbors) {
				if (root.getTree (n) == null) {
					s.add (node.addLeaf (n, node.getCost () + grid.get (n).getCost ()));
					found = true;
					break;
				}
			}
			if (found)
				node = s.peek ();
			else
				node = s.pop ();
		}

		System.out.println (root);
	}
}

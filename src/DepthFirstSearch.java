import java.awt.*;
import java.util.*;

public class DepthFirstSearch extends AbstractSearchAlgorithm {

	@Override
	public void run () {
		//route.clear ();
		root = new Tree (start, 0);
		ArrayList<Point> neighbors;
		Stack<Tree> s = new Stack<Tree> ();

		Tree node;
		s.add (root);
		while (!s.isEmpty ()) {
			node = s.pop ();
			System.out.println ("Checking node: " + node);
			if (node.getPoint ().equals (end)) {
				//while (node.getParent () != null) {
					//route.add (node.getPoint ());
					//node = node.getParent ();
				//}
				route = node;
				break;
			}
			neighbors = grid.getNeighbors (node.getPoint ());
			for (Point n : neighbors) {
				if (root.getTree (n) == null)
					s.add (node.addLeaf (n, node.getCost () + grid.get (n).getCost ()));
			}
		}

		System.out.println (root);
	}
}

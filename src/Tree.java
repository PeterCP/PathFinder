import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Tree {

	private static final int indent = 2;

	public static Collection<Point> getSuccessors (Point of, Collection<Tree> in) {
		for (Tree tree : in) {
			if (tree.locate.containsKey (of)) {
				return tree.getSuccessors (of);
			}
		}
		return new ArrayList<Point> ();
	}

	private Point point;
	private double cost, distance;
	private ArrayList<Tree> leafs = new ArrayList<Tree> ();
	private Tree parent = null;
	private HashMap<Point, Tree> locate = new HashMap<Point, Tree> ();

	public Tree (Point head, double cost, double distance) {
		this.point = head;
		this.cost = cost;
		this.distance = distance;
		locate.put (head, this);
	}

	public Tree (Point head, double cost) {
		this (head, cost, 0);
	}

	public Tree (Tree t) {
		this (t.point, t.cost, t.distance);
	}

	public void addLeaf (Tree root, Tree leaf) {
		if (locate.containsKey (root)) {
			locate.get (root).addLeaf (leaf);
		}
		else {
			addLeaf (root).addLeaf (leaf);
		}
	}

	public Tree addLeaf (Point leaf, double cost, double distance) {
		Tree t = new Tree (leaf, cost, distance);
		return addLeaf (t);
	}

	public Tree addLeaf (Point leaf, double cost) {
		Tree t = new Tree (leaf, cost);
		return addLeaf (t);
	}

	public Tree addLeaf (Tree t) {
		leafs.add (t);
		t.parent = this;
		t.locate = this.locate;
		locate.put (t.point, t);
		return t;
	}

	public Tree removeLeaf (Point p) {
		Tree t = locate.get (p);
		t.parent.leafs.remove (t);
		return t;
	}

	public Tree setAsParent (Tree parentRoot) {
		Tree t = new Tree (parentRoot);
		t.leafs.add (this);
		this.parent = t;
		t.locate = this.locate;
		t.locate.put (point, this);
		t.locate.put (parentRoot.getPoint (), t);
		return t;
	}

	public Point getPoint () {
		return point;
	}

	public double getCost () {
		return cost;
	}

	public double getDistance () {
		return distance;
	}

	public double heuristic () {
		return cost + distance;
	}

	public Tree getTree (Point point) {
		return locate.get (point);
	}

	public Tree getParent () {
		return parent;
	}

	public Collection<Point> getSuccessors (Point root) {
		Collection<Point> successors = new ArrayList<Point> ();
		Tree tree = getTree (root);
		if (null != tree) {
			for (Tree leaf : tree.leafs) {
				successors.add (leaf.getPoint ());
			}
		}
		return successors;
	}

	public Collection<Tree> getSubTrees () {
		return leafs;
	}

	private String printTree (int increment) {
		String s = "";
		String inc = "";
		for (int i = 0; i < increment; ++i) {
			inc = inc + " ";
		}
		s = inc + "[x=" + point.x + ", y=" + point.y + ", c=" + cost +
				(distance != 0 ? (", d=" + distance) : "") + "]";
		for (Tree child : leafs) {
			s += "\n" + child.printTree (increment + indent);
		}
		return s;
	}

	@Override
	public String toString () {
		return printTree (0);
	}
}

import java.awt.Point;

public class Node {

	private Point point;
	private double cost;

	public Node () {}

	public Node (Point point, double cost) {
		this.point = point;
		this.cost = cost;
	}

	public Point getPoint () {
		return point;
	}

	public double getCost () {
		return cost;
	}

	@Override
	public String toString () {
		return getClass ().getName () + "[x=" + point.x +
				",y=" + point.y + ",c=" + cost + "]";
	}

	@Override
	public boolean equals (Object obj) {
		if (obj instanceof Node) {
			Node node = (Node) obj;
			return point.equals (node.point);
		}
		else if (obj instanceof Point) {
			Point p = (Point) obj;
			return point.equals (p);
		}
		else
			return super.equals (obj);
	}
}

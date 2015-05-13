import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Grid extends JPanel implements ActionListener {

	private int xCount, yCount;
	private Point start, end;
	private State selectedState;
	private Tree root, route;
	private GridButton[][] buttons;
	private AbstractSearchAlgorithm sa;

	public Grid (int xCount, int yCount) {
		super (new GridLayout (xCount, yCount));

		this.xCount = xCount;
		this.yCount = yCount;
		start = null;
		end = null;

		setSelectedState (State.values ()[0]);
		setSelectedMethod (SearchAlgorithm.values ()[0]);

		buttons = new GridButton[xCount][yCount];
		GridButton b;
		for (int j = 0; j < yCount; j++) {
			for (int i = 0; i < xCount; i++) {
				b = new GridButton (i, j, this);
				add (b);
				buttons[i][j] = b;
			}
		}

		setSize (GridButton.SIZE * xCount, GridButton.SIZE * yCount);
	}

	public void clear () {
		for (int j = 0; j < yCount; j++) {
			for (int i = 0; i < xCount; i++) {
				buttons[i][j].setState (State.NORMAL);
				buttons[i][j].setText ("");
			}
		}
		start = null;
		end = null;
	}

	public void clearRoute () {
		for (int j = 0; j < yCount; j++) {
			for (int i = 0; i < xCount; i++) {
				buttons[i][j].setRoute (false);
				buttons[i][j].setText ("");
			}
		}
	}

	public void search () {
		if (start == null) {
			Random r = new Random ();
			do
				start = new Point (r.nextInt (xCount), r.nextInt (yCount));
			while (start.equals (end));
			get (start).setState (State.START);
		}
		if (end == null) {
			Random r = new Random ();
			do
				end = new Point (r.nextInt (xCount), r.nextInt (yCount));
			while (end.equals (start));
			get (end).setState (State.END);
		}

		sa.init (this);
		clearRoute ();
		sa.run ();
		root = sa.root;
		route = sa.route;
		drawRoute ();
	}

	public void drawRoute () {
		drawRoute (root);

		Tree node = route;
		while (node.getParent () != null) {
			if (get (node.getPoint ()).getState () != State.START &&
					get (node.getPoint ()).getState () != State.END)
				get (node.getPoint ()).setRoute (true);
			node = node.getParent ();
		}
	}

	private void drawRoute (Tree node) {
		String c = String.valueOf (node.getCost ());
		get (node.getPoint ()).setText (c);
		get (node.getPoint ()).setToolTipText (String.valueOf (node.getCost ()));

		for (Tree n : node.getSubTrees ()) {
			drawRoute (n);
		}
	}

	public ArrayList<Point> getNeighbors (Point p) {
		ArrayList<Point> neighbors = new ArrayList<Point> ();
		if (p != null) {
			if (p.y > 0 && get (p).getState () != State.IMPOSSIBLE)
				neighbors.add (new Point (p.x, p.y - 1));
			if (p.x < xCount - 1 && get (p).getState () != State.IMPOSSIBLE)
				neighbors.add (new Point (p.x + 1, p.y));
			if (p.y < yCount - 1 && get (p).getState () != State.IMPOSSIBLE)
				neighbors.add (new Point (p.x, p.y + 1));
			if (p.x > 0 && get (p).getState () != State.IMPOSSIBLE)
				neighbors.add (new Point (p.x - 1, p.y));
		}

		return neighbors;
	}

	public double distance (Point p1, Point p2) {
		return Math.sqrt (Math.pow (p2.x - p1.x, 2) + Math.pow (p2.y - p1.y, 2));
	}

	public GridButton get (Point p) {
		return buttons[p.x][p.y];
	}

	public Point getStart () {
		return start;
	}

	public Point getEnd () {
		return end;
	}

	public void setSelectedState (State state) {
		selectedState = state;
	}

	public void setSelectedMethod (SearchAlgorithm searchAlgorithm) {
		sa = searchAlgorithm.getSearchAlgorithm ();
	}

	@Override
	public void actionPerformed (ActionEvent e) {
		GridButton b = (GridButton) e.getSource ();
		if (selectedState == State.START) {
			if (start != null)
				get (start).setState (State.NORMAL);
			start = b.getPoint ();
		}
		else if (selectedState == State.END) {
			if (end != null)
				get (end).setState (State.NORMAL);
			end = b.getPoint ();
		}
		b.setState (selectedState);
	}
}

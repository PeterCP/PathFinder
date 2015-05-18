import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Grid class represents the cell grid in the GUI. It also implements
 * the grid logic, as well as some utilities used by the search algorithms,
 * these are mainly:
 * {@code distance (Point, Point)}
 * {@code getNeighbors (Point)}
 * This class also implements the action listener for each grid cell.
 */

public class Grid extends JPanel implements ActionListener {

	private int xCount, yCount;
	private Point start, end;
	private State selectedState;
	private Tree root, route;
	private GridButton[][] buttons;
	private AbstractSearchAlgorithm sa;

	/**
	 * Constructor for the cell grid. It initializes the button grid as well
	 * as setting the default selected values for both State and SearchAlgorithm.
	 *
	 * @param xCount The column count for the grid.
	 * @param yCount The row count for the grid.
	 */
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

	/**
	 * This method is the entry point for the execution of the search.
	 * It generates a start and/or end point if needed, and it clears the
	 * last search route. After that, it initializes the selected search
	 * algorithm and runs the search. Finally it takes the needed information
	 * from the search algorithm class and draws the search in the grid.
	 */
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

		clearRoute ();

		sa.init (this);
		sa.run ();

		root = sa.root;
		route = sa.route;
		drawRoute ();
	}

	/**
	 * Clears the grid. That is, each button gets a NORMAL state, an empty
	 * text and a white color. It also clears the start and end fields.
	 */
	public void clear () {
		for (int j = 0; j < yCount; j++) {
			for (int i = 0; i < xCount; i++) {
				buttons[i][j].setState (State.NORMAL);
				buttons[i][j].setText ("");
				buttons[i][j].updateUI ();
			}
		}
		start = null;
		end = null;
	}

	/**
	 * Clears the route. That is, each button gets an empty text and their
	 * corresponding state's color and cost.
	 */
	public void clearRoute () {
		for (int j = 0; j < yCount; j++) {
			for (int i = 0; i < xCount; i++) {
				buttons[i][j].setRoute (false);
				buttons[i][j].setText ("");
				buttons[i][j].updateUI ();
			}
		}
	}

	/**
	 * Draws the route. That is, prints the cost for each grid cell, and
	 * then paints the ROUTE color in each grid cell that pertains to the
	 * route.
	 *
	 * If no path was found between the start and end points, the search tree
	 * is still printed on the grid, but the end point receives a ":(" that
	 * signals that no path was found.
	 */
	public void drawRoute () {
		drawRoute (root);

		if (route != null) {
			Tree node = route;
			while (node.getParent () != null) {
				if (get (node.getPoint ()).getState () != State.START &&
						get (node.getPoint ()).getState () != State.END)
					get (node.getPoint ()).setRoute (true);
				node = node.getParent ();
			}
		}
		else {
			get (end).setText (":(");
		}
	}

	/**
	 * Recursive method to draw the text for each node.
	 * @param node The search tree node to be explored.
	 */
	private void drawRoute (Tree node) {
		String c = String.valueOf (node.getCost ());
		get (node.getPoint ()).setText (c);
		get (node.getPoint ()).setToolTipText (String.valueOf (node.getCost ()));
		get (node.getPoint ()).updateUI ();

		for (Tree n : node.getSubTrees ()) {
			drawRoute (n);
		}
	}

	/**
	 * Gets the possible neighbors for the provided node point.
	 * @param p The grid cell point to be explored.
	 * @return An ArrayList with the possible neighbors.
	 */
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

	/**
	 * Returns the button at position p. It is unnecessary to handle the
	 * IndexOutOfBoundsException since this method is always accessed from
	 * controlled logic points, and never by the user directly.
	 *
	 * @param p The position of the requested button.
	 * @return The button at position p.
	 */
	public GridButton get (Point p) {
		return buttons[p.x][p.y];
	}

	/**
	 * Returns the start point.
	 * @return The start point.
	 */
	public Point getStart () {
		return start;
	}

	/**
	 * Returns the end point.
	 * @return The end point.
	 */
	public Point getEnd () {
		return end;
	}

	/**
	 * This method is called by the field {@code PathFinder.stateComboBox}. It sets
	 * the selected state for the buttons.
	 * @param state The selected state in PathFinder
	 */
	public void setSelectedState (State state) {
		selectedState = state;
	}

	/**
	 * This method is called by the field {@code PathFinder.methodComboBox}. It sets
	 * the selected search algorithm for the search.
	 * @param searchAlgorithm The selected search algorithm in PathFinder
	 */
	public void setSelectedMethod (SearchAlgorithm searchAlgorithm) {
		sa = searchAlgorithm.getSearchAlgorithm ();
	}

	/**
	 * Utility method to calculate the distance between the points p1 and p2.
	 * @param p1 First point.
	 * @param p2 Second point.
	 * @return The linear distance between p1 and p2.
	 */
	public double distance (Point p1, Point p2) {
		return Math.sqrt (Math.pow (p2.x - p1.x, 2) + Math.pow (p2.y - p1.y, 2));
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

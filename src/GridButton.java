import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class GridButton extends JButton {

	public static int SIZE = 30;

	private int x, y;
	private State state;

	public GridButton (int x, int y, ActionListener al) {
		super ();

		this.x = x;
		this.y = y;

		setSize (SIZE, SIZE);
		addActionListener (al);
		//addActionListener (this);

		setBorder (new LineBorder (Color.BLACK));
		setOpaque (true);
		setBorderPainted (true);

		setState (State.NORMAL);
	}

	public State getState () {
		return state;
	}

	public void setState (State state) {
		this.state = state;
		setBackground (state.color);
		//setText (this.toString ());
		setToolTipText (state.name);
		updateUI ();
	}

	public void setRoute (boolean b) {
		setBackground (b ? State.ROUTE.color : state.color);
		updateUI ();
	}

	public Point getPoint () {
		return new Point (x, y);
	}

	public double getCost () {
		return state.cost;
	}

	@Override
	public String toString () {
		return /*state.name.substring (0, 1) + "(" +*/ x + "," + y /*+ ")"*/;
	}
}

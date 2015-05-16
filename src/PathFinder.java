import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

/**
 * GUI class for the main (and only) window.
 */

public class PathFinder extends JFrame implements ActionListener {

	private static int X = 20, Y = 20;

	private JPanel controlsPanel;
	private JLabel stateLabel, methodLabel;
	private JComboBox<State> stateComboBox;
	private JComboBox<SearchAlgorithm> methodComboBox;
	private JButton goButton, clearButton;
	private Grid grid;

	public static void main (String[] args) {
		PathFinder window = new PathFinder ();
		window.setVisible (true);
	}

	public PathFinder () {
		super ("Path Finder");
		setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);
		setResizable (false);
		setLayout (null);

		addKeyListener (new KeyAdapter () {
			@Override
			public void keyPressed (KeyEvent e) {
				System.out.println (e.getKeyCode ());
			}
		});

		controlsPanel = new JPanel (new FlowLayout (SwingConstants.HORIZONTAL));
		controlsPanel.setLocation (10, 10);
		controlsPanel.setSize (620, 30);
		getContentPane ().add (controlsPanel, BorderLayout.PAGE_START);

		stateLabel = new JLabel ("State:");
		controlsPanel.add (stateLabel);

		stateComboBox = new JComboBox<State> (Arrays.copyOfRange (
				State.values (), 0, State.values ().length - 1));
		stateComboBox.setActionCommand ("stateComboBoxChanged");
		stateComboBox.addActionListener (this);
		//stateComboBox.addActionListener (new ActionListener () {
		//	@Override
		//	public void actionPerformed (ActionEvent e) {
		//		grid.setSelectedState ((State) stateComboBox.getSelectedItem ());
		//	}
		//});
		controlsPanel.add (stateComboBox);

		methodLabel = new JLabel ("Method:");
		controlsPanel.add (methodLabel);

		methodComboBox = new JComboBox<SearchAlgorithm> (SearchAlgorithm.values ());
		methodComboBox.setActionCommand ("methodComboBoxChanged");
		methodComboBox.addActionListener (this);
		//methodComboBox.addActionListener (new ActionListener () {
		//	@Override
		//	public void actionPerformed (ActionEvent e) {
		//		grid.setSelectedMethod ((SearchAlgorithm) methodComboBox.getSelectedItem ());
		//	}
		//});
		controlsPanel.add (methodComboBox);

		// TODO Implement Go button
		goButton = new JButton ("Go");
		goButton.addActionListener (this);
		//goButton.addActionListener (new ActionListener () {
		//	@Override
		//	public void actionPerformed (ActionEvent e) {
		//		try {
		//			grid.search ();
		//		}
		//		catch (Exception ex) {
		//			System.out.println (ex);
		//		}
		//	}
		//});
		controlsPanel.add (goButton);

		clearButton = new JButton ("Clear");
		clearButton.addActionListener (this);
		//clearButton.addActionListener (new ActionListener () {
		//	@Override
		//	public void actionPerformed (ActionEvent e) {
		//		grid.clear ();
		//	}
		//});
		controlsPanel.add (clearButton);

		grid = new Grid (X, Y);
		grid.setLocation (10, 60);
		getContentPane ().add (grid);

		Dimension cd = controlsPanel.getSize (),
				gd = grid.getSize ();
		int width = Math.max (cd.width, gd.width) + 20,
				height = 62 + cd.height + gd.height;
		setSize (width, height);
	}

	@Override
	public void actionPerformed (ActionEvent evt) {
		String cmd = evt.getActionCommand ();
		if (cmd.equals ("Go")) {
			grid.search ();
		}
		else if (cmd.equals ("Clear")) {
			grid.clear ();
		}
		else if (cmd.equals ("stateComboBoxChanged")) {
			grid.setSelectedState ((State) stateComboBox.getSelectedItem ());
		}
		else if (cmd.equals ("methodComboBoxChanged")) {
			grid.setSelectedMethod ((SearchAlgorithm) methodComboBox.getSelectedItem ());
		}
	}
}

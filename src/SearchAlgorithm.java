/**
 * The SearchAlgorithm enum class is a wrapper for each individual search
 * algorithm. It is used by the combo box {@code PathFinder.methodComboBox}
 * to pass the correct algorithm to the Grid class.
 */

public enum SearchAlgorithm {

	BREADTH (BreadthFirstSearch.class, "Breadth First Search"),
	DEPTH (DepthFirstSearch.class, "Depth First Search"),
	ASTAR (AStarSearch.class, "A* Search");

	private AbstractSearchAlgorithm searchAlgorithm;
	private String name;

	private SearchAlgorithm (Class<? extends AbstractSearchAlgorithm> c, String name) {
		try {
			searchAlgorithm = c.newInstance ();
		}
		catch (Exception e) {
			searchAlgorithm = null;
		}
		this.name = name;
	}

	public AbstractSearchAlgorithm getSearchAlgorithm () {
		return searchAlgorithm;
	}

	@Override
	public String toString () {
		return name;
	}
}

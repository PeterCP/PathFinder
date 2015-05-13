/**
 * Created by PeterCP on 4/28/15.
 */
public enum SearchAlgorithm {

	BREADTH (BreadthFirstSearch.class, "Breadth First Search"),
	DEPTH (DepthFirstSearch.class, "Depth First Search"),
	ASTAR (AStarSearch.class, "A* Search");

	private AbstractSearchAlgorithm searchAlgorithm;
	private String name;

	private SearchAlgorithm (Class c, String name) {
		try {
			searchAlgorithm = (AbstractSearchAlgorithm) c.newInstance ();
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

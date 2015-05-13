import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by PeterCP on 5/5/15.
 */
public class TreeTest {

	public static void main (String[] args) {
		//Tree<Integer> tree = new Tree<Integer> (0);
		//for (int i = 1; i < 20; i++) {
		//	tree.addLeaf (i/2, i);
		//}
		//System.out.println (tree);
		//System.out.println (tree.getTree (4));

		Stack<Integer> q = new Stack<Integer> ();
		for (int i = 0; i < 10; i++)
			q.add (i);
		System.out.println (q);
		System.out.println (q.pop ());
	}
}

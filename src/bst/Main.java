package bst;

public class Main {

	public static void main(String[] args) {
		Tree bst = new Tree();
		bst.setRoot(new Node(50));
		bst.getRoot().setLeft(new Node(30));
		bst.getRoot().setRight(new Node(60));
		bst.getRoot().getLeft().setLeft(new Node(10));
		bst.getRoot().getRight().setLeft(new Node(55));
	}

	private static void trimLeftChild(Tree tree) {
		if (tree.getRoot() == null) {
			return;
		}

		convertToRightSkewed(tree.getRoot());
	}

	private static void convertToRightSkewed(Node root) {

	}
}

package bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Transactions {
	Node root;

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}

		void insert(int key) {
			if (key == data) {
				return;
			}
			if (key < data) {
				if (left == null) {
					left = new Node(key);
				} else {
					left.insert(key);
				}
			} else {
				if (right == null) {
					right = new Node(key);
				} else {
					right.insert(key);
				}
			}
		}

	}

	public static void main(String[] args) {
		Transactions transacTree = new Transactions();

		// sample data
		transacTree.root = new Node(50);
		transacTree.root.left = new Node(30);
		transacTree.root.right = new Node(60);
		transacTree.root.left.left = new Node(10);
		transacTree.root.right.left = new Node(55);

		System.out.println("Before(PreOrder Traversal):");
		preOrder(transacTree.root);

		// tree with increasing
		transacTree.convertToIncreasingOrder(transacTree.root);

		System.out.println();
		System.out.println("After(PreOrder Traversal:");
		preOrder(transacTree.root);

	}

	private void convertToIncreasingOrder(Node node) {
		if (node == null) {
			return;
		}

		List<Integer> sortedData = getSortedElements(node); // asc order

		// reset the current root and create a new right skewed tree
		root = null;

		for (int data : sortedData) {
			if (root == null) {
				root = new Node(data);
			}
			root.insert(data);
		}
	}

	private List<Integer> getSortedElements(Node root) {
		List<Integer> result = new ArrayList<>();
		Stack<Node> stack = new Stack<>();
		Node current = root;
		boolean flag = false;
		while (!flag) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			} else {
				if (stack.empty()) {
					flag = true;
				} else {
					current = stack.pop();
					result.add(current.data);
					current = current.right;
				}
			}
		}
		return result;
	}

	void insert(int key) {
		if (root == null) {
			root = new Node(key);
			return;
		}

		root.insert(key);
	}

	private static void preOrder(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.data + " ");
		preOrder(node.left);
		preOrder(node.right);
	}
}

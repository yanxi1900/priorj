package adt.bst;

import adt.bt.BTNode;

/**
 * <p>
 *  A binary search tree (BST), sometimes also called an ordered or
 *   sorted binary tree, is a node-based binary tree data structure 
 *   which has the following properties:
 *
 *   The left subtree of a node contains only nodes with keys less than 
 *   the node's key.
 *   The right subtree of a node contains only nodes with keys greater 
 *  than the node's key.
 *   The left and right subtree must each also be a binary search tree.
 *   There must be no duplicate nodes.
 *
 * Generally, the information represented by each node is a record rather
 * than a single data element. However, for sequencing purposes, nodes are 
 * compared according to their keys rather than any part of their 
 * associated records.
 *
 * </p>
 * @author Samuel T. C. Santos
 *
 * @param <T>
 */
public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;
	protected int size;
	private int index;

	public BSTImpl() {
		root = new BSTNode<T>();
		root.setParent(new BSTNode<T>());
		root.setLeft(new BSTNode<T>());
		root.setRight(new BSTNode<T>());
		size = 0;
		index = 0;
	}

	@Override
	public BTNode<T> getRoot() {
		return root;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int height() {
		return height(root);
	}

	protected int height(BSTNode<T> node) {
		int leftHeight = 0;
		int rightHeight = 0;
		final int TREE_EMPTY = -1;

		if (node.isEmpty())
			return TREE_EMPTY;

		if (!node.getLeft().isEmpty())
			leftHeight += 1 + height((BSTNode<T>) node.getLeft());

		if (!node.getRight().isEmpty())
			rightHeight += 1 + height((BSTNode<T>) node.getRight());

		return Math.max(leftHeight, rightHeight);
	}

	@Override
	public BTNode<T> search(T elem) {
		return search(elem, root);
	}

	public BSTNode<T> search(T elem, BSTNode<T> node) {
		if (node.isEmpty() || elem.compareTo(node.getData()) == 0) {
			return node;
		}
		if (elem.compareTo(node.getData()) < 0) {
			return search(elem, (BSTNode<T>) node.getLeft());
		} else if (elem.compareTo(node.getData()) > 0) {
			return search(elem, (BSTNode<T>) node.getRight());
		} else {
			return null;
		}
	}

	
	@Override
	public void insert(T value) {
		if (root.isEmpty()) {
			root.setData(value);
			root.setLeft(new BSTNode<T>());
			root.setRight(new BSTNode<T>());
		} else {
			insert(value, root);
		}
		size++;
	}

	private void insert(T value, BSTNode<T> node) {
		if (value.compareTo(node.getData()) < 0) {
			if (node.getLeft().isEmpty()) {
				node.getLeft().setParent(node);
				node.getLeft().setData(value);
				node.getLeft().setLeft(new BSTNode<T>());
				node.getLeft().setRight(new BSTNode<T>());
			} else {
				insert(value, (BSTNode<T>) node.getLeft());
			}
		} else {
			if (node.getRight().isEmpty()) {
				node.getRight().setParent(node);
				node.getRight().setData(value);
				node.getRight().setLeft(new BSTNode<T>());
				node.getRight().setRight(new BSTNode<T>());
			} else {
				insert(value, (BSTNode<T>) node.getRight());
			}
		}
	}

	@Override
	public void remove(T key) {
		if (isEmpty())
			return;
		
		remove((BSTNode<T>) search(key));
	}

	private void remove(BSTNode<T> node) {

		if (!node.getLeft().isEmpty()) {
			transplant(node, (BSTNode<T>) node.getRight());
		} else if (node.getRight().isEmpty()) {
			transplant(node, (BSTNode<T>) node.getLeft());
		} else {
			BSTNode<T> y = minimum((BSTNode<T>) node.getRight());
			if (!y.getParent().equals(node)) {
				transplant(y, (BSTNode<T>) y.getRight());
				y.setRight(node.getRight());
				y.getRight().setParent(y);
			}
			transplant(node, y);
			y.setLeft(node.getLeft());
			y.getLeft().setParent(y);
		}
		size--;
	}

	

	protected void transplant(BSTNode<T> u, BSTNode<T> v) {
		if (u.getParent().isEmpty()) {
			root = v;
		} else if (u.equals(u.getParent().getLeft())) {
			u.getParent().setLeft(v);
		} else {
			u.getParent().setRight(v);
		}
		if (!v.isEmpty())
			v.setParent(u.getParent());
	}

	@Override
	public T[] preOrder() {
		@SuppressWarnings("rawtypes")
		Comparable[] array = new Comparable[size];
		preOrder((T[]) array, root);
		index = 0;
		return (T[]) array;
	}

	private void preOrder(T[] array, BSTNode<T> node) {
		if (!node.isEmpty()) {
			array[index++] = node.getData();
			preOrder(array, (BSTNode<T>) node.getLeft());
			preOrder(array, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] order() {
		@SuppressWarnings("rawtypes")
		Comparable[] array = new Comparable[size];
		order((T[]) array, root);
		index = 0;
		return (T[]) array;
	}

	private void order(T[] array, BSTNode<T> node) {
		if (!node.isEmpty()) {
			order(array, (BSTNode<T>) node.getLeft());
			array[index++] = node.getData();
			order(array, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] postOrder() {
		@SuppressWarnings("rawtypes")
		Comparable[] array = new Comparable[size];
		postOrder((T[]) array, root);
		index = 0;
		return (T[]) array;
	}

	private void postOrder(T[] array, BSTNode<T> node) {
		if (!node.isEmpty()) {
			postOrder(array, (BSTNode<T>) node.getLeft());
			postOrder(array, (BSTNode<T>) node.getRight());
			array[index++] = node.getData();

		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		while (!node.getRight().isEmpty()) {
			node = (BSTNode<T>) node.getRight();
		}
		return node;
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(root);
	}

	protected BSTNode<T> minimum(BSTNode<T> node) {
		while (!node.getLeft().isEmpty()) {
			node = (BSTNode<T>) node.getLeft();
		}
		return node;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		if (isEmpty())
			return null;

		return sucessor((BSTNode<T>) search(element));
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		if (node == null)
			return null;
		if (!node.getRight().isEmpty()) {
			return minimum((BSTNode<T>) node.getRight());
		}

		BSTNode<T> parent = (BSTNode<T>) node.getParent();

		while (!parent.isEmpty() && node.equals((BSTNode<T>) parent.getRight())) {
			node = parent;
			parent = (BSTNode<T>) parent.getParent();
		}

		return parent;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		return predecessor((BSTNode<T>) search(element));
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		if (node == null)
			return null;
		if (!node.getLeft().isEmpty())
			return maximum((BSTNode<T>) node.getLeft());

		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (!parent.isEmpty() && node.equals((BSTNode<T>) parent.getLeft())) {
			node = parent;
			parent = (BSTNode<T>) parent.getParent();
		}
		return parent;
	}
}
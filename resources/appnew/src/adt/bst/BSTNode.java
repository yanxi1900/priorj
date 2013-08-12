package adt.bst;

import adt.bt.BTNode;

public class BSTNode<T extends Comparable<T>> extends BTNode<T> {

	public int compareTo(T data) {
		return this.data.compareTo(data);
	}

	public int compareTo(BSTNode<T> node) {
		return this.data.compareTo(node.data);
	}

}
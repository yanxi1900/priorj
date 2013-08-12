package bst;

import adt.bst.BST;
import adt.bst.BSTImpl;
import adt.bst.BSTNode;


public class BSTTest {

	public static void main(String[] args) {

		BST<Integer> bst = new BSTImpl<Integer>();
		print("size should be 0? " + bst.size());

		bst.insert(30);
		print("size should be 1? " + bst.size());

		bst.insert(34);
		print("size should be 2? " + bst.size());

		bst.insert(29);
		print("size should be 3? " + bst.size());

		BSTNode<Integer> node = (BSTNode<Integer>) bst.search(29);
		print("search(29)should be 29? " + node.getData());

		node = (BSTNode<Integer>) bst.minimum();
		print("minimum() should be 29? " + node.getData());

		node = (BSTNode<Integer>) bst.maximum();
		print("maximum() should be 34? " + node.getData());

		// new initialization
		bst = new BSTImpl<Integer>();

		bst.insert(15);
		bst.insert(6);
		bst.insert(18);
		bst.insert(3);
		bst.insert(7);
		bst.insert(2);
		bst.insert(4);
		bst.insert(13);
		bst.insert(9);
		bst.insert(17);
		bst.insert(20);

		print("size() should be 11? " + bst.size());

		node = bst.sucessor(18);
		print("sucessor(18) should be 20? " + node.getData());

		node = bst.sucessor(3);
		print("sucessor(3) should be 4? " + node.getData());

		node = bst.sucessor(9);
		print("sucessor(9) should be 13? " + node.getData());

		node = bst.predecessor(20);
		print("predecessor(20) should be 18? " + node.getData());

		node = bst.predecessor(4);
		print("predecessor(4) should be 3? " + node.getData());

		Object[] pre = bst.preOrder();
		print("size() should be 11? " + bst.size());
		Object[] order = bst.order();
		print("size() should be 11? " + bst.size());
		Object[] post = bst.postOrder();
		print("size() should be 11? " + bst.size());

		print(pre);
		print(order);
		print(post);

		System.out.println("height should be 4 ? " + bst.height());

		print("size() should be 11? " + bst.size());

		bst.remove(2);
		print("remove(2), size should be 10? " + bst.size());

		bst.remove(7);
		print("remove(7), size should be 9? " + bst.size());

		order = bst.order();
		print(order);

	}

	private static void print(String str) {
		System.out.println(str);
	}

	private static void print(Object[] array) {

		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for (int i = 0; i < array.length; i++) {
			builder.append(array[i]);
			if (i < array.length - 1)
				builder.append(", ");
		}
		builder.append("]");

		System.out.println(builder.toString());
	}

}
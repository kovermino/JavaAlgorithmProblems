package com.tree;

/**
 * @author Benny
 *	AVL tree는 이 자료구조를 고안한 3명 이름의 앞글자를 딴 명칭이다.
 */
public class AVLTree {

	public class Node{
		private int key;
		private int height;
		private Node left;
		private Node right;
		
		public Node(int key) {
			this.key = key;
			height = 1;
		}
	}
	
	private Node root;
	
	private int height(Node node) {
		if(node==null) {
			return 0;
		}
		return node.height;
	}
	
	private Node rightRotate(Node root) {
		Node newRoot = root.left;
		Node rightNode = newRoot.right;
		
		newRoot.right = root;
		root.left = rightNode;
		
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
		
		return newRoot;
	}
	
	private Node leftRotate(Node root) {
		Node newRoot = root.right;
		Node leftNode = newRoot.left;
		
		newRoot.left = root;
		root.right = leftNode;
		
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
		
		return newRoot;
	}
	
	private int getBalance(Node node) {
		if(node == null) {
			return 0;
		}
		return height(node.left) - height(node.right);
	}
	
	private Node insert(Node node, int key) {
		
		if(node == null) {
			return (new Node(key));
		}
		
		if(key < node.key) {
			node.left = insert(node.left, key);
		}else if(key > node.key) {
			node.right = insert(node.right, key);
		}else {
			return node;
		}
		
		node.height = 1 + Math.max(height(node.left), height(node.right));
		int balance = getBalance(node);
		
		if(balance > 1) {
			if(key < node.left.key) {
				return rightRotate(node);
			}
			
			if(key > node.left.key) {
				node.left = leftRotate(node.left);
				return rightRotate(node);
			}
		}
		
		if(balance < -1) {
			if(key > node.right.key) {
				return leftRotate(node);
			}
			
			if(key < node.right.key) {
				node.right = rightRotate(node.right);
				return leftRotate(node);
			}
		}
		
		return node;
	}
	
	private void inOrder(Node node) {
		if(node != null) {
			inOrder(node.left);
			System.out.print(node.key+" ");
			inOrder(node.right);
		}
	}
	
	public static void main(String[] args) {
		
		AVLTree tree = new AVLTree();
		
		Node root = tree.root;
		root = tree.insert(root, 10);
		root = tree.insert(root, 20);
		root = tree.insert(root, 30);
		root = tree.insert(root, 40);
		root = tree.insert(root, 50);
		root = tree.insert(root, 25);
		
		tree.inOrder(root);
	}
	
}

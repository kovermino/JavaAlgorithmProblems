package com.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.jsoup.nodes.Node;

// TextNode를 담고있는 Iterator를 리턴
/*
 * Iterable은 iterator() 메서드를 가지고 있으므로 이를 오버라이딩해야함(Iterator를 리턴하도록 구현)
 * 그래서 inner class로 Iterator를 작성하고 이 인터페이스가 가지고 있는 메서드 스펙 중 hasNext와 next를 구현 
 */
public class WikiNodeIterable implements Iterable<Node> {

	private Node root;

	public WikiNodeIterable(Node root) {
	    this.root = root;
	}

	@Override
	public Iterator<Node> iterator() {
		return new WikiNodeIterator(root);
	}

	private class WikiNodeIterator implements Iterator<Node> {

		Deque<Node> stack;

		public WikiNodeIterator(Node node) {
			stack = new ArrayDeque<Node>();
		    stack.push(node);
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public Node next() {
			if (stack.isEmpty()) {
				throw new NoSuchElementException();
			}

			Node node = stack.pop();

			List<Node> nodes = new ArrayList<Node>(node.childNodes());
			Collections.reverse(nodes);
			for (Node child: nodes) {
				stack.push(child);
			}
			return node;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
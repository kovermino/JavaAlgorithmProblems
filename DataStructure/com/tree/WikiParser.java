package com.tree;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class WikiParser {
	
	private Elements paragraphs;
	private Deque<String> parenthesisStack;
	
	public WikiParser(Elements paragraphs) {
		this.paragraphs = paragraphs;
		this.parenthesisStack = new ArrayDeque<String>();
	}
	
	public Element findFirstLink() {
		for (Element paragraph: paragraphs) { 
			Element firstLink = findFirstLinkParagraph(paragraph);
			if (firstLink != null) {
				return firstLink;
			}
			if (!parenthesisStack.isEmpty()) {
				System.err.println("Warning: unbalanced parentheses."); 
	   	 	}
		}
		return null;
	}

	private Element findFirstLinkParagraph(Node root) {
		Iterable<Node> paragraph = new WikiNodeIterable(root);

		for (Node node: paragraph) {
			if (node instanceof TextNode) {
				parenthesisCheck((TextNode) node);
			}
			if (node instanceof Element) {
		        Element firstLink = getFirstLinkFromElement((Element) node);
		        if (firstLink != null) {
					return firstLink;
				}
			}
		}
		return null;
	}

	private Element getFirstLinkFromElement(Element node) {
		if (isValidLink(node)) {
			return node;
		}
		return null;
	}

	private boolean isValidLink(Element node) {
		if (isATag(node) && !isItalic(node) && !isInParens(node) && !isBookmark(node) && !isHelpPage(node)) {
			return true;
		}
		return false;
	}
	
	private boolean isATag(Element node) {
		if (node.tagName().equals("a")) {
			return true;
		}
		return false;
	}
	
	private boolean isBookmark(Element node) {
		if (startsWith(node, "#")) {
			return true;
		}
		return false;
	}
	
	private boolean isHelpPage(Element node) {
		if (startsWith(node, "/wiki/Help:")) {
			return true;
		}
		return false;
	}

	private boolean startsWith(Element node, String s) {
		return (node.attr("href").startsWith(s));
	}

	private boolean isInParens(Element elt) {
		return !parenthesisStack.isEmpty();
	}

	private boolean isItalic(Element start) {
		for (Element elt=start; elt != null; elt = elt.parent()) {
			if (elt.tagName().equals("i") || elt.tagName().equals("em")) {
				return true;
			}
		}
		return false;
	}

	private void parenthesisCheck(TextNode node) {
		StringTokenizer st = new StringTokenizer(node.text(), " ()", true);
		while (st.hasMoreTokens()) {
		     String token = st.nextToken();
		     if (token.equals("(")) {
		    	 parenthesisStack.push(token);
		     }
		     if (token.equals(")")) {
		    	 if (parenthesisStack.isEmpty()) {
		    		 System.err.println("Warning: unbalanced parentheses."); 
		    	 }
		    	 parenthesisStack.pop();
		     }
		}
	}
}
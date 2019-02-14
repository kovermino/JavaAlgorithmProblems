package com.tree;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class WikiNodeExample {
	
	public static void main(String[] args) throws IOException {
		String url = "https://ko.wikipedia.org/wiki/%EC%9E%90%EB%B0%94_(%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D_%EC%96%B8%EC%96%B4)";
		
		Connection conn = Jsoup.connect(url);
		Document firstDocument = conn.get();
		
		// 텍스트 컨텐츠를 가져온다.
		Element content = firstDocument.getElementById("mw-content-text");
		
		// 텍스트 컨텐츠 중에서 p태그의 데이터(설명 본문)을 가져온다.
		Elements pTagContents = content.select("p");
		
		// 첫 번째 단락(첫 번째 p태그를 가져온다)
		Element firstPara = pTagContents.get(0);
		
		//재귀 호출 DFS
		recursiveDFS(firstPara);
		System.out.println();

		// 반복적 호출 DFS
		iterativeDFS(firstPara);
		System.out.println();

		// WikiNodeIterable을 통한 본문 순회
		Iterable<Node> iter = new WikiNodeIterable(firstPara);
		for (Node node: iter) {
			if (node instanceof TextNode) {
				System.out.print(node);
			}
		}
	}

	private static void iterativeDFS(Node root) {
		Deque<Node> stack = new ArrayDeque<>();
		stack.push(root);
		
		while(!stack.isEmpty()) {
			Node current = stack.pop();
			if(current instanceof TextNode) {
				System.out.print(current);
			}
			List<Node> nodes = new ArrayList<>(current.childNodes());
			Collections.reverse(nodes);
			for(Node child: nodes) {
				stack.push(child);
			}
		}
	}

	private static void recursiveDFS(Node node) {
		if(node instanceof TextNode) {
			System.out.print(node);
		}
		
		for(Node child: node.childNodes()) {
			recursiveDFS(child);
		}
	}

}
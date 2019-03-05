package com.tree;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/**
 * @author benny
 * 검색어와 검색어가 페이지에서 등장하는 횟수를 매핑
 */
public class TermCounter {

	private Map<String, Integer> termCountMap;
	private String urlString;

	public TermCounter(String url) {
		this.urlString = url;
		this.termCountMap = new HashMap<String, Integer>();
	}

	public String getLabel() {
		return urlString;
	}

	public int size() {
		int total = 0;
		for(String key: this.termCountMap.keySet()) {
			total += this.termCountMap.get(key);
		}
		return total;
	}

	public void extractTermFromElements(Elements paragraphs) {
		for (Node node: paragraphs) {
			extractTermFromTextNode(node);
		}
	}

	public void extractTermFromTextNode(Node root) {
		for (Node node: new WikiNodeIterable(root)) {
			if (node instanceof TextNode) {
				addTermCountToMap(((TextNode) node).text());
			}
		}
	}

	public void addTermCountToMap(String text) {
		String[] array = text.replaceAll("\\pP", " ").toLowerCase().split("\\s+");
		
		for (int i=0; i<array.length; i++) {
			String term = array[i];
			incrementTermCount(term);
		}
	}

	public void incrementTermCount(String term) {
		put(term, get(term) + 1);
	}

	public void put(String term, int count) {
		termCountMap.put(term, count);
	}

	public Integer get(String term) {
		Integer count = termCountMap.get(term);
		return count == null ? 0 : count;
	}

	public Set<String> keySet() {
		return termCountMap.keySet();
	}

	public void printCounts() {
		for (String key: keySet()) {
			Integer count = get(key);
			System.out.println(key + ", " + count);
		}
		System.out.println("전체 개수 = " + size());
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		String url = "https://ko.wikipedia.org/wiki/%EC%9E%90%EB%B0%94_(%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D_%EC%96%B8%EC%96%B4)";

		WikiFetcherSingleton wikiFetcher = WikiFetcherSingleton.getInstance();
		Elements paragraphs = wikiFetcher.fetchWikipedia(url);

		TermCounter counter = new TermCounter(url.toString());
		counter.extractTermFromElements(paragraphs);
		counter.printCounts();
	}
}

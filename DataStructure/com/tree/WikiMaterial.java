package com.tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiMaterial {

    final static List<String> visited = new ArrayList<String>();
    final static WikiFetcherSingleton wikiFetcher = WikiFetcherSingleton.getInstance();
    private static int iteration = 10;


    public static void main(String[] args) throws IOException, InterruptedException {
        String destination = "https://ko.wikipedia.org/wiki/%EB%AC%BC%EC%A7%88";
        String source = "https://ko.wikipedia.org/wiki/%EC%9E%90%EB%B0%94_(%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D_%EC%96%B8%EC%96%B4)";

        testConjecture(destination, source, iteration);
    }

    public static void testConjecture(String destination, String source, int limit) throws IOException, InterruptedException {
		String url = source;
		for (int i=0; i<limit; i++) {
			if(!isVisited(url)) {
				visited.add(url);
			}
			
			Element link = getFirstValidLink(url);
			url = getLinkUrl(link);
			
			if (url.equals(destination)) {
				System.out.println("Eureka!");
				break;
			}
		}
	}
    
    public static boolean isVisited(String url) {
    	if (visited.contains(url)) {
			System.err.println("We're in a loop, exiting.");
			return true;
		}
    	return false;
    }
    
	public static Element getFirstValidLink(String url) throws IOException, InterruptedException {
		print("Fetching %s...", url);
		Elements paragraphs = wikiFetcher.fetchWikipedia(url);
		WikiParser parser = new WikiParser(paragraphs);
		Element firstLink = parser.findFirstLink();
		return firstLink;
	}
	
	public static String getLinkUrl(Element link) {
    	if (link == null) {
			System.err.println("Got to a page with no valid links.");
			return "";
		}
		
		System.out.println("**" + link.text() + "**");
		return link.attr("abs:href");
    }

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}
}
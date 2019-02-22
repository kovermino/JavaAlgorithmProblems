package com.tree;


import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiFetcherSingleton {
	
	private volatile static WikiFetcherSingleton instance;
	
	private long lastRequestTime = -1;
	private long minInterval = 1000;

	private WikiFetcherSingleton() {}
	
	public static WikiFetcherSingleton getInstance() {
		if(instance==null) {
			synchronized(WikiFetcherSingleton.class) {
				if(instance==null) {
					instance = new WikiFetcherSingleton();
				}
			}
		}
		return instance;
	}
	
	public Elements fetchWikipedia(String url) throws IOException, InterruptedException {
		sleepIfNeeded();
		
		Connection conn = Jsoup.connect(url);
		Document document = conn.get();
		return getParagraphs(document);
	}
	
	public Elements getParagraphs(Document document) {
		Element content = document.getElementById("mw-content-text");
		Elements paras = content.select("p");
		return paras;
	}
	
	private void sleepIfNeeded() throws InterruptedException {
		if(lastRequestTime != -1) {
			long now = System.currentTimeMillis();
			long requestAvalilableTime = lastRequestTime + minInterval;
			if(now < requestAvalilableTime) {
				try {
					Thread.sleep(requestAvalilableTime - now);
				} catch (InterruptedException e) {
					System.err.println("fetchWikipedia에 의해 인터럽트가 발생하였습니다");
				}
			}
		}
		lastRequestTime = System.currentTimeMillis();
	}
}

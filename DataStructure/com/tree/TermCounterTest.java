package com.tree;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

public class TermCounterTest {

	private TermCounter counter;

	@Before
	public void setUp() throws Exception {
		String url = "https://ko.wikipedia.org/wiki/%EC%9E%90%EB%B0%94_(%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D_%EC%96%B8%EC%96%B4)";
		
		WikiFetcherSingleton wikiFetcher = WikiFetcherSingleton.getInstance();
		Elements paragraphs = wikiFetcher.fetchWikipedia(url);
		
		counter = new TermCounter(url.toString());
		counter.extractTermFromElements(paragraphs);
	}

	@Test
	public void testSize() {
		assertThat(counter.size(), is(1288));
	}
}
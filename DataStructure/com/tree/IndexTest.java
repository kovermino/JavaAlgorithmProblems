package com.tree;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.util.Set;

import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

public class IndexTest {

	private Index index;
	private WikiFetcher wf;

	@Before
	public void setUp() {
		wf = new WikiFetcher();
		index = new Index();
	}

	@Test
	public void testIndexPage() throws IOException {
		String url = "https://ko.wikipedia.org/wiki/%EC%9E%90%EB%B0%94_(%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D_%EC%96%B8%EC%96%B4)";
		Elements paragraphs = wf.fetchWikipedia(url);
		index.indexPage(url, paragraphs);
		
		url = "https://ko.wikipedia.org/wiki/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D_%EC%96%B8%EC%96%B4";
		paragraphs = wf.fetchWikipedia(url);
		index.indexPage(url, paragraphs);
		
		Set<TermCounter> set = index.get("지향적");
		assertThat(set.size(), is(1));
		
		for (TermCounter tc: set) {
			assertThat(tc.size(), is(1288));
			assertThat(tc.get("지향적"), is(1));
			assertThat(tc.get("프로그래밍을"), is(1));
		}
	}

}
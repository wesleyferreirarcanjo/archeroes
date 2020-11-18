package com.archeroes.service;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archeroes.domain.MetaHero;
import com.archeroes.repository.PageRepository;

@Service
public class WebCrawler {

	@Autowired
	private PageRepository pageRepository;

	private String urlNextPage = "https://dc.fandom.com/wiki/Category:Good_Characters";

	public void getHeroesDCLinkWikiFandom() {
		
		try {
			while (Jsoup.connect(urlNextPage).get() != null) {

				Document doc = Jsoup.connect(urlNextPage).get();
				Elements nextPage = doc.select(".category-page__pagination-next");
				Elements links = doc.select(".category-page__member-link");

				for (Element link : links) {
					MetaHero metaHero = new MetaHero();
					metaHero.setName(link.text());
					metaHero.setLink(link.attr("href"));
					pageRepository.save(metaHero);
				}

				this.urlNextPage = nextPage.attr("href");
				
				if (nextPage.isEmpty()) {
					return;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getHeroDCContentWikiFandom () {
		
	}

}

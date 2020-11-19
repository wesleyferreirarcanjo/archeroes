package com.archeroes.service;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archeroes.domain.Hero;
import com.archeroes.domain.MetaHero;
import com.archeroes.repository.HeroRepository;
import com.archeroes.repository.MetaHeroRepository;

@Service
public class WebCrawler {

	@Autowired
	private MetaHeroRepository metaHeroRepository;

	@Autowired
	private HeroRepository heroRepository;

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
					metaHeroRepository.save(metaHero);
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

	public void getHeroDCContentWikiFandom() {
		
		List<MetaHero> metaHeros = metaHeroRepository.findTop100By();
		metaHeros.stream().forEach(metaHero -> this.saveHeroInDataBase(metaHero));
	}

	private void saveHeroInDataBase(MetaHero metaHero) {
		
		Hero hero = new Hero();
		this.sidePanelHeroPage(metaHero.getLink(), hero);
		this.heroRepository.save(hero);
	}

	private void sidePanelHeroPage(String link, Hero hero) {
		
		try {
			Document doc = Jsoup.connect("https://dc.fandom.com" + link).get();
			
			hero.setRealName(this.getValueBasedDataSourceName(doc, "RealName")); 
			hero.setMainAlias(this.getValueBasedDataSourceName(doc, "CurrentAlias"));
			hero.setOtherAlias(this.getValueBasedDataSourceName(doc, "Aliases"));
			hero.setAffiliation(this.getValueBasedDataSourceName(doc, "Affiliation"));
			hero.setRelatives(this.getValueBasedDataSourceName(doc, "Relatives"));
			hero.setBaseOfOperations(this.getValueBasedDataSourceName(doc, "BaseOfOperations"));
			hero.setAlignment(this.getValueBasedDataSourceName(doc, "Alignment"));
			hero.setIdentity(this.getValueBasedDataSourceName(doc, "Identity"));
			hero.setCitizenship(this.getValueBasedDataSourceName(doc, "Citizenship"));
			hero.setMaritalStatus(this.getValueBasedDataSourceName(doc, "MaritalStatus"));
			hero.setOccupation(this.getValueBasedDataSourceName(doc, "Occupation"));
			hero.setGender(this.getValueBasedDataSourceName(doc, "Gender"));
			hero.setHeight(this.getValueBasedDataSourceName(doc, "Height"));
			hero.setWeight(this.getValueBasedDataSourceName(doc, "Weight"));
			hero.setEyes(getValueBasedDataSourceName(doc, "Eyes"));
			hero.setHair(this.getValueBasedDataSourceName(doc, "Hair"));
			hero.setUniverse(this.getValueBasedDataSourceName(doc, "Universe"));
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private String getValueBasedDataSourceName(Document doc, String valueDataSource) {

		Elements elementParent = doc.getElementsByAttributeValueMatching("data-source", valueDataSource);

		if (elementParent.isEmpty()) {
			return "";
		}

		Elements element = elementParent.select(".pi-data-value");

		if (element.isEmpty()) {
			return "";
		}

		return element.get(0).text();
	}

}

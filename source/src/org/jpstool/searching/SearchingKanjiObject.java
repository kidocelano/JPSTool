package org.jpstool.searching;

import java.util.HashMap;
import java.util.Map;

public class SearchingKanjiObject {
	private String kanji;
	private String hanViet;
	private String meaning;
	private Map<String, String> mapComponent;

	public SearchingKanjiObject(String kanji, String hanViet, String meaning) {
		this.kanji = kanji;
		this.hanViet = hanViet;
		this.meaning = meaning;
		this.mapComponent = new HashMap<String, String>();
	}

	public void addKanjiComponent(String radial, String hanViet) {
		this.mapComponent.put(radial, hanViet);
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getKanji() {
		return kanji;
	}

	public void setKanji(String kanji) {
		this.kanji = kanji;
	}

	public String getHanViet() {
		return hanViet;
	}

	public void setHanViet(String hanViet) {
		this.hanViet = hanViet;
	}

	public Map<String, String> getMapComponent() {
		return mapComponent;
	}

	public void setMapComponent(Map<String, String> mapComponent) {
		this.mapComponent = mapComponent;
	}

	@Override
	public String toString() {
		return "SearchingKanjiObject [kanji=" + kanji + ", hanViet=" + hanViet + ", meaning=" + meaning + ", mapComponent=" + mapComponent + "]";
	}
}

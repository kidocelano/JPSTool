package org.jpstool.searching;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SearchingKanjiObject {
	private String kanji;
	private String hanViet;
	private String meaning;
	private String on;
	private String kun;
	private Map<String, String> mapComponent;
	private List<SimilarKanji> listSimilarKanji;
	private List<SentenceExample> listSentence;

	public SearchingKanjiObject(String kanji, String hanViet, String meaning, String on, String kun) {
		this.kanji = kanji;
		this.hanViet = hanViet;
		this.meaning = meaning;
		this.on = on;
		this.kun = kun;
		this.mapComponent = new LinkedHashMap<String, String>();
		this.listSimilarKanji = new ArrayList<SearchingKanjiObject.SimilarKanji>();
		this.listSentence = new ArrayList<SearchingKanjiObject.SentenceExample>();
	}

	public void addSentence(String content, String mean, String transcription) {
		this.listSentence.add(new SentenceExample(content, mean, transcription));
	}
	
	public void setSentenceExample(List<SentenceExample> list) {
		this.listSentence = list;
	}

	public List<SentenceExample> getListSentence() {
		return this.listSentence;
	}

	public void addSimilarKanji(String w, String p, String m, String h) {
		listSimilarKanji.add(new SimilarKanji(w, p, m, h));
	}

	public List<SimilarKanji> getListSimilarKanji() {
		return this.listSimilarKanji;
	}

	public String getOn() {
		return on;
	}

	public void setOn(String on) {
		this.on = on;
	}

	public String getKun() {
		return kun;
	}

	public void setKun(String kun) {
		this.kun = kun;
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
		return "SearchingKanjiObject [kanji=" + kanji + ", hanViet=" + hanViet + ", meaning=" + meaning + ", on=" + on + ", kun=" + kun + ", mapComponent=" + mapComponent
				+ ", listSimilarKanji=" + listSimilarKanji + ", listSentence=" + listSentence + "]";
	}



	public static class SentenceExample {
		private String content;
		private String mean;
		private String transcription;

		public SentenceExample(String content, String mean, String transcription) {
			super();
			this.content = content;
			this.mean = mean;
			this.transcription = transcription;
		}

		public String getContent() {
			return content;
		}

		public String getMean() {
			return mean;
		}

		public String getTranscription() {
			return transcription;
		}

		@Override
		public String toString() {
			return "SentenceExample [content=" + content + ", mean=" + mean + ", transcription=" + transcription + "]";
		}
	}

	public static class SimilarKanji {
		private String w;
		private String p;
		private String m;
		private String h;

		public SimilarKanji(String w, String p, String m, String h) {
			super();
			this.w = w;
			this.p = p;
			this.m = m;
			this.h = h;
		}

		public String getW() {
			return w;
		}

		public String getP() {
			return p;
		}

		public String getM() {
			return m;
		}

		public String getH() {
			return h;
		}

		@Override
		public String toString() {
			return "Examples [w=" + w + ", p=" + p + ", m=" + m + ", h=" + h + "]";
		}
	}
}

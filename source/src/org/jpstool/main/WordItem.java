package org.jpstool.main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class WordItem {
	private String kanjiWord;
	private String hanVietWord;
	private String meaning;
	private String hiragana;
	private String otherText;
	private LinkedHashMap<String, List<String>> mapAnalyseRadical;

	public WordItem(String kanjiWord, String hanVietWord, String meaning, String hiragana) {
		this.kanjiWord = kanjiWord;
		this.hanVietWord = hanVietWord;
		this.meaning = meaning;
		this.hiragana = hiragana;
		init();
	}

	public WordItem(String kanjiWord, String hanVietWord, String meaning, String hiragana, String otherText) {
		this.kanjiWord = kanjiWord;
		this.hanVietWord = hanVietWord;
		this.meaning = meaning;
		this.hiragana = hiragana;
		this.otherText = otherText;
		init();
	}

	private void init() {
		mapAnalyseRadical = new LinkedHashMap<String, List<String>>();
	}

	public void addKanjiRadical(String kanjiText, String Radical) {
		List<String> radicalArr = mapAnalyseRadical.get(kanjiText);
		if (radicalArr == null) {
			radicalArr = new ArrayList<String>();
			mapAnalyseRadical.put(kanjiText, radicalArr);
		}
		radicalArr.add(Radical);
	}

	public List<String> getKanjiRadical(String kanjiText) {
		return mapAnalyseRadical.get(kanjiText);
	}
	
	public LinkedHashMap<String, List<String>> getKanjiRadicals() {
		return mapAnalyseRadical;
	}

	public String getOtherText() {
		return otherText;
	}

	public void setOtherText(String otherTExt) {
		this.otherText = otherTExt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kanjiWord == null) ? 0 : kanjiWord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordItem other = (WordItem) obj;
		if (kanjiWord == null) {
			if (other.kanjiWord != null)
				return false;
		} else if (!kanjiWord.equals(other.kanjiWord))
			return false;
		return true;
	}

	public String getKanjiWord() {
		return kanjiWord;
	}

	public void setKanjiWord(String kanjiWord) {
		this.kanjiWord = kanjiWord;
	}

	public String getHanVietWord() {
		return hanVietWord;
	}

	public void setHanVietWord(String hanVietWord) {
		this.hanVietWord = hanVietWord;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getHiragana() {
		return hiragana;
	}

	public void setHiragana(String hiragana) {
		this.hiragana = hiragana;
	}

	@Override
	public String toString() {
		return "WordItem [kanjiWord=" + kanjiWord + ", hanVietWord=" + hanVietWord + ", meaning=" + meaning + ", hiragana=" + hiragana + ", otherTExt=" + otherText + "]";
	}
}
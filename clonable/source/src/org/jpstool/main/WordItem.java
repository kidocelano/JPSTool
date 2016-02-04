package org.jpstool.main;

import java.io.Serializable;
import java.util.List;

import org.jpstool.searching.SearchingKanjiObject;

public class WordItem implements Serializable {
	private static final long serialVersionUID = 7795917386791193981L;

	private String kanjiWord;
	private String hanVietWord;
	private String meaning;
	private String hiragana;
	private String otherText;
	private transient List<SearchingKanjiObject> lstSearchingKanji;

	public WordItem(String kanjiWord, String hanVietWord, String meaning, String hiragana, String otherText) {
		this.kanjiWord = kanjiWord;
		this.hanVietWord = hanVietWord;
		this.meaning = meaning;
		this.hiragana = hiragana;
		this.otherText = otherText;
	}

	public List<SearchingKanjiObject> getSearchingKanji() {
		return this.lstSearchingKanji;
	}

	public void setSearchingKanji(List<SearchingKanjiObject> lstSearchingKanji) {
		this.lstSearchingKanji = lstSearchingKanji;
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
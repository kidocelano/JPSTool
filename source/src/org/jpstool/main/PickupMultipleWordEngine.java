package org.jpstool.main;

import java.util.List;
import java.util.Random;

public class PickupMultipleWordEngine implements PickupWordEnigne {
	private int wordNumber;
	private static final String CONST_DEFAULT_DELIMIETER_KANJI = " - ";

	public PickupMultipleWordEngine(int wordNumber) {
		this.wordNumber = wordNumber;
	}

	@Override
	public WordItem pickUpWord(List<WordItem> listWordItem) {
		Random random = new Random();
		StringBuffer sbKnaji = new StringBuffer();
		StringBuffer sbMeaning = new StringBuffer();

		for (int i = 0; i < wordNumber; i++) {
			WordItem wordItem = listWordItem.get(random.nextInt(listWordItem.size()));

			sbKnaji.append(wordItem.getKanjiWord());
			sbKnaji.append(CONST_DEFAULT_DELIMIETER_KANJI);

			sbMeaning.append(wordItem.getKanjiWord());
			sbMeaning.append(String.format("(%s)", wordItem.getHanVietWord()));
			sbMeaning.append(" -> " + wordItem.getMeaning());
			sbMeaning.append(System.lineSeparator());
		}

		return new WordItem(sbKnaji.toString(), "", sbMeaning.toString(), "", "");
	}
}

package org.jpstool.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class LoadWordPlainText implements LoadWordsEngine {

	private WordItem parseWordItemFromPlainText(String text) {
		String[] result = text.split("(\t+->\t+)|\t+");

		String kanjiText = result.length > 0 ? result[0] : "";
		String hanVietText = result.length > 1 ? result[1] : "";
		String meaningText = result.length > 2 ? result[2] : "";
		String hiraganaText = result.length > 3 ? result[3] : "";
		String otherText = "";

		if (result.length > 4) {
			otherText = StringUtils.join(result, "\t", 4);
		}

		return new WordItem(kanjiText, hanVietText, meaningText, hiraganaText, otherText);
	}

	@Override
	public List<WordItem> getListWords(File wordFile) throws IOException {
		FileInputStream fis = new FileInputStream(wordFile);
		List<String> allLines = IOUtils.readLines(fis, "UTF-8");
		List<WordItem> listWordItem = new ArrayList<>();
		for (String line : allLines) {
			listWordItem.add(parseWordItemFromPlainText(line));
		}
		return listWordItem;
	}

}

package org.jpstool.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface LoadWordsEngine {
	public List<WordItem> getListWords(File wordFile) throws IOException;
}

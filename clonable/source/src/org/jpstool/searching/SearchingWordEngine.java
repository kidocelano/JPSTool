package org.jpstool.searching;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

public interface SearchingWordEngine {
	public List<SearchingKanjiObject> search(String word) throws IOException, JSONException;
}

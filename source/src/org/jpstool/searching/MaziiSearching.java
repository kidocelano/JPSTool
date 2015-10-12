package org.jpstool.searching;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MaziiSearching implements SearchingWordEngine {
	private static final String CONST_URL = "http://mazii.net/api/mazii/%s/10";

	@Override
	public List<SearchingKanjiObject> search(String searchingWord) throws IOException, JSONException {
		List<SearchingKanjiObject> lstResult = new ArrayList<SearchingKanjiObject>();
		SearchingKanjiObject kanjiObject = null;

		JSONObject jsonObject = JsonReader.readJsonFromUrl(String.format(CONST_URL, searchingWord));
		JSONArray result = jsonObject.getJSONArray("results");

		for (int i = 0; i < result.length(); i++) {
			JSONObject word = result.getJSONObject(i);
			String kanjiText = word.getString("kanji");
			String hanvietText = word.getString("mean");
			String meaning = word.getString("detail");
			kanjiObject = new SearchingKanjiObject(kanjiText, hanvietText, meaning);
			if (word.has("compDetail")) {
				JSONArray compDetail = word.getJSONArray("compDetail");
				for (int j = 0; j < compDetail.length(); j++) {
					JSONObject joComponent = compDetail.getJSONObject(j);
					String kanjiComponent = "";
					String hanVietComponent = "";

					if (joComponent.has("w")) {
						kanjiComponent = joComponent.getString("w");
					}

					if (joComponent.has("h")) {
						hanVietComponent = joComponent.getString("h");
					}

					kanjiObject.addKanjiComponent(kanjiComponent, hanVietComponent);
				}

			}
			lstResult.add(kanjiObject);
		}

		return lstResult;
	}
}

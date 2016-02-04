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

		if (jsonObject.has("results") == false || jsonObject.get("results") instanceof JSONArray == false) {
			return lstResult;
		}

		JSONArray result = jsonObject.getJSONArray("results");

		for (int i = 0; i < result.length(); i++) {
			JSONObject word = result.getJSONObject(i);
			String kanjiText = "";
			String hanvietText = "";
			String meaning = "";
			String on = "";
			String kun = "";

			if (word.has("kanji") && word.get("kanji") instanceof String) {
				kanjiText = word.getString("kanji");
			}

			if (word.has("mean") && word.get("mean") instanceof String) {
				hanvietText = word.getString("mean");
			}

			if (word.has("detail") && word.get("detail") instanceof String) {
				meaning = word.getString("detail");
			}

			if (word.has("on") && word.get("on") instanceof String) {
				on =  word.getString("on");
			}

			if (word.has("kun") && word.get("kun") instanceof String) {
				kun =  word.getString("kun");
			}

			kanjiObject = new SearchingKanjiObject(kanjiText, hanvietText, meaning, on, kun);
			if (word.has("compDetail") && word.get("compDetail") instanceof JSONArray) {
				JSONArray compDetail = word.getJSONArray("compDetail");
				for (int j = 0; j < compDetail.length(); j++) {
					JSONObject joComponent = compDetail.getJSONObject(j);
					String kanjiComponent = "";
					String hanVietComponent = "";

					if (joComponent.has("w") && joComponent.get("w") instanceof String) {
						kanjiComponent = joComponent.getString("w");
					}

					if (joComponent.has("h") && joComponent.get("h") instanceof String) {
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

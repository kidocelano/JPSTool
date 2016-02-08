package org.jpstool.searching;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MaziiSearching implements SearchingWordEngine {
	private static final String CONST_URL_KANJI = "http://mazii.net/api/mazii/%s/10";
	private static final String CONST_URL_SENTENCE = "http://mazii.net/api/smile/%s";

	public List<SearchingKanjiObject.SentenceExample> searchSentenceExample(String searchingWord) throws IOException, JSONException {
		JSONObject jsonObject = JsonReader.readJsonFromUrl(String.format(CONST_URL_SENTENCE, searchingWord));

		List<SearchingKanjiObject.SentenceExample> listSetenceExample = new ArrayList<SearchingKanjiObject.SentenceExample>();

		if (jsonObject.has("results") == false || jsonObject.get("results") instanceof JSONArray == false) {
			return listSetenceExample;
		}

		JSONArray result = jsonObject.getJSONArray("results");

		for (int i = 0; i < result.length(); i++) {
			JSONObject sentence = result.getJSONObject(i);
			String content = getValueFromJSONObject(sentence, "content");
			String mean = getValueFromJSONObject(sentence, "mean");
			String transcription = getValueFromJSONObject(sentence, "transcription");
			listSetenceExample.add(new SearchingKanjiObject.SentenceExample(content, mean, transcription));
		}

		return listSetenceExample;
	}

	@Override
	public List<SearchingKanjiObject> search(String searchingWord) throws IOException, JSONException {
		List<SearchingKanjiObject.SentenceExample> listSentenceExample = searchSentenceExample(searchingWord);
		
		List<SearchingKanjiObject> lstResult = new ArrayList<SearchingKanjiObject>();
		SearchingKanjiObject kanjiObject = null;

		JSONObject jsonObject = JsonReader.readJsonFromUrl(String.format(CONST_URL_KANJI, searchingWord));

		if (jsonObject.has("results") == false || jsonObject.get("results") instanceof JSONArray == false) {
			return lstResult;
		}

		JSONArray result = jsonObject.getJSONArray("results");

		for (int i = 0; i < result.length(); i++) {
			JSONObject word = result.getJSONObject(i);
			String kanjiText = getValueFromJSONObject(word, "kanji");
			String hanvietText = getValueFromJSONObject(word, "mean");
			String meaning = getValueFromJSONObject(word, "detail");
			String on = getValueFromJSONObject(word, "on");
			String kun = getValueFromJSONObject(word, "kun");

			kanjiObject = new SearchingKanjiObject(kanjiText, hanvietText, meaning, on, kun);
			kanjiObject.setSentenceExample(listSentenceExample);
			if (word.has("compDetail") && word.get("compDetail") instanceof JSONArray) {
				JSONArray compDetail = word.getJSONArray("compDetail");
				for (int j = 0; j < compDetail.length(); j++) {
					JSONObject joComponent = compDetail.getJSONObject(j);
					String kanjiComponent = getValueFromJSONObject(joComponent, "w");
					String hanVietComponent = getValueFromJSONObject(joComponent, "h");
					kanjiObject.addKanjiComponent(kanjiComponent, hanVietComponent);
				}
			}

			if (word.has("examples") && word.get("examples") instanceof JSONArray) {
				JSONArray compExample = word.getJSONArray("examples");
				for (int j = 0; j < compExample.length(); j++) {
					JSONObject joExample = compExample.getJSONObject(j);
					String w = getValueFromJSONObject(joExample, "w");
					String p = getValueFromJSONObject(joExample, "p");
					String m = getValueFromJSONObject(joExample, "m");
					String h = getValueFromJSONObject(joExample, "h");
					kanjiObject.addSimilarKanji(w, p, m, h);
				}
			}
			lstResult.add(kanjiObject);
		}
		
		return lstResult;
	}

	private String getValueFromJSONObject(JSONObject jsonObject, String key) throws JSONException {
		if (jsonObject.has(key) && jsonObject.get(key) instanceof String) {
			return jsonObject.getString(key);
		}
		return "";
	}
}

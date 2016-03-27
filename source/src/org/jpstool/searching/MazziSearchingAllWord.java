package org.jpstool.searching;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MazziSearchingAllWord implements SearchingWordEngine {
	private static final String CONST_URL_SEARCHING = "http://mazii.net/api/search/%s/10/1";
	private SearchingWordEngine mazziSearchKanji;
	
	@Override
	public List<SearchingKanjiObject> search(String word) throws IOException, JSONException {
		List<SearchingKanjiObject> lstResult = new ArrayList<SearchingKanjiObject>();

		mazziSearchKanji = new MaziiSearching();
		List<SearchingKanjiObject> lstResultKanji = mazziSearchKanji.search(word);
		String kanjiText = "";
		for (SearchingKanjiObject searchingKanjiObject : lstResultKanji) {
			kanjiText += searchingKanjiObject.getHanViet() + " ";
		}
		kanjiText.substring(0, kanjiText.length() - 1);
		
		JSONObject jsonObject = JsonReader.readJsonFromUrl(String.format(CONST_URL_SEARCHING, word));

		if (jsonObject.has("status") == false) {
			return lstResult;
		}

		int intStatus =  (Integer) jsonObject.get("status");
		if (intStatus != 200) {
			System.out.println("out 2");
			return lstResult;
		}

		JSONArray arrData = jsonObject.getJSONArray("data");
		if (arrData.length() > 0) {
			JSONObject objData = arrData.getJSONObject(0);
			String strPhonetic = objData.getString("phonetic");
			strPhonetic = strPhonetic.replaceAll("「|」", "");
			JSONArray arrayMeans = objData.getJSONArray("means");
			String mean = "";
			SearchingKanjiObject sko = new SearchingKanjiObject(word, kanjiText, "", strPhonetic, strPhonetic);
			for (int i = 0; i < arrayMeans.length(); i++) {
				JSONObject objMeans = arrayMeans.getJSONObject(i);
				mean += objMeans.getString("mean") + "(" + objMeans.getString("kind") + "), ";
			}
			mean = mean.substring(0, mean.length() - 1);
			sko.setMeaning(mean);
			lstResult.add(sko);
		}
		return lstResult;
	}
	
	public static void main(String[] args) throws IOException, JSONException {
		MazziSearchingAllWord m = new MazziSearchingAllWord();
		List<SearchingKanjiObject> lstResult = m.search("漢字");
		System.out.println("lstResult : " + lstResult.size());
		StringBuffer sbResult = new StringBuffer();
		
		for (SearchingKanjiObject searchingKanjiObject : lstResult) {
			//System.out.println(searchingKanjiObject);
			sbResult.append(String.format("%-20s %-20s %-100s %s", searchingKanjiObject.getKanji(), searchingKanjiObject.getHanViet(), searchingKanjiObject.getMeaning(), searchingKanjiObject.getOn()));
		}
		
		System.out.println(sbResult);
	}
}

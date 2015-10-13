package org.jpstool.searching;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jpstool.main.WordItem;

public class RadicalAnalysing {
	public static class RadicalNode {
		private String radical;
		private String hanViet;

		private List<RadicalNode> lstChildRadical;

		public RadicalNode(String radical, String hanViet, List<RadicalNode> lstChildRadical) {
			this.radical = radical;
			this.hanViet = hanViet;
			this.lstChildRadical = lstChildRadical;
		}

		public RadicalNode(String radical, String hanViet) {
			this(radical, hanViet, new ArrayList<RadicalNode>());
		}

		public void addChildRadicalNode(RadicalNode node) {
			lstChildRadical.add(node);
		}

		public String getRadical() {
			return radical;
		}

		public void setRadical(String radical) {
			this.radical = radical;
		}

		public String getHanViet() {
			return hanViet;
		}

		public void setHanViet(String hanViet) {
			this.hanViet = hanViet;
		}

		public List<RadicalNode> getLstChildRadical() {
			return lstChildRadical;
		}

		public void setLstChildRadical(List<RadicalNode> lstChildRadical) {
			this.lstChildRadical = lstChildRadical;
		}

		@Override
		public String toString() {
			return "RadicalNode [radical=" + radical + ", hanViet=" + hanViet + ", lstChildRadical=" + lstChildRadical + "]";
		}
	}

	private WordItem wordItem;
	private SearchingWordEngine searchingEngine;
	private RadicalNode rootRadicalNode;

	public RadicalAnalysing(WordItem wordItem, SearchingWordEngine searchingEngine) {
		this.wordItem = wordItem;
		this.searchingEngine = searchingEngine;
	}

	public void analyse() {
		rootRadicalNode= new RadicalNode(wordItem.getKanjiWord(), wordItem.getHanVietWord());
		analyseRecurise(rootRadicalNode);
	}

	public void travel() {

	}

	private void analyseRecurise(RadicalNode radicalNode) {
		try {
			List<SearchingKanjiObject> lstResult = searchingEngine.search(radicalNode.getRadical());

			for (SearchingKanjiObject item : lstResult) {
				Set<Map.Entry<String, String>> entries = item.getMapComponent().entrySet();
				for (Map.Entry<String, String> entry : entries) {
					RadicalNode node = new RadicalNode(entry.getKey(), entry.getValue());
					if (entry.getKey().equals("") == false) {
						radicalNode.addChildRadicalNode(node);
						analyseRecurise(node);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public WordItem getWordItem() {
		return wordItem;
	}

	public void setWordItem(WordItem wordItem) {
		this.wordItem = wordItem;
	}


}

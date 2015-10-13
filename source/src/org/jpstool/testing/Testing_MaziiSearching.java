package org.jpstool.testing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.jpstool.searching.MaziiSearching;
import org.jpstool.searching.SearchingKanjiObject;
import org.json.JSONException;
import org.junit.Test;

public class Testing_MaziiSearching {

	@Test
	public void test() throws IOException, JSONException {
		MaziiSearching ms = new MaziiSearching();
		List<SearchingKanjiObject> lst = ms.search("可能");
		for (SearchingKanjiObject searchingKanjiObject : lst) {
			System.out.println(searchingKanjiObject);
		}
	}

}

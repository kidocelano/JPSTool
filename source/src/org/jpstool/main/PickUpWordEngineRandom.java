package org.jpstool.main;

import java.util.List;
import java.util.Random;

public class PickUpWordEngineRandom implements PickupWordEnigne{

	@Override
	public WordItem pickUpWord(List<WordItem> listWordItem) {
		Random random = new Random();
		return listWordItem.get(random.nextInt(listWordItem.size()));
	}

}

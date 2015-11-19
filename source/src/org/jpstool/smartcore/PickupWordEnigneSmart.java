package org.jpstool.smartcore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.jpstool.main.PickupWordEnigne;
import org.jpstool.main.WordItem;

public class PickupWordEnigneSmart implements PickupWordEnigne {
	private static enum PickupType {
		NEW_WORD, FAIL_WORD, OLD_WORD
	}

	private static final int CONST_PERCENT_FAIL_WORD = 60;
	private static final int CONST_APPEAR_TIME_NEW = 20;
	private static final int CONST_TOTAL_TIME_RANDOM = 10;

	private File profileFile;
	private ProfileManangement pm;
	private List<ProfileLearning> listProfileLearing;
	private HashMap<PickupType, List<ProfileLearning>> hashMapType;

	private float percentNewWord;
	private float percentFailWord;
	private float percentOldWord;

	public PickupWordEnigneSmart(File profileFile, int percentNewWord, int percentFailWord, int percentOldWord) throws ClassNotFoundException, IOException {
		this.profileFile = profileFile;
		this.percentNewWord = percentNewWord;
		this.percentFailWord = percentFailWord;
		this.percentOldWord = percentOldWord;
		this.profileFile = profileFile;
		init();
		parseType();
	}

	private void init() throws IOException, ClassNotFoundException {
		pm = ProfileManangement.getInstance(profileFile);
		pm = ProfileManangement.getInstance(profileFile);
		if (profileFile.isFile()) {
			pm.load();
		}
		listProfileLearing = pm.getListProfile();
		hashMapType = new HashMap<PickupType, List<ProfileLearning>>();
	}

	private void parseType() {
		float sumTimes;
		final int CONST_INIT_STANDARD = 10;
		float percent;
		for (ProfileLearning pl : listProfileLearing) {
			sumTimes = pl.getKnewCount() + pl.getNotKnewCount();
			if (sumTimes == 0) {
				sumTimes = 1;
			}

			percent = ((float) ((float) pl.getKnewCount() / sumTimes) * 100);

			if (percent < CONST_PERCENT_FAIL_WORD || percent >= CONST_PERCENT_FAIL_WORD && sumTimes < CONST_INIT_STANDARD) {
				getProfilesList(PickupType.FAIL_WORD).add(pl);
			}

			if (sumTimes > CONST_APPEAR_TIME_NEW) {
				getProfilesList(PickupType.OLD_WORD).add(pl);
			} else {
				getProfilesList(PickupType.NEW_WORD).add(pl);
			}
		}
	}

	private List<ProfileLearning> getProfilesList(PickupType type) {
		List<ProfileLearning> tempList = hashMapType.get(type);
		if (tempList == null) {
			tempList = new ArrayList<ProfileLearning>();
			hashMapType.put(type, tempList);
		}
		return tempList;
	}

	private void synchronizedProfile(List<WordItem> listWordItem) {
		for (WordItem wordItem : listWordItem) {
			ProfileLearning pl = pm.get(wordItem);
			if (pl == null) {
				pm.addInfo(wordItem);
			}
		}
	}

	private List<WordItem> getRandomFormList(int times, List<ProfileLearning> profileList) {
		List<WordItem> list = new ArrayList<WordItem>();
		Random random = new Random();
		if (profileList.size() == 0) {
			return list;
		}
		for (int i = 0; i < times; i++) {
			list.add(profileList.get(random.nextInt(profileList.size())).getWord());
		}
		return list;
	}

	@Override
	public WordItem pickUpWord(List<WordItem> listWordItem) {
		if (listWordItem.size() != listProfileLearing.size()) {
			synchronizedProfile(listWordItem);
			parseType();
		}

		float total = percentNewWord + percentFailWord + percentOldWord;
		float realPNewWord = percentNewWord / total;
		float realPFailWord = percentFailWord / total;
		float realPOldWord = percentOldWord / total;

		List<WordItem> resultList = new ArrayList<WordItem>();
		resultList.addAll(getRandomFormList(Math.round(realPNewWord * CONST_TOTAL_TIME_RANDOM), getProfilesList(PickupType.NEW_WORD)));
		resultList.addAll(getRandomFormList(Math.round(realPFailWord * CONST_TOTAL_TIME_RANDOM), getProfilesList(PickupType.FAIL_WORD)));
		resultList.addAll(getRandomFormList(Math.round(realPOldWord * CONST_TOTAL_TIME_RANDOM), getProfilesList(PickupType.OLD_WORD)));

		Random random = new Random();
		return resultList.get(random.nextInt(resultList.size()));
	}
}

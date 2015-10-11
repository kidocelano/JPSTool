package org.jpstool.smartcore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jpstool.main.WordItem;

public class ProfileManangement {
	private static ProfileManangement singleOn;
	private List<ProfileLearning> listProfile;
	private File handleFile;

	public synchronized static ProfileManangement getInstance(File outputFile) {
		if (singleOn == null) {
			singleOn = new ProfileManangement(outputFile);
		}
		return singleOn;
	}

	private ProfileManangement(File outputFile) {
		this.handleFile = outputFile;
		listProfile = new ArrayList<ProfileLearning>();
	}

	public void save() throws IOException {
		FileOutputStream fileOut = new FileOutputStream(handleFile);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(listProfile);
		out.close();
		fileOut.close();
	}

	public ProfileLearning get(WordItem wordItem) {
		int index = listProfile.indexOf(convertToPL(wordItem));

		if (index == -1) {
			return null;
		}

		return listProfile.get(index);
	}

	public List<ProfileLearning> getListProfile() {
		return listProfile;
	}

	public void addInfo(ProfileLearning pl) {
		listProfile.add(pl);
	}

	public void addInfo(WordItem wordItem) {
		listProfile.add(convertToPL(wordItem));
	}

	public void increaseFail(WordItem wordItem) {
		getAndInsert(wordItem).increaseFail();
	}

	public void increaseKnew(WordItem wordItem) {
		getAndInsert(wordItem).inscreaseKnew();
	}

	private ProfileLearning getAndInsert(WordItem wordItem) {
		ProfileLearning pl = get(wordItem);
		if (pl == null) {
			pl = convertToPL(wordItem);
			addInfo(pl);
		}
		return pl;
	}

	private ProfileLearning convertToPL(WordItem wordItem) {
		return new ProfileLearning(wordItem, 0, 0, 0);
	}

	@SuppressWarnings("unchecked")
	public void load() throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(handleFile);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		listProfile = (List<ProfileLearning>) in.readObject();
		in.close();
		fileIn.close();
	}
}

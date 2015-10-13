package org.jpstool.testing;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jpstool.main.WordItem;
import org.jpstool.smartcore.ProfileLearning;
import org.jpstool.smartcore.ProfileManangement;
import org.junit.Test;

public class Testing_Serializing {

	public void test() throws IOException {
		File file = new File("D:/Projects/JPSTool/source/testing_files/a.ser");
		ProfileManangement pm = ProfileManangement.getInstance(file);

		for (int i = 0; i < 10; i++) {
			WordItem wi = new WordItem("漢字" + i, "HÁN VIỆT", "chữ kanji", "かんじ", "");
			pm.addInfo(new ProfileLearning(wi, 5 + i, 3 + i, 2 + i));
		}

		pm.save();
	}

	@Test
	public void load() throws ClassNotFoundException, IOException {
		File file = new File("D:/Project/mine/git/JPSTool/source/profile - Copy.jps");
		ProfileManangement pm = ProfileManangement.getInstance(file);
		pm.load();

		List<ProfileLearning> lst = pm.getListProfile();
		for (ProfileLearning profileLearning : lst) {
			System.out.println(profileLearning);
		}
	}

}

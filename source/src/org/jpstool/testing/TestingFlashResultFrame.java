package org.jpstool.testing;

import org.jpstool.main.FlashResultFrame;
import org.jpstool.main.WordItem;
import org.junit.Test;

public class TestingFlashResultFrame {

	@Test
	public void test() throws InterruptedException {
		FlashResultFrame frf = new FlashResultFrame();
		frf.setVisible(true);
		frf.setDisplayWord(new WordItem("削除", "TRƯỚC TRỪ", "Xoá", "�?��??�?�ょ"));
		Thread.sleep(999999999);
	}

}

package org.jpstool.testing;

import org.jpstool.main.FlashResultFrame;
import org.jpstool.main.WordItem;
import org.junit.Test;

public class TestingFlashResultFrame {

	@Test
	public void test() throws InterruptedException {
		FlashResultFrame frf = new FlashResultFrame();
		frf.setVisible(true);
		frf.setDisplayWord(new WordItem("å‰Šé™¤", "TRÆ¯á»šC TRá»ª", "XoÃ¡", "ã?•ã??ã?˜ã‚‡"));
		Thread.sleep(999999999);
	}

}

package org.jpstool.main;

import java.io.IOException;

import org.jpstool.gui.FlashCardSettingFrame;

public class MainDriven {
	public static void main(String[] args) throws ClassNotFoundException, IOException {

		if (args.length > 0) {
			new FlashCardSettingFrame(args[0]).setVisible(true);

		} else {
			new FlashCardSettingFrame().setVisible(true);
		}
	}
}

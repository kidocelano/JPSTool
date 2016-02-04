package org.jpstool.main;

import java.io.IOException;

import org.jpstool.gui.FlashCardSettingFrame;

public class MainDriven {
	public static void main(String[] args) throws ClassNotFoundException, IOException {

		if (args.length > 0) {
			new FlashCardSettingFrame(args[0], args.length > 1 ? Long.parseLong(args[1]) : -1, args.length > 2 ? true : false).setVisible(true);

		} else {
			new FlashCardSettingFrame().setVisible(true);
		}
	}
}

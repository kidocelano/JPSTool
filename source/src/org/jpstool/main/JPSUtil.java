package org.jpstool.main;

import java.awt.Font;

public class JPSUtil {
	public static class FontType {
		private static String fontName = "MS Gothic";
		private static int style = Font.TRUETYPE_FONT;
		private static int smallSize = 12;
		private static int largeSize = 50;

		public static Font getLargeFont() {
			return new Font(fontName, style, largeSize);
		}

		public static Font getFontBySize(int size) {
			return new Font(fontName, style, size);
		}

		public static Font getFontByStyleSize(int type, int size) {
			return new Font(fontName, type, size);
		}

		public static Font getFont(String fontName, int type, int size) {
			return new Font(fontName, type, size);
		}


		public static Font getSmallFont() {
			return new Font(fontName, style, smallSize);
		}
	}
}

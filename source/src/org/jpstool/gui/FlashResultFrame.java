package org.jpstool.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import org.apache.commons.lang3.StringUtils;
import org.jpstool.main.WordItem;
import org.jpstool.searching.MaziiSearching;
import org.jpstool.searching.SearchingKanjiObject;
import org.jpstool.searching.SearchingWordEngine;

public class FlashResultFrame extends JFrame {
	private static final int COSNT_TEXTFIELD_LENGTH = 10;
	private static final int CONST_LAYOUT_CONTAINER_MARGIN_HOR = 15;
	private static final int CONST_LAYOUT_CONTAINER_MARGIN_VER = 15;

	private JTextField tfSmallKanjiText;
	private JTextField tfLargeKanjiText;
	private JTextField tfHanVietText;
	private JTextField tfHiraganaText;
	private JTextArea taMeaningText;
	private JTextArea taOtherText;
	private JTextArea taAnalyseRadicals;

	private JScrollPane spMeaningText;
	private JScrollPane spOtherText;
	private JScrollPane spAnalyseRadicals;

	private Container container;
	private JPanel panelTop;
	private JPanel panelMiddle;
	private JPanel panelBottom;
	private JPanel panelCenterContainer;

	private List<JTextComponent> listTextComponentScreen;

	public FlashResultFrame() {
		init();
	}

	public FlashResultFrame(WordItem wordItem) {
		init();
		setDisplayWord(wordItem);
	}

	private void init() {
		listTextComponentScreen = new ArrayList<JTextComponent>();
		initScreen();
		setupLayout();
		setReadOnlyAllTextBox(false);
		setLocationRelativeTo(null);
	}

	private void initScreen() {
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.container = this.getContentPane();
	}

	private void setDisplayKanjiText(String kanjiText) {
		tfSmallKanjiText.setText(kanjiText);
		tfLargeKanjiText.setText(kanjiText);
	}

	public void setDisplayWord(WordItem wordItem) {
		setDisplayKanjiText(wordItem.getKanjiWord());
		tfHanVietText.setText(wordItem.getHanVietWord().toUpperCase());
		tfHiraganaText.setText(wordItem.getHiragana());
		taMeaningText.setText(wordItem.getMeaning());
		taOtherText.setText(wordItem.getOtherText());
		setDisplayKanjiRadical(wordItem.getKanjiRadicals());
		setDisplayAnalyseRadicals(wordItem.getKanjiWord());
	}

	private void setDisplayKanjiRadical(LinkedHashMap<String, List<String>> mapKanjiRadicals) {
		Set<Map.Entry<String, List<String>>> entries = mapKanjiRadicals.entrySet();
		StringBuffer sbRadical = new StringBuffer();
		String kanjiDilimiter = " : ";
		String radicalDelimiter = " ; ";
		List<String> listRadicals = new ArrayList<String>();
		for (Map.Entry<String, List<String>> entry : entries) {
			sbRadical.append(entry.getKey());
			sbRadical.append(kanjiDilimiter);
			sbRadical.append(StringUtils.join(entry.getValue(), radicalDelimiter));
			listRadicals.add(sbRadical.toString());
			taAnalyseRadicals.append(sbRadical.toString());
			sbRadical.setLength(0);
		}
	}

	private void setDisplayAnalyseRadicals(String word) {
		SearchingWordEngine searching = new MaziiSearching();
		StringBuffer sb = new StringBuffer();
		try {
			List<SearchingKanjiObject> lst = searching.search(word);
			for (SearchingKanjiObject item : lst) {
				sb.append(item.getKanji());
				sb.append(System.lineSeparator());
				Set<Map.Entry<String, String>> entries = item.getMapComponent().entrySet();
				for (Map.Entry<String, String> entry : entries) {
					sb.append(entry.getKey());
					sb.append(String.format("(%s)", entry.getValue()));
					sb.append(", ");
				}
				sb.append(System.lineSeparator());
				sb.append(item.getMeaning().replaceAll("##", System.lineSeparator()));
				sb.append(System.lineSeparator());
				sb.append(System.lineSeparator());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		taAnalyseRadicals.setText(sb.toString());
		taAnalyseRadicals.setCaretPosition(0);
	}

	private void setReadOnlyAllTextBox(boolean flag) {
		for (JTextComponent jTextComponent : listTextComponentScreen) {
			jTextComponent.setEditable(flag);
		}
	}

	private void createComponents() {
		tfSmallKanjiText = new JTextField(COSNT_TEXTFIELD_LENGTH);
		listTextComponentScreen.add(tfSmallKanjiText);

		tfLargeKanjiText = new JTextField(COSNT_TEXTFIELD_LENGTH);
		listTextComponentScreen.add(tfLargeKanjiText);

		tfHanVietText = new JTextField(COSNT_TEXTFIELD_LENGTH);
		listTextComponentScreen.add(tfHanVietText);

		taMeaningText = new JTextArea();
		listTextComponentScreen.add(taMeaningText);

		tfHiraganaText = new JTextField(COSNT_TEXTFIELD_LENGTH);
		tfHiraganaText.setFont(FontType.getSmallFont());
		listTextComponentScreen.add(tfHiraganaText);

		taOtherText = new JTextArea();
		listTextComponentScreen.add(taOtherText);

		taAnalyseRadicals = new JTextArea();
		taAnalyseRadicals.setFont(taAnalyseRadicals.getFont().deriveFont(25f));
		listTextComponentScreen.add(taAnalyseRadicals);

		spMeaningText = new JScrollPane(taMeaningText);
		spAnalyseRadicals = new JScrollPane(taAnalyseRadicals);
		spOtherText = new JScrollPane(taOtherText);
	}

	private void setupLayout() {
		createComponents();

		tfLargeKanjiText.setFont(FontType.getLargeFont());

		container.setLayout(new BorderLayout(CONST_LAYOUT_CONTAINER_MARGIN_HOR, CONST_LAYOUT_CONTAINER_MARGIN_VER));
		container.add(panelCenterContainer = new JPanel(), BorderLayout.CENTER);
		panelCenterContainer.setBorder(new EmptyBorder(CONST_LAYOUT_CONTAINER_MARGIN_VER, CONST_LAYOUT_CONTAINER_MARGIN_HOR, CONST_LAYOUT_CONTAINER_MARGIN_VER, CONST_LAYOUT_CONTAINER_MARGIN_HOR));

		panelCenterContainer.setLayout(new BoxLayout(panelCenterContainer, BoxLayout.Y_AXIS));
		panelCenterContainer.add(panelTop = new JPanel());
		panelCenterContainer.add(panelMiddle = new JPanel());
		panelCenterContainer.add(panelBottom = new JPanel());

		panelTop.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTop.add(tfSmallKanjiText);
		panelTop.add(tfLargeKanjiText);
		panelTop.add(tfHanVietText);

		panelMiddle.setLayout(new BoxLayout(panelMiddle, BoxLayout.Y_AXIS));
		panelMiddle.add(tfHiraganaText);
		panelMiddle.add(spAnalyseRadicals);
		panelMiddle.add(spMeaningText);
		panelBottom.add(spOtherText);
	}

	public void setKanjiText(String kanjiText) {
		tfSmallKanjiText.setText(kanjiText);
		tfLargeKanjiText.setText(kanjiText);
	}

	public void setHanVietText(String hanVietText) {
		tfHanVietText.setText(hanVietText);
	}

	public void setMeaningText(String meaningText) {
		taMeaningText.setText(meaningText);
	}

	public void setHiraganaText(String hiraganaText) {
		tfHiraganaText.setText(hiraganaText);
	}

	public void setOtherText(String otherText) {
		taOtherText.setText(otherText);
	}

	private static class FontType {
		private static String fontName = "MS Gothic";
		private static int style = Font.TRUETYPE_FONT;
		private static int smallSize = 12;
		private static int largeSize = 50;

		public static Font getLargeFont() {
			return new Font(fontName, style, largeSize);
		}

		public static Font getSmallFont() {
			return new Font(fontName, style, smallSize);
		}
	}
}

package org.jpstool.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import org.jpstool.main.JPSConstant;
import org.jpstool.main.JPSUtil;
import org.jpstool.main.WordItem;
import org.jpstool.searching.SearchingKanjiObject;
import org.jpstool.searching.SearchingKanjiObject.SentenceExample;
import org.jpstool.smartcore.ProfileLearning;
import org.jpstool.smartcore.ProfileManangement;

public class FlashResultFrame extends JFrame {
	private static final int COSNT_TEXTFIELD_LENGTH = 19;
	private static final int CONST_LAYOUT_CONTAINER_MARGIN_HOR = 15;
	private static final int CONST_LAYOUT_CONTAINER_MARGIN_VER = 15;
	private static final float CONST_FONT_TEXT_AREA_STD = 19f;

	private JTextField tfSmallKanjiText;
	private JTextField tfLargeKanjiText;
	private JTextField tfHanVietText;
	private JTextField tfHiraganaText;
	private JTextArea taMeaningText;
	private JTextArea taOtherText;
	private JTextArea taAnalyseRadicals;
	private JTextArea taSimilarKanji;
	private JTextArea taSentenceExample;

	private JScrollPane spMeaningText;
	private JScrollPane spOtherText;
	private JScrollPane spAnalyseRadicals;
	private JScrollPane spSimilarKanji;
	private JScrollPane spSentenceExample;

	private Container container;
	private JPanel panelTop;
	private JPanel panelMiddle;
	private JPanel panelBottom;
	private JPanel panelCenterContainer;
	private JPanel panelRightExample;

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
		setupAction();
	}

	private void initScreen() {
		setSize(1300, 800);
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
		setDisplayOtherText(wordItem);
		setDisplayRadicals(wordItem);
		setSimilarKanji(wordItem);
		setSentenceExample(wordItem);
		reFixSize();
	}

	private void setDisplayOtherText(WordItem wordItem) {
		ProfileLearning pl = ProfileManangement.getInstance(new File("")).get(wordItem);
		if (pl != null) {
			float total = pl.getKnewCount() + pl.getNotKnewCount();
			taOtherText.setText("Knew: " + pl.getKnewCount() + "; Not Knew: " + pl.getNotKnewCount() + "; Understanding: " + ((float) pl.getKnewCount() / total) * 100);
		}
	}

	private void setDisplayRadicals(WordItem wordItem) {
		StringBuffer sb = new StringBuffer();
		try {
			List<SearchingKanjiObject> lst = wordItem.getSearchingKanji();
			if (lst != null) {
				for (SearchingKanjiObject item : lst) {
					sb.append(item.getKanji());
					sb.append(String.format("(%s)", item.getHanViet()));
					sb.append(JPSConstant.CONST_LINE_SEPARATOR);
					sb.append(String.format("訓:%s", item.getKun()));
					sb.append(JPSConstant.CONST_LINE_SEPARATOR);
					sb.append(String.format("音:%s", item.getOn()));
					sb.append(JPSConstant.CONST_LINE_SEPARATOR);
					Set<Map.Entry<String, String>> entries = item.getMapComponent().entrySet();
					for (Map.Entry<String, String> entry : entries) {
						sb.append(entry.getKey());
						sb.append(String.format("(%s)", entry.getValue()));
						sb.append(", ");
					}
					sb.append(JPSConstant.CONST_LINE_SEPARATOR);
					sb.append(item.getMeaning().replaceAll("##", JPSConstant.CONST_LINE_SEPARATOR));
					sb.append(JPSConstant.CONST_LINE_SEPARATOR);
					sb.append(JPSConstant.CONST_LINE_SEPARATOR);
				}
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
		taMeaningText.setFont(taMeaningText.getFont().deriveFont(CONST_FONT_TEXT_AREA_STD));
		listTextComponentScreen.add(taMeaningText);

		taSimilarKanji = new JTextArea();
		taSimilarKanji.setFont(taSimilarKanji.getFont().deriveFont(CONST_FONT_TEXT_AREA_STD));
		listTextComponentScreen.add(taSimilarKanji);

		taSentenceExample = new JTextArea();
		taSentenceExample.setFont(taSentenceExample.getFont().deriveFont(CONST_FONT_TEXT_AREA_STD));
		listTextComponentScreen.add(taSentenceExample);

		tfHiraganaText = new JTextField(COSNT_TEXTFIELD_LENGTH);

		tfHiraganaText.setFont(JPSUtil.FontType.getSmallFont());
		listTextComponentScreen.add(tfHiraganaText);

		taOtherText = new JTextArea();
		listTextComponentScreen.add(taOtherText);

		taAnalyseRadicals = new JTextArea();
		taAnalyseRadicals.setFont(taAnalyseRadicals.getFont().deriveFont(25f));
		listTextComponentScreen.add(taAnalyseRadicals);

		spMeaningText = new JScrollPane(taMeaningText);
		spAnalyseRadicals = new JScrollPane(taAnalyseRadicals);
		spOtherText = new JScrollPane(taOtherText);
		spSimilarKanji = new JScrollPane(taSimilarKanji);
		spSentenceExample = new JScrollPane(taSentenceExample);
	}

	private void setupLayout() {
		createComponents();

		tfLargeKanjiText.setFont(JPSUtil.FontType.getLargeFont());

		container.setLayout(new BorderLayout(CONST_LAYOUT_CONTAINER_MARGIN_HOR, CONST_LAYOUT_CONTAINER_MARGIN_VER));
		container.add(panelCenterContainer = new JPanel(), BorderLayout.CENTER);
		panelCenterContainer.setBorder(new EmptyBorder(CONST_LAYOUT_CONTAINER_MARGIN_VER, CONST_LAYOUT_CONTAINER_MARGIN_HOR, CONST_LAYOUT_CONTAINER_MARGIN_VER,
				CONST_LAYOUT_CONTAINER_MARGIN_HOR));

		panelCenterContainer.setLayout(new BoxLayout(panelCenterContainer, BoxLayout.Y_AXIS));
		panelCenterContainer.add(panelTop = new JPanel());
		panelCenterContainer.add(panelMiddle = new JPanel());
		panelCenterContainer.add(panelBottom = new JPanel());

		container.add(panelRightExample = new JPanel(), BorderLayout.EAST);
		panelRightExample.setLayout(new BoxLayout(panelRightExample, BoxLayout.Y_AXIS));
		panelRightExample.add(spSimilarKanji);
		panelRightExample.add(spSentenceExample);

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

	private void setupAction() {
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); 
		getRootPane().getActionMap().put("Cancel", new AbstractAction() { //$NON-NLS-1$
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
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

	public void setSentenceExample(WordItem wordItem) {
		StringBuilder sbSentence = new StringBuilder();
		List<SearchingKanjiObject> lstKanjiObject = wordItem.getSearchingKanji();
		if (lstKanjiObject == null || lstKanjiObject.size() < 1) {
			return;
		}
		SearchingKanjiObject firstKanjiObject = lstKanjiObject.get(0);
		for (SentenceExample sentence : firstKanjiObject.getListSentence()) {
			sbSentence.append(sentence.getContent());
			sbSentence.append(JPSConstant.CONST_LINE_SEPARATOR);
			sbSentence.append(sentence.getTranscription());
			sbSentence.append(JPSConstant.CONST_LINE_SEPARATOR);
			sbSentence.append(sentence.getMean());
			sbSentence.append(JPSConstant.CONST_LINE_SEPARATOR);
			sbSentence.append(JPSConstant.CONST_LINE_SEPARATOR);
		}
		taSentenceExample.setText(sbSentence.toString());
		taSentenceExample.setCaretPosition(0);
	}

	public void setSimilarKanji(WordItem wordItem) {
		StringBuilder sbSimilarKanji = new StringBuilder();

		List<SearchingKanjiObject> lstKanjiObject = wordItem.getSearchingKanji();
		if (lstKanjiObject == null) {
			return;
		}

		for (SearchingKanjiObject kanjiObject : lstKanjiObject) {
			List<SearchingKanjiObject.SimilarKanji> listExample = kanjiObject.getListSimilarKanji();
			for (SearchingKanjiObject.SimilarKanji examples : listExample) {
				sbSimilarKanji.append(examples.getW());
				sbSimilarKanji.append("-");
				sbSimilarKanji.append(examples.getH());
				sbSimilarKanji.append("-");
				sbSimilarKanji.append(examples.getP());
				sbSimilarKanji.append("-");
				sbSimilarKanji.append(examples.getM());

				sbSimilarKanji.append(JPSConstant.CONST_LINE_SEPARATOR);
			}
			sbSimilarKanji.append(JPSConstant.CONST_LINE_SEPARATOR);
		}

		taSimilarKanji.setText(sbSimilarKanji.toString());
		taSimilarKanji.setCaretPosition(0);
	}

	private void reFixSize() {
		panelRightExample.setPreferredSize(new Dimension(this.getWidth() / 3, taSimilarKanji.getParent().getHeight()));
	}
}
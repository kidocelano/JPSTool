package org.jpstool.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jpstool.searching.MazziSearchingAllWord;
import org.jpstool.searching.SearchingKanjiObject;
import org.jpstool.searching.SearchingWordEngine;

public class FillListWordsFrame extends JFrame {
	private JTextArea taInput;
	private JTextArea taOutput;

	private JScrollPane spInput;
	private JScrollPane spOutput;

	private JPanel containerPanel;
	private JButton btnConvert;

	public FillListWordsFrame() {
		setupLayout();
		bindingAction();
	}

	private void setupLayout() {
		taInput = new JTextArea();
		taOutput = new JTextArea();

		spInput = new JScrollPane(taInput);
		spOutput = new JScrollPane(taOutput);

		btnConvert = new JButton("Convert");

		containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		add(containerPanel);

		containerPanel.add(spInput);
		containerPanel.add(btnConvert);
		containerPanel.add(spOutput);

		setLocationRelativeTo(null);
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void bindingAction() {
		btnConvert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				taOutput.setText(convertText(taInput.getText().split("\n")));
			}
		});
	}

	private String convertText(String[] arrayWord) {
		String strSeparator = System.getProperty("line.separator");
		SearchingWordEngine swe = new MazziSearchingAllWord();
		StringBuffer sbResult = new StringBuffer();
		for (int i = 0; i < arrayWord.length; i++) {
			try {
				List<SearchingKanjiObject> lstKanji = swe.search(arrayWord[i]);
				if (lstKanji.size() == 0) {
					continue;
				}
				SearchingKanjiObject searchingKanjiObj = lstKanji.get(0);
				sbResult.append(String.format("%-20s %-20s %-100s %s", searchingKanjiObj.getKanji(), searchingKanjiObj.getHanViet(), searchingKanjiObj.getMeaning(),
						searchingKanjiObj.getOn()));
				sbResult.append(strSeparator);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sbResult.toString();
	}

	public static void main(String[] args) {
		new FillListWordsFrame().setVisible(true);
	}
}

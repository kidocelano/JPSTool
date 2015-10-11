package org.jpstool.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jpstool.main.WordItem;

public class FlashCardFrame extends JFrame {
	private JTextField tfSmallKanjiText;
	private JButton btnShowLargeKanji;

	private JButton btnKnowThisWord;
	private JButton btnShowResult;
	private Container container;

	private JPanel mainPanel;
	private WordItem currentWordItem;

	public FlashCardFrame() {
		init();
	}

	public FlashCardFrame(WordItem wordItem) {
		init();
		setWord(wordItem);
	}

	private void init() {
		setupLayout();
		bindingAction();

		setSize(400, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		this.pack();
	}

	private void setupLayout() {
		tfSmallKanjiText = new JTextField(7);
		tfSmallKanjiText.setEditable(false);

		btnShowLargeKanji = new JButton("Larger");
		btnKnowThisWord = new JButton("Knew It");
		btnShowResult = new JButton("What's is it ?");

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		this.container = this.getContentPane();
		container.setLayout(new BorderLayout());

		container.add(mainPanel);
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.gridwidth = 2;
		mainPanel.add(tfSmallKanjiText, constraint);

		constraint.gridx = 0;
		constraint.gridy = 1;
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weighty = 3;
		mainPanel.add(btnShowLargeKanji, constraint);

		constraint.gridy = 2;
		constraint.gridwidth = 1;
		mainPanel.add(btnKnowThisWord, constraint);

		constraint.gridx = 1;
		mainPanel.add(btnShowResult, constraint);
	}

	private void bindingAction() {
		btnShowLargeKanji.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Font font = tfSmallKanjiText.getFont();
				tfSmallKanjiText.setFont(font.deriveFont((float) (font.getSize() * 0.3 + font.getSize())));
				pack();
			}
		});

		btnKnowThisWord.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnShowResult.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentWordItem != null) {
					new FlashResultFrame(currentWordItem).setVisible(true);
				} else {
					throw new IllegalStateException("Please set WordItem object for this frame");
				}
			}
		});
	}

	private void setWord(WordItem wordItem) {
		currentWordItem = wordItem;
		tfSmallKanjiText.setText(wordItem.getKanjiWord());
	}

	public static void main(String[] args) {
		new FlashCardFrame().setVisible(true);
	}
}

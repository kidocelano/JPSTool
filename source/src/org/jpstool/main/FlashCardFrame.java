package org.jpstool.main;

import java.awt.Container;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class FlashCardFrame extends JFrame{
	private JTextField tfSmallKanjiText;
	private JTextField tfLargeKanjiText;
	private JButton btnShowLargeKanji;
	
	private JButton btnKnowThisWord;
	private JButton btnShowResult;
	private Container container;
	
	public FlashCardFrame() {
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		tfSmallKanjiText = new JTextField();
		tfLargeKanjiText = new JTextField();
		btnShowLargeKanji = new JButton();
		btnKnowThisWord = new JButton();
		btnShowResult = new JButton();
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.container = this.getContentPane();
		this.container.add(tfSmallKanjiText);
		this.container.add(btnShowLargeKanji);
		this.container.add(tfLargeKanjiText);
	}
}

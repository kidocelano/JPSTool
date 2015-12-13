package org.jpstool.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jpstool.main.JPSConstant;
import org.jpstool.main.LoadWordPlainText;
import org.jpstool.main.LoadWordsEngine;
import org.jpstool.main.LoopMechanic;
import org.jpstool.main.PickUpWordEngineRandom;
import org.jpstool.main.PickupMultipleWordEngine;
import org.jpstool.main.PickupWordEnigne;
import org.jpstool.main.TimerLoopMechanic;
import org.jpstool.main.WordItem;
import org.jpstool.searching.MaziiSearching;
import org.jpstool.searching.SearchingWordEngine;
import org.jpstool.smartcore.PickupWordEnigneSmart;
import org.jpstool.smartcore.ProfileManangement;

public class FlashCardSettingFrame extends JFrame {
	public static class FlashCardSettingDefault {
		public static final int CONST_DEFAULT_INTERVAL = 90;
		public static final int CONST_DEFAULT_PERCENT_NEW_WORD = 20;
		public static final int CONST_DEFAULT_PERCENT_FAIL_WORD = 70;
		public static final int CONST_DEFAULT_PERCENT_OLD_WORD = 10;
		public static final int CONST_DEFAULT_APPEAR_MULTIPLE_WORD = 10;
	}

	private Container container;
	private JPanel mainPanel;
	private JPanel panelPickupSmartOption;
	private JTextField tfKanjiFilePath;
	private JButton btnSelectKanjiFilePath;

	private JTextField tfInterval;

	private JFileChooser fcKanjiFile;

	private ButtonGroup bgPickupMode;
	private JRadioButton rbRandomMode;
	private JRadioButton rbSmartMode;
	private JRadioButton rbMultiWord;

	private JTextField tfSModeNewWord;
	private JTextField tfSModeFailWord;
	private JTextField tfSModeOldWord;

	private JButton btnApply;

	private LoopMechanic loop;
	ProfileManangement pm;

	public FlashCardSettingFrame() throws ClassNotFoundException, IOException {
		init();
		setupLayout();
		initConfig();
		bindingAction();
		pack();
	}

	public FlashCardSettingFrame(String filePath, long interval, boolean isAutoStart) throws ClassNotFoundException, IOException {
		this();
		tfKanjiFilePath.setText(filePath);

		if (interval != -1) {
			tfInterval.setText(interval + "");
		}

		if (isAutoStart) {
			btnApply.doClick();
		}
	}

	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(800, 600);
	}

	private void initConfig() {
		enableComponents(panelPickupSmartOption, false);
		tfInterval.setText(FlashCardSettingDefault.CONST_DEFAULT_INTERVAL + "");
		tfSModeNewWord.setText(FlashCardSettingDefault.CONST_DEFAULT_PERCENT_NEW_WORD + "");
		tfSModeFailWord.setText(FlashCardSettingDefault.CONST_DEFAULT_PERCENT_FAIL_WORD + "");
		tfSModeOldWord.setText(FlashCardSettingDefault.CONST_DEFAULT_PERCENT_OLD_WORD + "");

		rbSmartMode.setSelected(true);
	}

	private void setupLayout() {
		container = this.getContentPane();
		container.setLayout(new BorderLayout());

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.container.add(mainPanel, BorderLayout.CENTER);

		JPanel boxPn1KanjiFilePath = new JPanel(new FlowLayout(FlowLayout.LEFT));
		boxPn1KanjiFilePath.add(new JLabel("Kanji File Path"));
		boxPn1KanjiFilePath.add(tfKanjiFilePath = new JTextField(30));
		boxPn1KanjiFilePath.add(btnSelectKanjiFilePath = new JButton("Open"));

		JPanel boxPn2OptionsInterval = new JPanel(new FlowLayout(FlowLayout.LEFT));
		boxPn2OptionsInterval.add(new JLabel("Interval(s"));
		boxPn2OptionsInterval.add(tfInterval = new JTextField(5));

		JPanel boxPn3OptionsPickup = new JPanel();
		boxPn3OptionsPickup.setLayout(new BoxLayout(boxPn3OptionsPickup, BoxLayout.Y_AXIS));
		rbRandomMode = new JRadioButton("Random");
		rbSmartMode = new JRadioButton("Smart");
		rbMultiWord = new JRadioButton("Multiple Word Appear");
		bgPickupMode = new ButtonGroup();
		bgPickupMode.add(rbRandomMode);
		bgPickupMode.add(rbSmartMode);
		bgPickupMode.add(rbMultiWord);

		boxPn3OptionsPickup.add(rbRandomMode);
		boxPn3OptionsPickup.add(rbSmartMode);
		boxPn3OptionsPickup.add(rbMultiWord);

		panelPickupSmartOption = new JPanel(new GridBagLayout());
		boxPn3OptionsPickup.add(panelPickupSmartOption);
		GridBagConstraints boxPn3Contraints = new GridBagConstraints();

		boxPn3Contraints.gridx = 0;
		boxPn3Contraints.gridy = 0;
		boxPn3OptionsPickup.add(new JLabel("New Words"), boxPn3Contraints);
		boxPn3Contraints.gridx = 1;
		boxPn3OptionsPickup.add(tfSModeNewWord = new JTextField(3), boxPn3Contraints);

		boxPn3Contraints.gridx = 0;
		boxPn3Contraints.gridy = 2;
		boxPn3OptionsPickup.add(new JLabel("Fail Words"), boxPn3Contraints);
		boxPn3Contraints.gridx = 1;
		boxPn3OptionsPickup.add(tfSModeFailWord = new JTextField(3), boxPn3Contraints);

		boxPn3Contraints.gridx = 0;
		boxPn3Contraints.gridy = 3;
		boxPn3OptionsPickup.add(new JLabel("Old Words"), boxPn3Contraints);
		boxPn3Contraints.gridx = 1;
		boxPn3OptionsPickup.add(tfSModeOldWord = new JTextField(3), boxPn3Contraints);

		mainPanel.add(boxPn1KanjiFilePath);
		mainPanel.add(boxPn2OptionsInterval);
		mainPanel.add(boxPn3OptionsPickup);
		mainPanel.add(btnApply = new JButton("Apply"));
	}

	private void bindingAction() {
		btnSelectKanjiFilePath.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fcKanjiFile == null) {
					fcKanjiFile = new JFileChooser();
					fcKanjiFile.setMultiSelectionEnabled(false);
					fcKanjiFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
				}

				int result = fcKanjiFile.showOpenDialog(container);
				if (result == JFileChooser.APPROVE_OPTION) {
					tfKanjiFilePath.setText(fcKanjiFile.getSelectedFile().getAbsolutePath());
				}
			}
		});

		rbSmartMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JRadioButton rb = (JRadioButton) e.getSource();
				boolean isEnable;
				if (rb.isSelected()) {
					isEnable = true;
				} else {
					isEnable = false;
				}
				enableComponents(panelPickupSmartOption, isEnable);
			}
		});

		btnApply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					File kanjiFile = new File(tfKanjiFilePath.getText());
					int intervalValue = Integer.parseInt(tfInterval.getText());

					PickupWordEnigne pickUpWordEngine;
					if (rbSmartMode.isSelected()) {
						int percentNewWord = Integer.parseInt(tfSModeNewWord.getText());
						int percentFailWord = Integer.parseInt(tfSModeFailWord.getText());
						int percentOldWord = Integer.parseInt(tfSModeOldWord.getText());
						pickUpWordEngine = new PickupWordEnigneSmart(new File(JPSConstant.CONST_FILE_PATH_PROFILE), percentNewWord, percentFailWord, percentOldWord);

					} else if (rbRandomMode.isSelected()) {
						pickUpWordEngine = new PickUpWordEngineRandom();

					} else {
						pickUpWordEngine = new PickupMultipleWordEngine(FlashCardSettingDefault.CONST_DEFAULT_APPEAR_MULTIPLE_WORD);
					}

					LoadWordsEngine loadWordsEngine = new LoadWordPlainText();

					if (loop != null) {
						loop.stopLoop();
					}

					loop = new TimerLoopMechanic(intervalValue, pickUpWordEngine, loadWordsEngine, kanjiFile);
					loop.loop(new LoopMechanic.LookMechanicCallBack() {

						@Override
						public void callBack(WordItem wordItem) {
							try {
								SearchingWordEngine enigne = new MaziiSearching();
								if (rbMultiWord.isSelected() == false) {
									wordItem.setSearchingKanji(enigne.search(wordItem.getKanjiWord()));
								}

							} catch (Exception e2) {
								e2.printStackTrace();
							}

							new FlashCardFrame(wordItem, !rbMultiWord.isSelected()).setVisible(true);
						}
					});

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
	}

	private void enableComponents(Container container, boolean enable) {
		Component[] components = container.getComponents();
		for (Component component : components) {
			component.setEnabled(enable);
			if (component instanceof Container) {
				enableComponents((Container) component, enable);
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		new FlashCardSettingFrame().setVisible(true);
	}
}

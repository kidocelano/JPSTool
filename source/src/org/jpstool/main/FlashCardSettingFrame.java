package org.jpstool.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FlashCardSettingFrame extends JFrame {
	public static class FlashCardSettingDefault {
		public static final int CONST_DEFAULT_INTERVAL = 90;
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

	private JTextField tfSModeNewWord;
	private JTextField tfSModeFailWord;
	private JTextField tfSModeOldWord;

	private JButton btnApply;

	public FlashCardSettingFrame() {
		init();
		setupLayout();
		bindingAction();
		pack();
	}

	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 400);
	}

	private void setupLayout() {
		container = this.getContentPane();
		container.setLayout(new BorderLayout());

		mainPanel = new JPanel(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.container.add(mainPanel, BorderLayout.CENTER);

		JPanel boxPn1KanjiFilePath = new JPanel(new FlowLayout(FlowLayout.LEFT));
		boxPn1KanjiFilePath.add(new JLabel("Kanji File Path"));
		boxPn1KanjiFilePath.add(tfKanjiFilePath = new JTextField(30));
		boxPn1KanjiFilePath.add(btnSelectKanjiFilePath = new JButton("Open"));

		JPanel boxPn2OptionsInterval = new JPanel(new FlowLayout(FlowLayout.LEFT));
		boxPn2OptionsInterval.add(new JLabel("Interval(s"));
		boxPn2OptionsInterval.add(tfInterval = new JTextField(5));

		JPanel boxPn3OptionsPickup = new JPanel(new BoxLayout(null, BoxLayout.Y_AXIS));
		rbRandomMode = new JRadioButton("Random");
		rbSmartMode = new JRadioButton("Smart");
		bgPickupMode.add(rbRandomMode);
		bgPickupMode.add(rbSmartMode);

		boxPn3OptionsPickup.add(rbRandomMode);
		boxPn3OptionsPickup.add(rbSmartMode);

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
				panelPickupSmartOption.setEnabled(isEnable);
			}
		});
		
		btnApply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}

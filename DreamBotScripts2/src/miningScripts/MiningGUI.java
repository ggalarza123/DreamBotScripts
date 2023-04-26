package miningScripts;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Component;

public class MiningGUI extends JFrame {

	private JPanel contentPane;
	private JTextField minutesTextField;
	private JComboBox locationBox;
	private ButtonGroup yesNoAntiBotGroup, yesNoBreaksGroup, yesNoEatGroup, yesNoTimeGroup, yesNoHopWorldsGroup,
			worldBG;
	private JRadioButton yesGemsRadioButton;
	private JRadioButton yesBankingRadioButton;
	private JRadioButton noBankingRadioButton;
	private JRadioButton noGemsRadioButton;
	private JRadioButton yesTimeRadioButton;
	private JRadioButton noTimeRadioButton;
	private JButton startButton;
	private JLabel messageTextField;
	private JComboBox oreBox;
	private JPanel panel_6;
	private JLabel lblNewLabel_6;
	private JRadioButton yesAntiBotRadioButton;
	private JRadioButton noAntiBotRadioButton;
	private JPanel panel_7;
	private JLabel lblNewLabel_7;
	private JRadioButton yesHopWorldsRB;
	private JRadioButton noHopWorldsRB;
	private JLabel worldLabel_8;
	private JRadioButton p2pRB;
	private JRadioButton f2pRB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiningGUI frame = new MiningGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MiningGUI() {

		setTitle("Ruthless Mining");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		Panel panel_1 = new Panel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(panel_1);

		JLabel lblNewLabel = new JLabel("Location:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblNewLabel);

		locationBox = new JComboBox();
		locationBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		locationBox.setModel(new DefaultComboBoxModel(
				new String[] { "Lumbridge Swamp Mine", "Al Kharid Mine", "Al Kharid Iron Power Mine" }));
		panel_1.add(locationBox);
		locationBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (locationBox.getSelectedItem().toString().equals("Al Kharid Iron Power Mine")) {
					panel_7.setVisible(true);
					oreBox.setSelectedIndex(2);
					f2pRB.setVisible(true);
					p2pRB.setVisible(true);
					worldLabel_8.setVisible(true);
				} else {
					panel_7.setVisible(false);
					f2pRB.setVisible(false);
					p2pRB.setVisible(false);
					worldLabel_8.setVisible(false);
				}

			}

		});

		panel_7 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_7.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_7);

		lblNewLabel_7 = new JLabel("Hop worlds if power mining has other player:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_7.add(lblNewLabel_7);

		yesHopWorldsRB = new JRadioButton("Yes");
		yesHopWorldsRB.setSelected(true);
		panel_7.add(yesHopWorldsRB);

		noHopWorldsRB = new JRadioButton("No");
		panel_7.add(noHopWorldsRB);
		yesNoHopWorldsGroup = new ButtonGroup();
		yesNoHopWorldsGroup.add(noHopWorldsRB);
		yesNoHopWorldsGroup.add(yesHopWorldsRB);
		panel_7.setVisible(false);

		panel_6 = new JPanel();
		contentPane.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		lblNewLabel_6 = new JLabel("Antibot:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_6.add(lblNewLabel_6);

		yesAntiBotRadioButton = new JRadioButton("Yes");
		yesAntiBotRadioButton.setSelected(true);
		yesAntiBotRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_6.add(yesAntiBotRadioButton);

		noAntiBotRadioButton = new JRadioButton("No");
		noAntiBotRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_6.add(noAntiBotRadioButton);

		Panel panel_2 = new Panel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("Mine:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_1);

		oreBox = new JComboBox();
		oreBox.setModel(new DefaultComboBoxModel(new String[] { "Copper", "Tin", "Iron", "Silver ore", "Coal",
				"Gold ore", "Mithril ore", "Adamntite" }));
		panel_2.add(oreBox);
		yesNoAntiBotGroup = new ButtonGroup();
		yesNoAntiBotGroup.add(noAntiBotRadioButton);
		yesNoAntiBotGroup.add(yesAntiBotRadioButton);

		worldLabel_8 = new JLabel("Worlds:");
		worldLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_6.add(worldLabel_8);
		worldLabel_8.setVisible(false);

		p2pRB = new JRadioButton("Members only");
		p2pRB.setSelected(true);
		p2pRB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_6.add(p2pRB);

		f2pRB = new JRadioButton("F2P only");
		f2pRB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_6.add(f2pRB);
		worldBG = new ButtonGroup();
		worldBG.add(f2pRB);
		worldBG.add(p2pRB);
		f2pRB.setVisible(false);
		p2pRB.setVisible(false);

		Panel panel = new Panel();
		FlowLayout flowLayout_2 = (FlowLayout) panel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel);

		JLabel lblNewLabel_2 = new JLabel("Banking:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_2);

		yesBankingRadioButton = new JRadioButton("Yes");
		yesBankingRadioButton.setToolTipText("Does not logout, only idles");
		yesBankingRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(yesBankingRadioButton);

		noBankingRadioButton = new JRadioButton("No");
		noBankingRadioButton.setSelected(true);
		noBankingRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(noBankingRadioButton);

		yesNoBreaksGroup = new ButtonGroup();
		yesNoBreaksGroup.add(yesBankingRadioButton);
		yesNoBreaksGroup.add(noBankingRadioButton);

		Panel panel_3 = new Panel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_3);

		JLabel lblNewLabel_3 = new JLabel("Keep gems:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblNewLabel_3);

		yesGemsRadioButton = new JRadioButton("Yes");
		yesGemsRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(yesGemsRadioButton);

		noGemsRadioButton = new JRadioButton("No");
		noGemsRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		noGemsRadioButton.setSelected(true);
		panel_3.add(noGemsRadioButton);
		yesNoEatGroup = new ButtonGroup();
		yesNoEatGroup.add(yesGemsRadioButton);
		yesNoEatGroup.add(noGemsRadioButton);

		Panel panel_4 = new Panel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_4.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_4);

		JLabel lblNewLabel_4 = new JLabel("Stop script after:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_4);

		minutesTextField = new JTextField();
		minutesTextField.setForeground(new Color(0, 0, 0));
		minutesTextField.setBorder(new LineBorder(new Color(0, 0, 0)));
		minutesTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		minutesTextField.setEditable(false);
		panel_4.add(minutesTextField);
		minutesTextField.setColumns(5);

		JLabel lblNewLabel_5 = new JLabel("minutes.");
		lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_5);

		yesTimeRadioButton = new JRadioButton("Yes");
		panel_4.add(yesTimeRadioButton);

		noTimeRadioButton = new JRadioButton("No timer");
		noTimeRadioButton.setSelected(true);
		panel_4.add(noTimeRadioButton);

		yesNoTimeGroup = new ButtonGroup();
		yesNoTimeGroup.add(yesTimeRadioButton);
		yesNoTimeGroup.add(noTimeRadioButton);

		Panel panel_5 = new Panel();
		contentPane.add(panel_5);

		startButton = new JButton("START");
		startButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startButton.setForeground(Color.BLACK);
		startButton.setBackground(Color.WHITE);
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (yesBankingRadioButton.isSelected()) {
					Mining.banking = true;
				} else
					Mining.banking = false;
				if (yesGemsRadioButton.isSelected()) {
					Mining.keepGems = true;
				} else {
					Mining.keepGems = false;
				}
				if (noAntiBotRadioButton.isSelected()) {
					Mining.antiBot = false;
				} else {
					Mining.antiBot = true;
				}
				if (yesHopWorldsRB.isSelected()) {
					Mining.hopWorlds = true;
				} else {
					Mining.hopWorlds = false;
				}
				if (p2pRB.isSelected()) {
					Mining.membersOnly = true;
				} else {
					Mining.membersOnly = false;
				}
				Mining.locationIndex = locationBox.getSelectedIndex();
				Mining.rocksIndex = oreBox.getSelectedIndex();
				if (noTimeRadioButton.isSelected()) {
					Mining.scriptIsTimed = false;
					Mining.startLoop = true;
					dispose();
				} else {
					try {
						String timeInput = minutesTextField.getText();
						int time = Integer.parseInt(timeInput);
						Mining.time = time;
						Mining.startTime = System.nanoTime();
						Mining.scriptIsTimed = true;
						Mining.startLoop = true;
						// dispose of window
						dispose();
					} catch (Exception e1) {
						messageTextField.setText("Time must be a whole number.");
						messageTextField.setVisible(true);
					}
				}

			}

		});
		panel_5.add(startButton);

		messageTextField = new JLabel();
		messageTextField.setVisible(false);
		messageTextField.setText("Time must be a whole number");
		messageTextField.setForeground(Color.BLACK);
		messageTextField.setEnabled(false);
		messageTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_5.add(messageTextField);
		yesTimeRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				minutesTextField.setEditable(true);
			}

		});
		noTimeRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				minutesTextField.setEditable(false);
			}

		});

	}

	public boolean eatFood() {
		if (yesGemsRadioButton.isSelected()) {
			return true;
		}
		return false;
	}

	public boolean takeBreaks() {
		if (noBankingRadioButton.isSelected()) {
			return false;
		}
		return true;
	}

}

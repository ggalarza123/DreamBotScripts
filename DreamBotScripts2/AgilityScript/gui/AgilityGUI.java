package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import org.dreambot.api.script.ScriptManager;

import agilitycontrollers.RuthlessAgilityMain;

public class AgilityGUI extends JFrame {

	private JPanel contentPane;
	private JTextField foodTextField;
	private JTextField minutesTextField;
	private JComboBox locationBox;
	private ButtonGroup yesNoMarksGroup, yesNoBreaksGroup, yesNoEatGroup, yesNoTimeGroup;
	private JRadioButton yesEatRadioButton;
	private JRadioButton yesMarkRadioButton;
	private JRadioButton noMarksRadioButton;
	private JRadioButton yesTakesBreaksRadioButton;
	private JRadioButton noTakesBreaksRadioButton;
	private JRadioButton noEatRadioButton;
	private JRadioButton yesTimeRadioButton;
	private JRadioButton noTimeRadioButton;
	private JButton startButton;
	private JLabel messageTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgilityGUI frame = new AgilityGUI();
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
	public AgilityGUI() {

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				ScriptManager.getScriptManager().stop();
			}
		});
		setTitle("Ruthless Agility");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		locationBox.setModel(new DefaultComboBoxModel(new String[] { "Gnome Stronghold Agility Course",
				"Draynor Village Rooftop Course", "Al Kharid Rooftop Course", "Varrock Rooftop Course",
				"Canifis Rooftop Course", "Falador Rooftop Course", "Seer's Agility Course",
				"Pollnivneach Rooftop Course", "Rellekka Rooftop Course" }));
		panel_1.add(locationBox);

		Panel panel_2 = new Panel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("Pick up marks:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_1);

		yesMarkRadioButton = new JRadioButton("Yes");
		yesMarkRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yesMarkRadioButton.setSelected(true);
		panel_2.add(yesMarkRadioButton);

		noMarksRadioButton = new JRadioButton("No");
		noMarksRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(noMarksRadioButton);
		yesNoMarksGroup = new ButtonGroup();
		yesNoMarksGroup.add(yesMarkRadioButton);
		yesNoMarksGroup.add(noMarksRadioButton);

		Panel panel = new Panel();
		FlowLayout flowLayout_2 = (FlowLayout) panel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel);

		JLabel lblNewLabel_2 = new JLabel("Take breaks:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_2);

		yesTakesBreaksRadioButton = new JRadioButton("Yes");
		yesTakesBreaksRadioButton.setToolTipText("Does not logout, only idles");
		yesTakesBreaksRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yesTakesBreaksRadioButton.setSelected(true);
		panel.add(yesTakesBreaksRadioButton);

		noTakesBreaksRadioButton = new JRadioButton("No");
		noTakesBreaksRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(noTakesBreaksRadioButton);

		yesNoBreaksGroup = new ButtonGroup();
		yesNoBreaksGroup.add(yesTakesBreaksRadioButton);
		yesNoBreaksGroup.add(noTakesBreaksRadioButton);

		Panel panel_3 = new Panel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_3);

		JLabel lblNewLabel_3 = new JLabel("Eat Food:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblNewLabel_3);

		yesEatRadioButton = new JRadioButton("Yes");
		yesEatRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(yesEatRadioButton);

		noEatRadioButton = new JRadioButton("No");
		noEatRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		noEatRadioButton.setSelected(true);
		panel_3.add(noEatRadioButton);
		yesNoEatGroup = new ButtonGroup();
		yesNoEatGroup.add(yesEatRadioButton);
		yesNoEatGroup.add(noEatRadioButton);

		noEatRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				foodTextField.setEditable(false);
			}
		});
		yesEatRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				foodTextField.setEditable(true);

			}
		});

		foodTextField = new JTextField();
		foodTextField.setForeground(new Color(0, 0, 0));
		foodTextField.setBorder(new LineBorder(new Color(0, 0, 0)));
		foodTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		foodTextField.setToolTipText("If yes, food name here.");
		foodTextField.setEditable(false);
		panel_3.add(foodTextField);
		foodTextField.setColumns(10);

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
		minutesTextField.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("minutes.");
		lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_5);

		yesTimeRadioButton = new JRadioButton("Yes");
		panel_4.add(yesTimeRadioButton);

		noTimeRadioButton = new JRadioButton("No");
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
				if (yesEatRadioButton.isSelected() && foodTextField.getText().isEmpty()) {
					messageTextField.setText("Missing fields.");
					messageTextField.setVisible(true);
				} else if (yesTimeRadioButton.isSelected() && minutesTextField.getText().isEmpty()) {
					messageTextField.setText("Missing fields.");
					messageTextField.setVisible(true);
				} else {
					if (yesEatRadioButton.isSelected()) {
						RuthlessAgilityMain.eatFood = true;
						RuthlessAgilityMain.food = foodTextField.getText();
					} else {
						RuthlessAgilityMain.eatFood = false;
					}
					if (yesTakesBreaksRadioButton.isSelected()) {
						RuthlessAgilityMain.takeBreaks = true;

					} else {
						RuthlessAgilityMain.takeBreaks = false;
					}
					if (yesMarkRadioButton.isSelected()) {
						RuthlessAgilityMain.collectMarks = true;
					} else {
						RuthlessAgilityMain.collectMarks = false;
					}
					String location = locationBox.getSelectedItem().toString();
					RuthlessAgilityMain.course = location;
					if (noTimeRadioButton.isSelected()) {
						RuthlessAgilityMain.scriptIsTimed = false;
						RuthlessAgilityMain.startLoop = true;
						dispose();
					} else {
						try {
							String timeInput = minutesTextField.getText();
							int time = Integer.parseInt(timeInput);
							RuthlessAgilityMain.time = time;
							RuthlessAgilityMain.startTime = System.nanoTime();
							RuthlessAgilityMain.scriptIsTimed = true;
							RuthlessAgilityMain.startLoop = true;
							// dispose of window
							dispose();
						} catch (Exception e1) {
							messageTextField.setText("Time must be a whole number.");
							messageTextField.setVisible(true);
							System.out.println("caught string as integer input");
						}
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
				minutesTextField.setEditable(true);
			}

		});
		noTimeRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				minutesTextField.setEditable(false);
			}

		});

	}

}

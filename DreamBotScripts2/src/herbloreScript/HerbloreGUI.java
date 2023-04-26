package herbloreScript;

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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import org.dreambot.api.script.ScriptManager;

public class HerbloreGUI extends JFrame {

	private JPanel contentPane;
	private JTextField minutesTextField;
	private JComboBox<?> herbToCleanBox;
	private ButtonGroup yesNoAntiBotGroup, yesNoUseGeGroup, yesNoAutoLvlGroup, yesNoTimeGroup, yesNoHopWorldsGroup;
	private JRadioButton yesAutoLvlRB;
	private JRadioButton yesUseGeRB;
	private JRadioButton noUseGeRB;
	private JRadioButton noAutoLvlRB;
	private JRadioButton yesTimeRadioButton;
	private JRadioButton noTimeRadioButton;
	private JButton startButton;
	private JLabel messageTextField;
	private JComboBox<?> herbloreToDoBox;
	private JPanel panel_6;
	private JLabel lblNewLabel_6;
	private JRadioButton yesAntiBotRadioButton;
	private JRadioButton noAntiBotRadioButton;
	String[] grimyHerbs = { "Grimy guam leaf", "Grimy marrentill", "Grimy tarromin", "Grimy harralander",
			"Grimy ranarr weed", "Grimy toadflax", "Grimy irit leaf", "Grimy avantoe", "Grimy kwuarm",
			"Grimy snapdragon", "Grimy cadentine", "Grimy lantadyme", "Grimy dwarf weed", "Grimy torstol",
			"Grimy Cadantine" };
	String[] cleanHerbs = { "Guam leaf", "Marrentill", "Tarromin", "Harralander", "Ranarr weed", "Toadflax",
			"Irit leaf", "Avantoe", "Kwuarm", "Snapdragon", "Cadentine", "Lantadyme", "Dwarf weed", "Torstol",
			"Cadantine" };
	String[] unfPotions = { "Guam potion (unf)", "Marrentill potion (unf)", "Tarromin potion (unf)",
			"Harralander potion (unf)", "Ranarr potion (unf)", "Toadflax potion (unf)", "Irit potion (unf)",
			"Avantoe potion (unf)", "Kwuarm potion (unf)", "Snapdragon potion (unf)", "Cadantine potion (unf)",
			"Lantadyme potion (unf)", "Dwarf weed potion (unf)", "Torstol potion (unf)",
			"Cadantine blood potion (unf)" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HerbloreGUI frame = new HerbloreGUI();
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
	public HerbloreGUI() {

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				ScriptManager.getScriptManager().stop();
			}
		});
		setTitle("Ruthless Herblore");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		Panel panel_2 = new Panel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("Herblore to do:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_1);

		herbloreToDoBox = new JComboBox();
		herbloreToDoBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		herbloreToDoBox.setModel(
				new DefaultComboBoxModel(new String[] { "Clean herbs", "Make unfinished potions", "Make potions" }));
		panel_2.add(herbloreToDoBox);

		Panel panel_1 = new Panel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(panel_1);

		JLabel lblNewLabel = new JLabel("Herb to Clean");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblNewLabel);

		herbToCleanBox = new JComboBox();
		herbToCleanBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		herbToCleanBox.setModel(new DefaultComboBoxModel(grimyHerbs));
		panel_1.add(herbToCleanBox);
		herbToCleanBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

			}

		});
		yesNoHopWorldsGroup = new ButtonGroup();

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
		yesNoAntiBotGroup = new ButtonGroup();
		yesNoAntiBotGroup.add(noAntiBotRadioButton);
		yesNoAntiBotGroup.add(yesAntiBotRadioButton);

		Panel panel = new Panel();
		FlowLayout flowLayout_2 = (FlowLayout) panel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel);

		JLabel lblNewLabel_2 = new JLabel("Auto use Grand Exchange");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_2);

		yesUseGeRB = new JRadioButton("Yes");
		yesUseGeRB.setToolTipText("Does not logout, only idles");
		yesUseGeRB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(yesUseGeRB);

		noUseGeRB = new JRadioButton("No");
		noUseGeRB.setSelected(true);
		noUseGeRB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(noUseGeRB);

		yesNoUseGeGroup = new ButtonGroup();
		yesNoUseGeGroup.add(yesUseGeRB);
		yesNoUseGeGroup.add(noUseGeRB);

		Panel panel_3 = new Panel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_3);

		JLabel lblNewLabel_3 = new JLabel("Auto level:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblNewLabel_3);

		yesAutoLvlRB = new JRadioButton("Yes");
		yesAutoLvlRB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(yesAutoLvlRB);

		noAutoLvlRB = new JRadioButton("No");
		noAutoLvlRB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		noAutoLvlRB.setSelected(true);
		panel_3.add(noAutoLvlRB);
		yesNoAutoLvlGroup = new ButtonGroup();
		yesNoAutoLvlGroup.add(yesAutoLvlRB);
		yesNoAutoLvlGroup.add(noAutoLvlRB);

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
				minutesTextField.setText("");
				minutesTextField.setEditable(false);
			}

		});
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (noAntiBotRadioButton.isSelected()) {
					Herblore.antiBot = false;
				} else {
					Herblore.antiBot = true;
				}
				if (yesUseGeRB.isSelected()) {
					Herblore.useGe = true;
				} else {
					Herblore.useGe = false;
				}
				Herblore.herbloreToDo = herbloreToDoBox.getSelectedItem().toString();
				Herblore.herbToClean = herbToCleanBox.getSelectedItem().toString();
				Herblore.cleanHerb = cleanHerbs[herbToCleanBox.getSelectedIndex()];
				Herblore.unfPotion = unfPotions[herbToCleanBox.getSelectedIndex()];
				if (noTimeRadioButton.isSelected()) {
					Herblore.scriptIsTimed = false;
					Herblore.startLoop = true;
					dispose();
				} else {
					try {
						String timeInput = minutesTextField.getText();
						int time = Integer.parseInt(timeInput);
						Herblore.time = time;
						Herblore.startTime = System.nanoTime();
						Herblore.scriptIsTimed = true;
						Herblore.startLoop = true;
						// dispose of window
						dispose();
					} catch (Exception e1) {
						messageTextField.setText("Time must be a whole number.");
						messageTextField.setVisible(true);
					}
				}

			}

		});

	}

}

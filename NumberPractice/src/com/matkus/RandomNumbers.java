package com.matkus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class RandomNumbers extends JFrame {

	// Panel and text fields
	private JPanel contentPane;
	private JTextField txtNumberDisplay;
	private JTextField txtCloseWindow;
	private JTextField txtStartInstruction;
	private JTextField txtWhatNumber;
	
	// variable for number randomizer
	String numberString;
	private int min, max;
	
	// variable for moving the undecorated window
	private int initPosX;
	private int initPosY;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// Activating an undecorated frame and making it visible
					RandomNumbers frame = new RandomNumbers();
					frame.setUndecorated(true);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creating the frame and its components in a class constructor
	 * 
	 */
	public RandomNumbers() {

		// Frame - main window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 522);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Panel to display numbers
		JPanel panelNumberDisplay = new JPanel();
		panelNumberDisplay.setBackground(Color.DARK_GRAY);
		panelNumberDisplay.setBounds(0, 33, 469, 450);
		contentPane.add(panelNumberDisplay);
		panelNumberDisplay.setLayout(null);

		// number display
		txtNumberDisplay = new JTextField();
		txtNumberDisplay.setForeground(new Color(65, 105, 225));
		txtNumberDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumberDisplay.setBackground(Color.DARK_GRAY);
		txtNumberDisplay.setEditable(false);
		txtNumberDisplay.setFont(new Font("Tahoma", Font.BOLD, 99));
		txtNumberDisplay.setBounds(10, 101, 449, 229);
		txtNumberDisplay.setText("X");
		txtNumberDisplay.setBorder(new LineBorder(Color.DARK_GRAY));
		txtNumberDisplay.setColumns(15);
		panelNumberDisplay.add(txtNumberDisplay);

		// Text label for the number
		txtWhatNumber = new JTextField();
		txtWhatNumber.setText("What number is this?");
		txtWhatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtWhatNumber.setForeground(new Color(255, 165, 0));
		txtWhatNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtWhatNumber.setEditable(false);
		txtWhatNumber.setColumns(15);
		txtWhatNumber.setBorder(new LineBorder(Color.DARK_GRAY));
		txtWhatNumber.setBackground(Color.DARK_GRAY);
		txtWhatNumber.setBounds(10, 11, 436, 50);
		panelNumberDisplay.add(txtWhatNumber);

		//creating new range window 
		RangeSelection range1 = new RangeSelection();
		//setting undecorated window in range window popup
		range1.setUndecorated(true);
		
		// Activating new button to generate random number
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//assigning result to a string
				numberString = String.valueOf(selectNumber(range1.getMin(), range1.getMax()));
				//displaying string 
				txtNumberDisplay.setText(numberString);
				
				/* Just for testing
				 
				System.out.println("Min is:" + range1.getMin());
				System.out.println("Max is:" + range1.getMax());
				
				*/
				

			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnStart.setMnemonic(KeyEvent.VK_ENTER);
		btnStart.setBackground(new Color(241, 57, 83));
		btnStart.setForeground(Color.WHITE);
		btnStart.setBounds(479, 430, 254, 42);
		contentPane.add(btnStart);

		// Button for closing a window
		txtCloseWindow = new JTextField();
		txtCloseWindow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				System.exit(0);
			}
		});
		txtCloseWindow.setText("X");
		txtCloseWindow.setHorizontalAlignment(SwingConstants.CENTER);
		txtCloseWindow.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCloseWindow.setEditable(false);
		txtCloseWindow.setColumns(15);
		txtCloseWindow.setBackground(Color.WHITE);
		txtCloseWindow.setForeground(new Color(241, 57, 83));
		txtCloseWindow.setBorder(null);
		txtCloseWindow.setBounds(705, 0, 38, 33);
		contentPane.add(txtCloseWindow);

		// Panel for moving the window that is undecorated
		JPanel panelDragWindow = new JPanel();
		panelDragWindow.setBackground(SystemColor.windowBorder);
		panelDragWindow.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// local variable - initial position of the mouse(=window)
				initPosX = e.getX();
				initPosY = e.getY();
			}
		});

		panelDragWindow.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e1) {

				// local variable - getting mouse position and moving window to that position
				int currPosx = e1.getXOnScreen();
				int currPosy = e1.getYOnScreen();
				RandomNumbers.this.setLocation(currPosx - initPosX, currPosy - initPosY);

			}
		});
		panelDragWindow.setBounds(0, 0, 703, 33);
		contentPane.add(panelDragWindow);

		// Text instructions for the user to start the program
		txtStartInstruction = new JTextField();
		txtStartInstruction.setText("Press \"Start\" button to select a number");
		txtStartInstruction.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartInstruction.setForeground(new Color(65, 105, 225));
		txtStartInstruction.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtStartInstruction.setEditable(false);
		txtStartInstruction.setColumns(15);
		txtStartInstruction.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtStartInstruction.setBackground(Color.WHITE);
		txtStartInstruction.setBounds(479, 381, 254, 50);
		contentPane.add(txtStartInstruction);
		
		//Button to edit range of values ( min and max) by the user
		JButton btnSelectRange = new JButton("Select range");
		btnSelectRange.setBackground(new Color(176, 196, 222));
		btnSelectRange.setForeground(Color.WHITE);
		btnSelectRange.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSelectRange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Opening window to select range:
				range1.displayCurrentMinMax(); 
				
				range1.setVisible(true);

			}
		});
		btnSelectRange.setBounds(479, 89, 254, 25);
		contentPane.add(btnSelectRange);
		
		//Information about the initial range set in the program
		JTextArea txtrInitialRange = new JTextArea();
		txtrInitialRange.setColumns(1);
		txtrInitialRange.setEditable(false);
		txtrInitialRange.setRows(2);
		txtrInitialRange.setLineWrap(true);
		String str = "Initial range is set from " + range1.getMin() + " to " + range1.getMax() + ".\nClick below to select new range";
		txtrInitialRange.setText(str);		
		txtrInitialRange.setBounds(478, 44, 241, 44);
		contentPane.add(txtrInitialRange);
		
	}
	
	
	// function to generate random numbers in the specified range.
	private int selectNumber(int min, int max) {

		double z = (Math.random() * ((max - min) + 1)) + min;

		return (int) z;
	}
}





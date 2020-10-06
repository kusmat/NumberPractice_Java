package com.matkus;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class RangeSelection extends JFrame {

	// Panel and text fields
	private JPanel contentPane;
	private JTextField textMinimum;
	private JTextField textMaximum;
	private JTextField textClosePopup;

	// variables
	private int min = 1, max = 100;
	private int initPosX, initPosY;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RangeSelection frame = new RangeSelection();
					frame.setUndecorated(true);
					frame.setVisible(false);
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

	public RangeSelection() {

		// window properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Panel properties
		JPanel panelRangeSelection = new JPanel();
		panelRangeSelection.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelRangeSelection.setBackground(Color.WHITE);
		panelRangeSelection.setBounds(0, 0, 434, 261);
		contentPane.add(panelRangeSelection);
		panelRangeSelection.setLayout(null);

		// Label - enter new range
		JLabel lblNewRange = new JLabel("Enter new range below or cancel.");
		lblNewRange.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewRange.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewRange.setBounds(10, 55, 404, 24);
		panelRangeSelection.add(lblNewRange);

		// Button to accept the change in range
		JButton btnAccept = new JButton("Accept");
		btnAccept.setBackground(new Color(176, 196, 222));
		btnAccept.setForeground(Color.WHITE);
		btnAccept.addActionListener(new ActionListener() {
			
		ValidateNumber min;
		ValidateNumber max;
		
			public void actionPerformed(ActionEvent arg0) {

				/*
				 * JUST FOR TESTING 
				 * System.out.println(random.getMax());
				 * System.out.println(Integer.valueOf(textMinimum.getText()));
				 * System.out.println(textMaximum.getText());
				 * System.out.println(textMinimum.getText());
				 */



				/*
				 * CONDITIONAL ERROR - FOR FIXING*/ 
				 min = new ValidateNumber(Integer.valueOf(textMinimum.getText()));
				 max = new ValidateNumber(Integer.valueOf(textMaximum.getText()));
				 
				 String stringMin = min.toString();
				 String stringMax = max.toString();
				 
				
				 		setMin(Integer.valueOf(stringMin));	
						setMax(Integer.valueOf(stringMax));						
						RangeSelection.this.setVisible(false);
				 
				}
		});
		btnAccept.setBounds(61, 173, 89, 23);
		panelRangeSelection.add(btnAccept);

		// Cancel button to reject change, and to close the window.
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(176, 196, 222));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Closing the window without changes to the range
				RangeSelection.this.setVisible(false);

			}
		});
		btnCancel.setBounds(255, 173, 89, 23);
		panelRangeSelection.add(btnCancel);

		// Text field to enter minimum value
		textMinimum = new JTextField();
		textMinimum.setBounds(104, 111, 89, 23);
		// textMinimum.setColumns(10);
		panelRangeSelection.add(textMinimum);

		// Text field to enter maximum value
		textMaximum = new JTextField();
		textMaximum.setBounds(217, 111, 89, 23);
		// textMaximum.setColumns(10);
		panelRangeSelection.add(textMaximum);

		// Label for minimum value
		JLabel lblMinimum = new JLabel("From:");
		lblMinimum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMinimum.setBounds(104, 86, 64, 23);
		panelRangeSelection.add(lblMinimum);

		// Label for maximum value
		JLabel lblMaximum = new JLabel("To:");
		lblMaximum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaximum.setBounds(217, 85, 64, 23);
		panelRangeSelection.add(lblMaximum);

		// closing window button = X
		textClosePopup = new JTextField();
		textClosePopup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				// Closing the window without changes to the range = equivalent to close button, making window invisible.
				RangeSelection.this.setVisible(false);
			}
		});
		textClosePopup.setText("X");
		textClosePopup.setHorizontalAlignment(SwingConstants.CENTER);
		textClosePopup.setForeground(new Color(241, 57, 83));
		textClosePopup.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textClosePopup.setEditable(false);
		textClosePopup.setColumns(15);
		textClosePopup.setBorder(null);
		textClosePopup.setBackground(Color.WHITE);
		textClosePopup.setBounds(397, 0, 37, 29);
		panelRangeSelection.add(textClosePopup);

		// panel for moving the window
		JPanel panelDragWindow = new JPanel();
		panelDragWindow.setBackground(SystemColor.windowBorder);
		panelDragWindow.setBounds(0, 0, 396, 29);
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
				RangeSelection.this.setLocation(currPosx - initPosX, currPosy - initPosY);

			}
		});
		panelRangeSelection.add(panelDragWindow);
	}

	// Getters and setters for min and max values

	public int getMin() {
		return min;
	}

	public int getMax() {

		return max;
	}

	public void setMin(int a) {
		this.min = a;
	}

	public void setMax(int b) {
		this.max = b;
	}

	// Function to display current min and max values

	public void displayCurrentMinMax() {

		textMaximum.setText(String.valueOf(getMax()));
		textMinimum.setText(String.valueOf(getMin()));

	}
}

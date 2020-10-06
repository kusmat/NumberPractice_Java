package com.matkus;

import javax.swing.JOptionPane;


//Utility class to validate the number enteres is in the proper range.
public class ValidateNumber {
	
	int result;
	
	ValidateNumber(int input) {

		result = input;
		// check if number entered

		/* TO BE ADDED LATER */

		// check if number is positive
		isNumberPositive(result);	
		

		// check if number is less than or equal to 1,000,000
		
		isNumberNotTooLarge(result);

	}
	
	//Checking if number is larger than zero
	private void isNumberPositive(int testInput){
		
		if (testInput < 0) {

			JOptionPane.showMessageDialog(null,
				    "Number you entered is negative. Please enter number from 0 to 1,000,000",
				    "Negative number",
				    JOptionPane.ERROR_MESSAGE);
			result = 0;
			 
		} 
		
		else 
			result = testInput;			
	}//end check if number is > 0
	
	//Checking if number is less than 1,000,000
private void isNumberNotTooLarge(int testInput){
		
		if (testInput > 1000000) {

			JOptionPane.showMessageDialog(null,
				    "Number you entered is too large. Please enter number from 0 to 1,000,000",
				    "Too large number",
				    JOptionPane.ERROR_MESSAGE);
			result = 1000000;
			 
		}
		else 
			result = testInput;			
	}//end check if number is < 1,000,000

	
	public String toString() {
		
		return Integer.toString(result);
		
	}

}

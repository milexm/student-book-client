package com.acloudysky.studentbook.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

/***
 * Initializes the application and the UI classes. Displays the selection menu.
 * Starts the menu loop to allow the user to make the selection.   
 * @author Michael Miele
 *
 */
public class Main {
	
	
	public static void main(String[] args) {
			
		// Display greeting message.
		 Utility.displayWelcomeMessage("Student Book Client");
		 
		 
		// Instantiate the SimpleUI class and display menu.
		SimpleUI sui = new SimpleUI();

		// Start processing user's input.
		sui.processUserInput();
 
		
		 // Display goodbye message.
		Utility.displayGoodbyeMessage("Student Book Client");
		
		
		
	}

}

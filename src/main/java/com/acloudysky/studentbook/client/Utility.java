package com.acloudysky.studentbook.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/***
* Defines utility methods and variables to support the application operations
* such as menu creation, list initialization and so on.
* @author Michael Miele
*/
public class Utility {

	// The divider length used in displaying menu and other.
	static int DIVIDER_LENGTH = 66;
	
	/**
	 * Application menu entries.
	 */
	static ArrayList<String> menuEntries = new  ArrayList<String>(
			Arrays.asList(
							"ga - Get all students", 
							"gi - Get student with specified id",
							"po - Create new student",
							"pu - Modify student with specified id",
							"di - Delete student with specified id",
							"m  - Display menu",
							"x  - Quit the application"
						)
	);
		
	/***
	  * Creates the divider line.
	  * @param c The character to use to create the divider line.
	  * @param length The length of the divider line.
	  * @return Formatted divider line.
	  */
	 private static String dividerLine(String c, int length)
	 {
	     String divider = "";
	     for(int i = 0; i < length; i++)
	         divider = divider.concat(c);
	
	     return divider;
	 }
	 
	 
	 /***
	  * Creates the header to display.
	  * @param headerText The text to display in the header.
	  * @param length The length of the header.  
	  * @return Formatted header line.
	  */
	 private static String headerLine(String headerText, int length)
	 {
	     String header = "";
	     header = header.concat("***");
	     int blankSpaces = (length - (header.length() + headerText.length()))/2;
	     
	     for(int i = 2; i < blankSpaces; i++)
	     	header = header.concat(" ");
	     header = header.concat(headerText);
	     for(int i = header.length(); i < length - 3; i++)
	     	header = header.concat(" ");
	     header = header.concat("***");
	     return header;
	 }
	 
	 /***
		 * Gets the application menu.
		 * @return List containing the menu.
		 */
		public static ArrayList<String> getMenuEntries() {
				return menuEntries;
		}
		
	 /***
	  * Displays welcome message.
	  * @param message The message to display.
	  */
	 public static void displayWelcomeMessage(String message)
	 {
	     System.out.println(dividerLine("*", DIVIDER_LENGTH));
	     String welcome = "Welcome to " + message; 
	     System.out.println(headerLine(welcome, DIVIDER_LENGTH));
	     System.out.println(dividerLine("*", DIVIDER_LENGTH));
	 }
	
	 /***
	  * Displays good bye message.
	  * @param message The message to display.
	  */
	 public static void displayGoodbyeMessage(String message)
	 {
		 headerLine(message, DIVIDER_LENGTH);
	     System.out.println(dividerLine("*", DIVIDER_LENGTH));
	     String bye = "Thank you for using " + message; 
	     System.out.println(headerLine(bye, DIVIDER_LENGTH));
	     System.out.println(dividerLine("*", DIVIDER_LENGTH));
	 }
	 
	 /***
	  * Displays the menu.
	  * @param entry The array containing the menu entries. 
	  */
	 public static void displayMenu(ArrayList<String> entry) {
		
		// Display menu header.
		System.out.println(dividerLine("*", DIVIDER_LENGTH));
		
		// Display menu entries.
	 	Iterator<String> i = entry.iterator();
	 	while (i.hasNext()) {
	 		System.out.println(i.next());
	 	}	
	 	
	 	// Display menu footer.
	 	System.out.println(dividerLine("*", DIVIDER_LENGTH));
	 }
}

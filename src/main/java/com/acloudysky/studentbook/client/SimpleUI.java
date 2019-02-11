package com.acloudysky.studentbook.client;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.web.client.HttpServerErrorException;

import com.acloudysky.studentbook.student.Student;
import com.fasterxml.jackson.databind.ObjectMapper;


/*** 
 * Displays a selection menu for the user. Processes the  user's input and calls the proper 
 * method based on the user's selection. 
 * Each method calls the related student-book-service REST API.
 * @author Michael Miele.
 */
public class SimpleUI  {
	
	private static final int SERVER_PORT = 8282;
	private static final String CONTEXT_PATH = "students";
	
	private String studentId, studentName, studentEmail, studentPhoneNumber;
	private static String END_POINT;

	private Student student = new Student();
    
		
	/**
	 * Instantiate SimpleUI class.
	 */
	SimpleUI() {
		
		// Initialize END_POINT: http://localhost:8282/students/.
		END_POINT = String.format("%s:%s/%s/", "http://localhost", SERVER_PORT, CONTEXT_PATH);
		// Test
		// System.out.println(END_POINT);
		
		// Display menu.
		Utility.displayMenu(Utility.getMenuEntries());
		
	}
	
	/*
	 * Reads user input.
	 */
	private static String readUserInput(String msg) {
		
		// Open standard input.
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));

		String selection = null;
		
		//  Read the selection from the command-line; need to use try/catch with the
		//  readLine() method
		try {
			if (msg == null)
				System.out.print("\n>>> ");
			else
				System.out.print("\n" + msg);
			selection = br.readLine();
		} catch (IOException e) {
			System.out.println("IO error trying to read your input!");
			System.out.println(String.format("%s", e.getMessage()));
			System.exit(1);
		}
		
		return selection;

	}
	
	/*
	 * Executes the selected operation.
	 */
	private void performOperation(String operation) {
	
		// Select operation to perform.
		switch(operation) {
		
			case "gi": {
			
				try{
					// Get the selected student information.
					do {
						System.out.print(String.format("%s", "Enter student Id such as 10001"));
						studentId = readUserInput("Student Id: ").toString();	
					} while(studentId.isEmpty());
					
					// Display single student.
					String result = RestOperation.Get(String.format("%s%s/", END_POINT, studentId));
					System.out.println(result);
					
				}
				catch (ClientProtocolException e) {
					System.out.println(String.format("%s", e.getMessage()));
				} catch (IOException e) {
					System.out.println(String.format("%s", e.getMessage()));
				} catch (HttpServerErrorException e) {
					System.out.println(String.format("%s %s", e.getMessage()));
				}
				break;
			}
			
			case "ga": {
				try{
					// Display all students.
					String result = RestOperation.Get(String.format("%s", END_POINT));
					System.out.println(result);
				}
				catch (ClientProtocolException e) {
					System.out.println(String.format("%s", e.getMessage()));
				} catch (IOException e) {
					System.out.println(String.format("%s", e.getMessage()));
				}catch (HttpServerErrorException e) {
					System.out.println(String.format("%s %s", e.getMessage()));
				}
				break;
			}
			
			case "po": {
				try{
					// Create a new student entry.
						do {
							System.out.print(String.format("%s", "Enter student Id such as 10001"));
							studentId = readUserInput("Student Id: ").toString();	
					} while(studentId.isEmpty());
					
					student.setId(Long.parseLong(studentId));
					
					do {
						System.out.print(String.format("%s", "Enter student name such as Mary Johnson"));
						studentName = readUserInput("Student name: ").toString();
					} while(studentName.isEmpty());
					
					student.setName(studentName);
				
					do {
						System.out.print(String.format("%s", "Enter student email such as maryj@email.com"));
						studentEmail = readUserInput("Student email: ").toString();
					} while(studentEmail.isEmpty());
					
					student.setEmail(studentEmail);
					
					do {
						System.out.print(String.format("%s", "Enter student phone such as 222 345 6789"));
						studentPhoneNumber = readUserInput("Student phone number: ").toString();
					} while(studentPhoneNumber.isEmpty());
					
					student.setPhoneNumber(studentPhoneNumber);
					
					// Issue a Post request.
					String result = RestOperations.Post(String.format("%s", END_POINT), student);
					System.out.println(result);
				}
				catch (ClientProtocolException e) {
					System.out.println(String.format("%s", e.getMessage()));
				} catch (IOException e) {
					System.out.println(String.format("%s", e.getMessage()));
				}catch (HttpServerErrorException e) {
					System.out.println(String.format("%s %s", e.getMessage()));
				}
				break;
			}
			
			case "pu": {
				try{
					// Update the selected student entry.
					do {
							System.out.print(String.format("%s", "Enter student Id such as 10001"));
							studentId = readUserInput("Student Id: ").toString();	
					} while(studentId.isEmpty());
					
					student.setId(Long.parseLong(studentId));
					
					do {
						System.out.print(String.format("%s", "Enter student name such as Mary Johnson"));
						studentName = readUserInput("Student name: ").toString();
					} while(studentName.isEmpty());
					
					student.setName(studentName);
				
					do {
						System.out.print(String.format("%s", "Enter student email such as maryj@email.com"));
						studentEmail = readUserInput("Student email: ").toString();
					} while(studentEmail.isEmpty());
					
					student.setEmail(studentEmail);
					
					do {
						System.out.print(String.format("%s", "Enter student phone such as 222 345 6789"));
						studentPhoneNumber = readUserInput("Student phone number: ").toString();
					} while(studentPhoneNumber.isEmpty());
					
					student.setPhoneNumber(studentPhoneNumber);
					
					// Issue a Put request. 
					String result = RestOperations.Put(String.format("%s%s/", END_POINT, studentId), student);
					System.out.println(result);
					
				}
				catch (ClientProtocolException e) {
					System.out.println(String.format("%s", e.getMessage()));
				} catch (IOException e) {
					System.out.println(String.format("%s", e.getMessage()));
				}catch (HttpServerErrorException e) {
					System.out.println(String.format("%s %s", e.getMessage()));
				}
				break;
			}
			
			case "di": {
				
				try{
					// Get the selected student information.
					do {
						System.out.print(String.format("%s", "Enter student Id such as 10001"));
						studentId = readUserInput("Student Id: ").toString();	
					} while(studentId.isEmpty());
					
					// Delete student information.
					String result = RestOperations.Delete(String.format("%s%s/", END_POINT, studentId));
					System.out.println(result);
					
				}
				catch (ClientProtocolException e) {
					System.out.println(String.format("%s", e.getMessage()));
				} catch (IOException e) {
					System.out.println(String.format("%s", e.getMessage()));
				} catch (HttpServerErrorException e) {
					System.out.println(String.format("%s %s", e.getMessage()));
				}
				break;
			}
			
			default: {
				System.out.println(String.format("%s is not allowed", operation));
				break;
			}
		}
				
	}
	
	
	/***
	 * Gets user selection and calls the related method.
	 * Loops indefinitely until the user exits the application.
	 */
	public void processUserInput() {
		
		while (true) {
			
			// Get user input.
			String userSelection = readUserInput(null).toLowerCase();	
			// Normalize user's input.
			String normalizedUserSelection = userSelection.trim().toLowerCase();
			

			try{
				// Exit the application.
				if ("x".equals(normalizedUserSelection)){
					break;
				}
				else
					if ("m".equals(normalizedUserSelection)) {
						// Display menu
						Utility.displayMenu(Utility.getMenuEntries());
						continue;
					}
				
			}
			catch (Exception e){
				// System.out.println(e.toString());
				System.out.println(String.format("Input %s is not allowed%n", userSelection));
				continue;
			}
			
			performOperation(normalizedUserSelection);
		}
		
	}
	
}

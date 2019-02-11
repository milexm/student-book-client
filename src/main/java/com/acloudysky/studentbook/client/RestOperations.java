package com.acloudysky.studentbook.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.acloudysky.studentbook.student.Student;

/***
 * Performs REST operations using the {@link com.acloudysky.studentbook.student.StudentResource} class. 
 * Each method calls the related student-book-service REST API. 
 * In particular it uses the Spring Boot framework
 * <p>For more information, see 
 * @see <a href="https://o7planning.org/en/11647/spring-boot-restful-client-with-resttemplate-example#a13887020" target="_blank">Spring Boot Restful Client with RestTemplate example</a>.
 * </p>
 * @author Michael Miele
 *
 */
public class RestOperations {
	
	/***
	 *  
	 * @param url - The resource end-point address such as: http://localhost:8282/students/. 
	 * @return - The Id of the student if found. 
	 * @throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred. 
	 * @throws HttpServerErrorException - Exception thrown when an HTTP 5xx is received.
	 */
	public static String Get(String url) throws ClientProtocolException, IOException, HttpServerErrorException {
	 
		String result = "";
		
		// Instantiate template. 
        RestTemplate restTemplate = new RestTemplate();
 
        // Issue GET request.
        Student s = restTemplate.getForObject(url, Student.class);
 
        if (s != null) {	  
            result = String.format("Student %d found", s.getId());
         } else {
      	   result = String.format("Student not found");
         }
        
        return result;
	   
	}
	
	/***
	 * Creates a student record using the information entered by the user.
	 * @param url - The resource end-point address such as: http://localhost:8282/students/.
	 * @param student - The object containing student information. See also @see Student.java. 
	 * @return - Message about the operation success or failure. 
	 * @throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred. 
	 * @throws HttpServerErrorException - Exception thrown when an HTTP 5xx is received.
	 */
	public static String Post(String url, Student student) throws ClientProtocolException, IOException, HttpServerErrorException {
	
	  String result = "Student record created.";
      
	  // Instantiate headers.
      HttpHeaders headers = new HttpHeaders();
      
      // headers.add("Accept", "APPLICATION_JSON");
      headers.setContentType(MediaType.APPLICATION_JSON);
 
      // Instantiate rest template. 
      RestTemplate restTemplate = new RestTemplate();
 
      // Attach data to the request.
      HttpEntity<Student> requestBody = new HttpEntity<>(student, headers);
 
      // Issue POST request.
      // Student s = restTemplate.postForObject(url, requestBody, Student.class);
      restTemplate.postForObject(url, requestBody, Student.class);
      
      return result;
      
	}
	
	/***
	 * Updates the record of the student with the specified Id.
	 * @param url - The resource end-point address such as: http://localhost:8282/students/10001.
	 * @param student - The object containing student information. See also @see Student.java.
	 * @throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred. 
	 * @throws HttpServerErrorException - Exception thrown when an HTTP 5xx is received.
	 */
	public static String Put(String url, Student student) throws ClientProtocolException, IOException, HttpServerErrorException {
		
		String result = "Student record not updated."; 
		
		// Instantiate headers.
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	 
	    // Instantiate rest template. 
	    RestTemplate restTemplate = new RestTemplate();
	 
	    // Attach data to the request.
	    HttpEntity<Student> requestBody = new HttpEntity<>(student, headers);
	 
	    // Issue POST request.
	    restTemplate.put(url, requestBody, new Object[] {});
	 
        Student s = restTemplate.getForObject(url, Student.class);
 
        if (s != null) {
            result = "Student record updated."; 
        }
        
        return result; 
    }
	      
	/***
	 * Deletes the student with the specified Id. 
	 * @param url - The resource end-point address such as: http://localhost:8282/students/10007.  
	 * @throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred. 
	 * @throws HttpServerErrorException - Exception thrown when an HTTP 5xx is received.
	 */
	public static String Delete(String url) throws ClientProtocolException, IOException, HttpServerErrorException {
	 	
		String result = "Student record not deleted."; 
		
		// Instantiate template. 
        RestTemplate restTemplate = new RestTemplate();
 
        // Issue DELETE request.
        restTemplate.delete(url);
 
        result = "Student record deleted."; 
        
        return result;
	   
	}	
}

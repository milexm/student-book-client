package com.acloudysky.studentbook.client;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.HttpClientBuilder;



import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/***
 * Performs REST operations using the {@link com.acloudysky.studentbook.student.StudentResource} class. 
 * Each method calls the related student-book-service REST API. 
 * In particular it uses the Apache Http client. 
 * <p>For more information, see 
 * @see <a href="https://www.javacodegeeks.com/2012/09/simple-rest-client-in-java.html" target="_blank">Simple REST Client in Java</a>.
 * </p>
 * @author Michael Miele
 *  
 */
public class RestOperation {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	/*
	 * Make JSON response prettier. 
	 * @param response
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	private static String readJson(HttpResponse response) throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {
		String result = "";
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		Object json = mapper.readValue(response.getEntity().getContent(), Object.class);
		result = mapper
				.writerWithDefaultPrettyPrinter()
                .writeValueAsString(json);
		return result;
		
	}
	
	/***
	 * Obtain a single student or all students information 
	 * @param url - The resource end-point address such as: http://localhost:8282/students/ for all students or
	 * The resource end-point address such as: http://localhost:8282/students/10001 for a single student. 
	 * @return - Student info in JSON format.
	 * @throws ClientProtocolException - Signals an error in the HTTP protocol.
	 * @throws IOException - Signals that an I/O exception of some sort has occurred. 
	 */
	public static String Get(String url) throws ClientProtocolException, IOException {
	  
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url); 
		HttpResponse response = client.execute(request);
		return readJson(response);
		
	 }
	

}

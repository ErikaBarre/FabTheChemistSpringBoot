package net.fab.the.chemist.springbootrestfullws;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import sun.misc.BASE64Encoder;

public class NetClientTest {

	/*
	
	//si ajout de springsecurity alors on doit ajouter cela
			//mot de passe fournit par srping lors du lancement de l'application
			
			BASE64Encoder enc = new sun.misc.BASE64Encoder();
		      String userpassword = "user" + ":" + "980debe1-7e9e-4101-8931-ebbc3b0cc12f" ;
		      String encodedAuthorization = enc.encode( userpassword.getBytes() );
		      conn.setRequestProperty("Authorization", "Basic "+  encodedAuthorization);
		      
	ou bien prendre celui qu'on place dans application.properties
	
	String userpassword = "Erika" + ":" + "Barre" ;
	
	
	*/
	@Test
	public void retrieveAllUsersTest() {
		  try {

			URL url = new URL("http://localhost:8080/users");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		  assertTrue(true);
	}
	
	@Test
	public void retrieveUserTest() {
		  try {
				URL url = new URL("http://localhost:8080/users/1");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

				conn.disconnect();

			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			  }
		  assertTrue(true);
	}
		
	@Test
	public void postTest() {
		try {
			URL url = new URL("http://localhost:8080/users");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
	
			String input = "{\"id\":7,\"name\":\"James\",\"birthDate\":\"2019-02-15T21:21:22.703+0000\"}";
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			System.out.println("status code :" + conn.getResponseCode());
			//si c'est pour une creation il faut renvoyé un status 201 indiquant que tout s'est bien passé
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code :"+ 	+ conn.getResponseCode());
				//throw new RuntimeException(arg0);
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			System.out.println("Output from Server (post) .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		assertTrue(true);
	}
	
	@Test
	public void deleteTest() {

		  try {
				URL url = new URL("http://localhost:8080//users//1");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("DELETE");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

				conn.disconnect();

			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			  }
		  assertTrue(true);
	}
	
	@Test(expected = RuntimeException.class)
	public void postExceptionTest() {
		try {
			URL url = new URL("http://localhost:8080/users");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
	
			String input = "{\"id\":8,\"name\":\"J\",\"birthDate\":\"2019-04-15T21:21:22.703+0000\"}";
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			System.out.println("status code post exception:" + conn.getResponseCode());
			//si c'est pour une creation il faut renvoyé un status 201 indiquant que tout s'est bien passé
			if (conn.getResponseCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
				//conn.getres
				InputStream inputStream = conn.getErrorStream();
				//System.out.println(inputStream.toString());
				
				System.out.println(conn.getResponseMessage());
				
				BufferedReader rd = new BufferedReader(new InputStreamReader( conn.getErrorStream()));
				
				//BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		        StringBuffer result = new StringBuffer();
		        String line = "";
		        while ((line = rd.readLine()) != null)
		            result.append(line);
		        System.out.println(result);
				
				throw new RuntimeException("Failed : HTTP error code : one or more data are wrong" + conn.getResponseCode());
				//throw new RuntimeException(arg0);
			}
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code :"+ 	+ conn.getResponseCode());
				//throw new RuntimeException(arg0);
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			System.out.println("Output from Server (post) .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		assertTrue(false);
	}
	
}

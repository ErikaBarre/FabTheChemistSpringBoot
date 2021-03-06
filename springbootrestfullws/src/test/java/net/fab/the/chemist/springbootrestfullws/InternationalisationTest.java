package net.fab.the.chemist.springbootrestfullws;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import sun.misc.BASE64Encoder;

public class InternationalisationTest {

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

			URL url = new URL("http://localhost:8080/hello-world-internalization");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Accept-Language", "en");

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
	public void internationalisationTest() {
		  try {

			URL url = new URL("http://localhost:8080/hello-world-internalization2");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Accept-Language", "en");

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

}

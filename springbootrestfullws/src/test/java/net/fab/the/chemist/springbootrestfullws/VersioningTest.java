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

public class VersioningTest {

	@Test
	public void versionHeaderTest() {
		  try {

			URL url = new URL("http://localhost:8080/person/header");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("X-API-VERSION", "1");
			
			//si ajout de springsecurity alors on doit ajouter cela
			//mot de passe fournit par srping lors du lancement de l'application
			
			BASE64Encoder enc = new sun.misc.BASE64Encoder();
		      String userpassword = "Erika" + ":" + "Barre" ;
		      String encodedAuthorization = enc.encode( userpassword.getBytes() );
		      conn.setRequestProperty("Authorization", "Basic "+  encodedAuthorization);
		      
		      
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
	public void versionProducesTest() {
		  try {

			URL url = new URL("http://localhost:8080/person/produces");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/vnd.company.app-v2+json");
			//conn.setRequestProperty("X-API-VERSION", "1");
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

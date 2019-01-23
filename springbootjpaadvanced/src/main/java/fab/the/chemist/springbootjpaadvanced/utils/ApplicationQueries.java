package fab.the.chemist.springbootjpaadvanced.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.JdbcUtils;

public class ApplicationQueries {

	Logger LOGGER = LoggerFactory.getLogger(ApplicationQueries.class);
	
	public static enum ApplicationQuery {

		RequestOne			 	 ("sql/get_all_courses.sql"),
		RequestTwo			 	 ("sql/get_all_courses.sql");

		private String path = "";

		//Constructeur
		ApplicationQuery(String path){
			this.path = path;
		}

		public String toString(){
			return path;
		}
	}
	
	public static String getQuery (ApplicationQuery key) throws java.io.IOException {
		
		return readFile(key); 
	}
	
	public static String readFile (ApplicationQuery key) throws IOException {
		String query = new String();
		try(
				StringWriter writer= new StringWriter();
				InputStream inputStream = getInputStream(key);
				){
			IOUtils.copy(inputStream, writer,"UTF-8");
			query = writer.toString();
		}
		return query ;
	}

	public static String readFileCharArray(ApplicationQuery key)
														throws 	UnsupportedEncodingException, 
																IOException {
		String query = new String();
		//https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
		try(
				StringWriter writer= new StringWriter();	
				InputStream inputStream = getInputStream(key); 
				Reader reader = new java.io.InputStreamReader(inputStream, "UTF-8");
				){
			char[] t= new char[4096];
			while (true) {
				int n= reader.read(t, 0, t.length);
				if (n < 0) { break; }
				writer.write(t, 0, n); 
			} 
			query = writer.toString();
		}
		return query;
	}
	
	public static String readFromInputStream(ApplicationQuery key) throws IOException {
	    StringBuilder resultStringBuilder = new StringBuilder();
	    try (
	    		InputStream inputStream = getInputStream(key);
	    		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
	    	) {
		        String line;
		        while ((line = bufferedReader.readLine()) != null) {
		            resultStringBuilder.append(line).append("\n");
		        }
	    	}
	    return resultStringBuilder.toString();
	}
	
	private static InputStream getInputStream(ApplicationQuery key) {
		return ApplicationQueries.class.getClassLoader().getResourceAsStream(key.toString());
	}
	
	public static String SqlRequestIn(String sqlRequest, int[] replaceValue, String field, String value){
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\(" + replaceValue + "=" + replaceValue +"\\)");
		java.util.regex.Matcher matcher= pattern.matcher(sqlRequest);
		sqlRequest= matcher.replaceAll(field + " in (" + org.springframework.util.StringUtils.quoteIfString(value) + ")");
		return sqlRequest;
	}
		
	/**
	 * order by
	 * @param sqlRequest
	 * @param expresssion
	 * @return
	 */
	public static String replaceExpressionPatternOrder (String sqlRequest, String expresssion){
		return replaceExpressInSql(sqlRequest, expresssion, "--order-by--");
	}
	
	/**
	 * inner join
	 * @param sqlRequest
	 * @param expresssion
	 * @return
	 */
	public static String replaceExpressionPatternInnerJoin (String sqlRequest, String expresssion){
		return replaceExpressInSql(sqlRequest, expresssion, "--inner-join--");
	}

	private static String replaceExpressInSql(String sqlRequest,String expresssion, String expressionToReplace) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(java.util.regex.Pattern.quote(expressionToReplace));
		java.util.regex.Matcher matcher = pattern.matcher(sqlRequest);
		sqlRequest= matcher.replaceAll(" " + expresssion + " ");
		return sqlRequest;
	}

}

package test_package;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class MyClass1 {


public static void main(String[] args) throws IOException {
	// my variables
	String myText = "";
	
	// TODO Auto-generated method stub
	
    // Create a neat value object to hold the URL
    URL url = new URL("https://jsonplaceholder.typicode.com/posts");
    // Open a connection(?) on the URL(?) and cast the response(??)
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    // Now it's "open", we can set the request method, headers etc.
    connection.setRequestProperty("accept", "application/json");
    connection.setRequestMethod("GET");

    int status = connection.getResponseCode();
    System.out.println(status);
       
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	String inputLine;
	StringBuffer content = new StringBuffer();
	while ((inputLine = in.readLine()) != null) {
	    content.append(inputLine);
	}
	in.close();
	
	myText = content.toString();
    JSONArray array = new JSONArray(myText);
    JSONObject object = new JSONObject();
    boolean errorPresent = false;

	System.out.printf("Checking %d data\r\n", array.length());
	for(int i=0; i < array.length(); i++) {
		object = array.getJSONObject(i);
		try{
			object.getInt("userId");
			object.getInt("id");
			object.getString("title");
			object.getString("body");
		}
		catch(Exception e){
			System.out.println(e);
			System.out.printf("Data number %d is bad\r\n", i);
			errorPresent = true;
		}
	}
    	
	if(!errorPresent) {
		System.out.printf("%d data without error\r\n", array.length());
	}
	
	System.out.println("The 1st element of array");
	System.out.println(array.get(0));
	System.out.println("The 100th element of array");
	System.out.println(array.get(99));
	
	connection.disconnect();
	
	}
}
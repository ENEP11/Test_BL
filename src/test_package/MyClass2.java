package test_package;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class MyClass2 {


public static void main(String[] args) throws IOException {
	// my variables
	JSONObject data = new JSONObject();
	data.put("title", "recommendation");
	data.put("body", "motorcycle");
	data.put("id", 12);
	String myText = data.toString();
	
	// TODO Auto-generated method stub
	
    // Create a neat value object to hold the URL
    URL url = new URL("https://jsonplaceholder.typicode.com/posts");
    // Open a connection(?) on the URL(?) and cast the response(??)
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    // Now it's "open", we can set the request method, headers etc.
    connection.setRequestMethod("POST");
    connection.setRequestProperty("accept", "application/json");
    connection.setRequestProperty("content-type", "application/json; utf-8");
    connection.setDoOutput(true);
    
    DataOutputStream wr = new DataOutputStream( connection.getOutputStream());
	wr.writeUTF(myText);
	
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	String inputLine;
	StringBuffer content = new StringBuffer();
	while ((inputLine = in.readLine()) != null) {
	    content.append(inputLine);
	}
	in.close();
	
    int status = connection.getResponseCode();
    System.out.println(status);
    
	JSONArray array = new JSONArray();
	JSONObject object = new JSONObject();
    
	if( status != 201) {
    	System.out.println("Failed to POST!");
    }else {
        System.out.println(connection.getContentType());
    	System.out.println(content);
    	myText = content.toString();
    	
    	try {
    		try {
    			array = new JSONArray(myText);
    			object = array.getJSONObject(100);
    		}catch(Exception e) {
    			System.out.println(e);
        		try {
            	    object = new JSONObject(myText);
        			
        		}catch(Exception e2) {
        			System.out.println(e2);
        			System.out.println("is not a JSON, exiting...");
        			System.exit(-1);
        		}
    		}

    	    if(object.has("id") && object.has("userId") && object.has("title") && object.has("body")) {
    	    	if(object.get("id").equals(101) && object.get("userId").equals(12) && object.get("title").toString().equals("recommendation") && object.get("body").toString().equals("motorcycle")){
    	    		System.out.println("Input == Output, POST succeeded");
    	    		connection.disconnect();
    	    		System.exit(0);
    	    	}else{
    	    		System.out.println("Input != Output, POST failed");
    	    		connection.disconnect();
    	    		System.exit(-1);
    	    	}
    	    }else {
    	    	System.out.println("response is incomplete");
    	    }
    	}catch(Exception e){
    		System.out.println(e);
    	}
    }
    connection.disconnect();
    
    // This line makes the request
    // Open a connection(?) on the URL(?) and cast the response(??)
    connection = (HttpURLConnection) url.openConnection();
    // Now it's "open", we can set the request method, headers etc.
    connection.setRequestMethod("GET");
    connection.setRequestProperty("accept", "application/json");
   
    in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	inputLine = "";
	content.delete(0, content.length());
	while ((inputLine = in.readLine()) != null) {
	    content.append(inputLine);
	}
	in.close();
	
    status = connection.getResponseCode();
    System.out.println(status);
    
	if( status != 200) {
    	System.out.println("Failed to GET!");
    }else {
        System.out.println(connection.getContentType());
    	System.out.println(content);
    	myText = content.toString();
    	
    	try {
    		try {
    			array = new JSONArray(myText);
    			object = array.getJSONObject(100);
    			
    		}catch(Exception e) {
    			System.out.println(e);
        		try {
            	    object = new JSONObject(myText);
        			
        		}catch(Exception e2) {
        			System.out.println(e2);
        			System.out.println("is not a JSON, exiting...");
        			System.exit(-1);
        		}
    		}
    		
    	    if(object.has("id") && object.has("userId") && object.has("title") && object.has("body")) {
    	    	if(object.get("id").equals(101) && object.get("userId").equals(12) && object.get("title").toString().equals("recommendation") && object.get("body").toString().equals("motorcycle")){
    	    		System.out.println("Input == Output, POST succeeded");
    	    		connection.disconnect();
    	    		System.exit(0);
    	    	}else{
    	    		System.out.println("Input != Output, POST failed");
    	    		connection.disconnect();
    	    		System.exit(-1);
    	    	}
    	    }else {
    	    	System.out.println("response is incomplete");
    	    }
    	}catch(Exception e){
    		System.out.println(e);
    	}
    }
    connection.disconnect();
		
	}

}
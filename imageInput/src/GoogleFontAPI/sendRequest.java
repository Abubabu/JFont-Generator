package GoogleFontAPI;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONML;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONStringer;
import org.json.JSONTokener;
import org.json.JSONWriter;

public class sendRequest {
	final protected String apikey = "AIzaSyAgJ2Fpuffe-V-3TkmdTzJwjQ9sooh7TgU";
	
	final protected String USER_AGENT = "Chrome/41.0.2228.0";

	public static void main(String[] args) throws Exception {

		sendRequest http = new sendRequest();
		http.sendGet(http.queryBuilder());

	}

	private void sendGet(String string) throws Exception {
		        
		String url = string;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);
		
		
		InputStream inputStreamObject = con.getInputStream();
	    BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStreamObject, "UTF-8"));
	    StringBuilder responseStrBuilder = new StringBuilder();

	    String inputStr;
	    while ((inputStr = streamReader.readLine()) != null)
	    {
	        responseStrBuilder.append(inputStr);
	    }

	    JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
	    
	    System.out.print(jsonObject);
		
		/*BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
			    
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		System.out.println(response.toString());*/

	}
	
	private String queryBuilder()
	{
		return "https://www.googleapis.com/webfonts/v1/webfonts?key=" + apikey ;
		
		//https://developers.google.com/fonts/docs/developer_api
		//https://fonts.google.com/
		//http://jsonviewer.stack.hu/
	}
}
 
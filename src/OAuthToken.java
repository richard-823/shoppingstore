import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.tomcat.util.codec.binary.Base64;

public final class OAuthToken{
	
	private String token;

	private static final String SANDBOX_SERVER = "https://api.sandbox.paypal.com";
	
	/**
	 * OAuth URI path parameter
	 */
	private static final String OAUTH_TOKEN_PATH = "/v1/oauth2/token";

	/**
	 * Client ID for OAuth
	 */
	private String clientID;

	/**
	 * Client Secret for OAuth
	 */
	private String clientSecret;	
	
	
	public OAuthToken(String clientID, String clientSecret) {
		super();
		this.clientID = clientID;
		this.clientSecret = clientSecret;		
	}

	public String getAccessToken() throws Exception {		
		generateToken();			
		return token;		
	}
	

	public String getClientID() {
		return this.clientID;
	}

	public String getClientSecret() {
		return this.clientSecret;
	}

	private synchronized void generateToken() throws Exception {
		HttpURLConnection connection;
		URL url = new URL (SANDBOX_SERVER+OAUTH_TOKEN_PATH);		
		OutputStreamWriter writer = null;
		InputStream successResponse = null;
		BufferedReader reader = null;

		String inputLine;
		StringBuilder response = new StringBuilder();
		String base64ClientCredentials = null; 
		int responseCode = -1;
		try {
			connection = (HttpURLConnection)url.openConnection();
			
			connection.setDoOutput(true); 
			connection.setDoInput(true); 
			connection.setUseCaches(false); 
			connection.setConnectTimeout(30000);
			connection.setRequestMethod("POST");
			
			base64ClientCredentials = generateBase64String(clientID + ":" + clientSecret);			
			connection.setRequestProperty("Authorization", "Basic " +base64ClientCredentials);
			connection.setRequestProperty("Accept", "application/json");		
			connection.setRequestProperty("Content-type", "x-www-form-urlencoded"); 
			
			// send request and get response
			connection.connect();
			
			writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8"); 
			writer.write("grant_type=client_credentials");
			writer.flush();
			
			responseCode = connection.getResponseCode();
			
			if (responseCode >= 200 && responseCode < 300) {
				successResponse = connection.getInputStream();
			}
			
			reader = new BufferedReader(new InputStreamReader(successResponse,"UTF-8"));
			
			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
			
			// parse response as JSON object
			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(response.toString());
			this.token = //jsonElement.getAsJsonObject().get("token_type").getAsString() + " " +
					jsonElement.getAsJsonObject().get("access_token").getAsString();			
			
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new Exception(e.getMessage(), e);
		} finally {			
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
			} finally {
				reader = null;
				writer = null;
			}
		}
	}
	
	private String generateBase64String(String clientCredentials) throws Exception {		
        String base64ClientID = Base64.encodeBase64String(clientCredentials.getBytes());
		return base64ClientID;
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String SANDBOX_SERVER = "https://api.sandbox.paypal.com";	
	private static final String PAYMENT_PATH = "/v1/payments/payment";
	//private static String EXECUTE_PATH = "/v1/payments/payment/payment_id/execute/";
	
	private static final String clientID = "AVxdbhZ7PJl6WKruc99DbsIHGtfWe0YyruakKHPYpV0Xdo6udrSdkl1iCucBMOcMc73JFrYkz6JN5TFW";
	private static final String clientSecret = "EJW-7SWe4xIsKP0jhk4elOUhfXOcilOSA9nzt8YjUFHWoi2Im1Pi9mqNpaThkOQc69X9swr17r3C1mwt";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int transaction_id = -1;
		
		try {			

			//oAuth
			OAuthToken oAuthToken = new OAuthToken(clientID, clientSecret);
			
			String token = oAuthToken.getAccessToken();
			
			//make a payment
			String payload = buildReqBody(req);			
			
			transaction_id = makePayemnt(token, payload);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		req.setAttribute("txnId", transaction_id);
		req.getRequestDispatcher("Success.jsp").forward(req, resp);
	}
	
	public int makePayemnt(String token, String payload) throws Exception {
		int txn_id = -1;
		HttpURLConnection connection;
		URL url = new URL (SANDBOX_SERVER + PAYMENT_PATH);	
		OutputStreamWriter writer = null;
		InputStream successResponse = null;
		BufferedReader reader = null;

		String inputLine;
		StringBuilder response = new StringBuilder();
		int responseCode = -1;
		
		try{
			connection = (HttpURLConnection)url.openConnection();
			connection.setDoOutput(true); 
			connection.setDoInput(true); 
			connection.setUseCaches(false); 
			connection.setConnectTimeout(30000);
			//connection.setRequestMethod("POST");
			String base64 = Base64.encodeBase64String(token.getBytes());
			connection.setRequestProperty("Content-Length", String.valueOf(payload.trim().length()));
			connection.setRequestProperty("Content-type", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + base64);//Base64.encodeBase64String(token.getBytes()));
			connection.setRequestProperty("Accept", "application/json");	
			
			connection.connect();
						
			writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8"); 
			
			writer.write(payload);
			writer.flush();
			
			responseCode = connection.getResponseCode();//401, token as same as which returns by curl
			
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
			txn_id = Integer.valueOf(jsonElement.getAsJsonObject().get("id").toString()).intValue();			
					
		} catch(Exception e){
			System.out.println(e.toString());
			throw new Exception(e.getMessage(), e);
		} finally{
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
		
		return txn_id;
	}
/*
	private Transaction initTxn(HttpServletRequest req){
		Transaction txn = new Transaction();
		//txn.setAmount(req.getParameter(arg0));
		//txn.setItems(items);
		txn.setAmount(169.99);
		txn.setPayerAddressCountry(req.getParameter("country"));
		txn.setPayerAddressState(req.getParameter("state"));
		txn.setPayerAddressStreet(req.getParameter("street"));
		txn.setPayerEmail(req.getParameter("email"));
		txn.setPayerFirstName(req.getParameter("firstName"));
		txn.setPayerLastName(req.getParameter("lastName"));
		txn.setPayerPhone(req.getParameter("phone"));
		txn.setPayerZipcode(req.getParameter("zip"));
		return txn;
	}*/
	
	private String buildReqBody(HttpServletRequest req){
		String body = null;
		//TODO
		return body;
	}
}

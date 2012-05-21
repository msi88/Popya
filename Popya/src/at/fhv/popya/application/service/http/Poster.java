package at.fhv.popya.application.service.http;

import java.util.ArrayList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Message;

public class Poster {
	Message msg;
	private ArrayList<NameValuePair> params;
	private String url;

	/**
	 * Maak een poster aan
	 * 
	 * @param params
	 *            - Lijst met parameters TIP: gebruik BasicNameValuePair als
	 *            input
	 * @param url
	 *            - url om naar toe te sturen
	 */
	public Poster(ArrayList<NameValuePair> params, String url) {
		this.params = params;
		this.url = url;
	}

	/**
	 * Hiermee kun je de post uivoeren
	 * 
	 * @return
	 */
	public String postData() {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = params;
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			ResponseHandler<String> handler = new BasicResponseHandler();
			String response = httpclient.execute(httppost, handler);
			return response;

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		} catch (IOException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		}
		return "";
	}
}
package at.fhv.popya.application.service.ws.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class Poster {
	private final String _jsonParams;
	private final String _url;

	/**
	 * Create a new poster
	 * 
	 * @param jsonParams
	 *            The json encoded parameters
	 * @param url
	 *            The url to the service
	 */
	public Poster(String jsonParams, String url) {
		this._jsonParams = jsonParams;
		this._url = url;
	}

	/**
	 * Post the data
	 * 
	 * @return The json response string
	 */
	public String postData() throws ClientProtocolException {
		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(_url);

		try {
			// Add your data
			StringEntity entity = new StringEntity(_jsonParams, "UTF-8");
			entity.setContentType("application/json");
			httppost.setEntity(entity);

			ResponseHandler<String> handler = new BasicResponseHandler();
			String response = httpclient.execute(httppost, handler);
			return response;

		} catch (UnsupportedEncodingException e) {
			Log.e(getClass().toString(), "Error posting data.", e);
		} catch (IOException e) {
			throw new ClientProtocolException(e);
		}
		return "";
	}
}
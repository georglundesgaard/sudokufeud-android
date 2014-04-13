package no.lundesgaard.sudokufeud.sudokufeud_android.util;

import android.util.Log;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResponseUtil {
	public static String formatResponse(Response response) {
		if (response != null) {
			final TypedInput body = response.getBody();
			StringBuilder sb = new StringBuilder();
			if (body.length() > 0)  {
				try {
					final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(body.in()));
					String line;
					while (true) {
						line = bufferedReader.readLine();
						if (line != null)
							sb.append(line);
						else
							break;
					}
				} catch (IOException e1) {
					Log.e(Constants.TAG, "Failed to read RetrofitError body", e1);
				}

			}
			return response.getStatus() + ":" + response.getReason() + ":" + sb.toString();
		} else
			return "<response == null>";
	}

}

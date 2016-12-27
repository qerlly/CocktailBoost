package qerlly.cocktailboost.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    public String get(String url) {
        String response = null;
        try {
            URL mUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) mUrl.openConnection();
            connection.setRequestMethod("GET");

            InputStream mStream = connection.getInputStream();
            BufferedReader mReader = new BufferedReader(new InputStreamReader(mStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = mReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            response = stringBuilder.toString();

            mStream.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
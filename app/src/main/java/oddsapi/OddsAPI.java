package oddsapi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by john on 1/16/16.
 */
public class OddsAPI {
    private String server;
    private String apiPath;

    public OddsAPI(String server, String apiPath)
    {
        this.setServer(server);
        this.setApiPath(apiPath);
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getURL()
    {
        return server + apiPath;
    }

    public static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static JSONObject GET(String URL)
    {
        String json = "";
        JSONObject obj = null;
        HttpURLConnection urlConnection = null;
        try
        {
            java.net.URL url = new URL(URL);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            json = OddsAPI.convertStreamToString(in);
        }
        catch(IOException e)
        {

        }
        finally
        {
            if(urlConnection != null)
            {
                urlConnection.disconnect();
            }
        }
        try
        {
            obj = new JSONObject(json);
        }
        catch(JSONException e)
        {

        }

        return obj;
    }
}

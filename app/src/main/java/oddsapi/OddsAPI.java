package oddsapi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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

    public static void POST(String URL, HashMap<String, String> params)
    {
        String query;
        JSONObject obj = new JSONObject(params);
        java.net.URL url = null;
        try
        {
            url = new URL(URL);
            query = getQuery(params);

            byte[] postDataBytes = query.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            /*
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            for ( int c = in.read(); c != -1; c = in.read() )
                System.out.print((char)c);
            */
        }
        catch(Exception e)
        {
        }

    }

    private static String getQuery(HashMap<String, String> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, String> entry : params.entrySet())
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}

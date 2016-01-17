package oddsapi;

import com.example.kyle.firstpick.R;

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
public class User {
    private int id;
    private String name;
    private int facebook_id;

    public User(int id, String name, int facebook_id)
    {
        this.id = id;
        this.name = name;
        this.facebook_id = facebook_id;
    }

    public int getID()
    {
        return this.id;
    }

    public String getName()
    {
        return  this.name;
    }

    public int getFbID()
    {
        return this.facebook_id;
    }

    public static User getUserByFbID(OddsAPI api, int FbID)
    {
        int id = 0;
        String name = "";

        //Build API call URL
        StringBuilder urlStr = new StringBuilder();
        urlStr.append(api.getURL());
        urlStr.append("User/users/facebook_id/" + FbID);

        JSONObject obj = OddsAPI.GET(urlStr.toString());

        try
        {
            id = obj.getInt("id");
            name = obj.getString("name");
        }
        catch(JSONException e)
        {

        }

        return new User(id, name, FbID);
    }
}
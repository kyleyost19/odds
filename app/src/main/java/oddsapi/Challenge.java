package oddsapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by john on 1/16/16.
 */
public class Challenge {
    private int id;
    private int challenger;
    private int challengee;
    private int valRange;
    private int challengerVal;
    private int challengeeVal;
    private String challengeDesc;
    private String status;

    public Challenge(int id, int challenger, int challengee, int valRange, int challengerVal, int challengeeVal, String challengeDesc, String status)
    {
        this.setId(id);
        this.setChallenger(challenger);
        this.setChallengee(challengee);
        this.setValRange(valRange);
        this.setChallengerVal(challengerVal);
        this.setChallengeeVal(challengeeVal);
        this.setChallengeDesc(challengeDesc);
        this.setStatus(status);
    }

    public static Challenge getChallenge(OddsAPI api, int id)
    {
        int challenger = 0;
        int challengee = 0;
        int valRange = 0;
        int challengerVal = 0;
        int challengeeVal = 0;
        String challengeDesc = "";
        String status = "";

        //Build API call URL
        StringBuilder urlStr = new StringBuilder();
        urlStr.append(api.getURL());
        urlStr.append("Challenge/challenges/id/" + id);

        JSONObject obj = OddsAPI.GET(urlStr.toString());

        try
        {
            challenger = obj.getInt("challenger");
            challengee = obj.getInt("challengee");
            valRange = obj.getInt("valRange");
            challengerVal = obj.getInt("challengerVal");
            challengeeVal = obj.getInt("challengeeVal");
            challengeDesc = obj.getString("challengeDesc");
            status = obj.getString("status");
        }
        catch(JSONException e)
        {

        }

        return new Challenge(id, challenger, challengee, valRange, challengerVal, challengeeVal, challengeDesc, status);
    }

    public static ArrayList<Challenge> getUserChallenges(OddsAPI api, int userID)
    {
        int id = 0;
        int challenger = 0;
        int challengee = 0;
        int valRange = 0;
        int challengerVal = 0;
        int challengeeVal = 0;
        String challengeDesc = "";
        String status = "";
        ArrayList<Challenge> challenges = new ArrayList<Challenge>();

        //Build API call URL
        StringBuilder urlStr = new StringBuilder();
        urlStr.append(api.getURL());
        urlStr.append("Challenge/challenges/user/" + userID);

        JSONArray arr = OddsAPI.GETArray(urlStr.toString());
        JSONObject obj;

        for(int i = 0; i < arr.length(); i++)
        {
            try
            {
                obj = arr.getJSONObject(i);
                id = obj.getInt("id");
                challenger = obj.getInt("challenger");
                challengee = obj.getInt("challengee");
                valRange = obj.getInt("valRange");
                challengerVal = obj.getInt("challengerVal");
                challengeeVal = obj.getInt("challengeeVal");
                challengeDesc = obj.getString("challengeDesc");
                status = obj.getString("status");
                challenges.add(new Challenge(id, challenger, challengee, valRange, challengerVal, challengeeVal, challengeDesc, status));
            }
            catch(Exception e)
            {

            }
        }

        return challenges;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChallenger() {
        return challenger;
    }

    public void setChallenger(int challenger) {
        this.challenger = challenger;
    }

    public int getChallengee() {
        return challengee;
    }

    public void setChallengee(int challengee) {
        this.challengee = challengee;
    }

    public int getValRange() {
        return valRange;
    }

    public void setValRange(int valRange) {
        this.valRange = valRange;
    }

    public int getChallengerVal() {
        return challengerVal;
    }

    public void setChallengerVal(int challengerVal) {
        this.challengerVal = challengerVal;
    }

    public int getChallengeeVal() {
        return challengeeVal;
    }

    public void setChallengeeVal(int challengeeVal) {
        this.challengeeVal = challengeeVal;
    }

    public String getChallengeDesc() {
        return challengeDesc;
    }

    public void setChallengeDesc(String challengeDesc) {
        this.challengeDesc = challengeDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
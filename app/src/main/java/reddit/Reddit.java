package reddit;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Charlotte on 23/04/2017.
 */

public class Reddit {
    private static String frontPageURL = "{https://www.reddit.com/.json}";

    public static String redditFrontAll(Context context) throws JSONException {
        JSONObject response = new JSONObject(frontPageURL);

        Log.d("RESPONSE", "got here");
        JSONObject data = response.getJSONObject("data");
        JSONArray someShit = data.getJSONArray("children");

        for (int i = 0; i < someShit.length(); i++) {
            JSONObject something = someShit.getJSONObject(i);
            String title = something.getString("title");
            String score = something.getString("score");
            Log.d("REDDIT_TITLE", String.valueOf(title));
            Log.d("REDDIT_SCORE", String.valueOf(score));
        }

        return "hello";
    }
}

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
        JSONArray response = new JSONArray(frontPageURL);

        Log.d("RESPONSE", "got here");
        //JSONObject data = response.getJSONObject("data");
        //JSONArray someShit = data.getJSONArray("children");
        JSONParser parser = new JSONParser(response);

        return "hello";
    }
}

package reddit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Charlotte on 23/04/2017.
 */

public class JSONParser {
    public static JSONArray jsonArray = null;

    public JSONParser(JSONArray jsonArray) throws JSONException {
        this.jsonArray = jsonArray;
    }



    public static String getTitles() throws JSONException {
        JSONObject obj = null;
        String title = "";
        for (int i = 0; i < jsonArray.length(); i++) {
            obj = jsonArray.getJSONObject(i);
            title += obj.getString("data");
        }
        return title;
    }


}


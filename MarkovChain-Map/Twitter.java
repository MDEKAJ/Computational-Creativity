import com.temboo.core.TembooSession;
import com.temboo.Library.Twitter.Timelines.UserTimeline;
import com.temboo.Library.Twitter.Timelines.UserTimeline.UserTimelineInputSet;
import com.temboo.Library.Twitter.Timelines.UserTimeline.UserTimelineResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.io.IOException;

public class Twitter {
    public static String getTweetsForUser(String screenName) throws IOException {
        String s = "";

        // Instantiate the Choreo, using a previously instantiated TembooSession object, eg:
        try{
            TembooSession session = new TembooSession("triss", "myFirstApp", "dsLE57s1wXRodpJqTYpUkUfY2S0iEv4i");
            UserTimeline userTimelineChoreo = new UserTimeline(session);
            UserTimelineInputSet userTimelineInputs = userTimelineChoreo.newInputSet();
            userTimelineInputs.setCredential("triss");
            userTimelineInputs.set_Count("200");
            UserTimelineResultSet userTimelineResults = userTimelineChoreo.execute(userTimelineInputs);

            JSONArray results = new JSONArray(userTimelineResults.get_Response());

            System.out.println(results.length());
            for(int i = 0; i < results.length(); i++) {
                s += results.getJSONObject(i).getString("text") + " ";
            }
        } catch(Exception e) {
            throw(new IOException("Failed to get tweets for " + screenName, e));
        }

        return s;
    }
}

package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.runtime.CloudDB;
import java.util.List;
import org.json.JSONException;
import redis.clients.jedis.JedisPubSub;

public class CloudDBJedisListener extends JedisPubSub {
    private static String LOG_TAG = "CloudDB";
    public CloudDB cloudDB;
    private Thread hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Thread.currentThread();

    public void onSubscribe(String str, int i) {
    }

    public CloudDBJedisListener(CloudDB cloudDB2) {
        this.cloudDB = cloudDB2;
    }

    public void onMessage(String str, String str2) {
        try {
            List list = (List) JsonUtil.getObjectFromJson(str2, false);
            String str3 = (String) list.get(0);
            for (Object next : (List) list.get(1)) {
                String jsonRepresentationIfValueFileName = JsonUtil.getJsonRepresentationIfValueFileName(this.cloudDB.getForm(), next);
                if (jsonRepresentationIfValueFileName == null) {
                    this.cloudDB.DataChanged(str3, next);
                } else {
                    this.cloudDB.DataChanged(str3, jsonRepresentationIfValueFileName);
                }
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "onMessage: JSONException", e);
            CloudDB cloudDB2 = this.cloudDB;
            cloudDB2.CloudDBError("System Error: " + e.getMessage());
        }
    }

    public void terminate() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.interrupt();
    }
}

package com.example.lucerito.asvproject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.strongloop.android.loopback.RestAdapter;

public class ASVApplication extends Application {

    private static ASVApplication sInstance;
    private static ASVApi sASVApi;
    private static SharedPreferences sharedPreferences;


    private static final String PREFERENCES = "preferences";
    private static final int PREFERENCE_MODE_PRIVATE = 0;
    private static final String PREFERENCE_FCM_API_TOKEN = "fcm_token";
    private static final String PREFERENCE_ICON_BADGE = "icon_badge";


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sASVApi = new ASVApi();
        sharedPreferences = getSharedPreferences(PREFERENCES, PREFERENCE_MODE_PRIVATE);
    }

    public static synchronized ASVApplication getInstance() {
        return sInstance;
    }

    public static ASVApi getASVApi() {
        return sASVApi;
    }

    public static void setASVApi(ASVApi asvApi) {
        sASVApi = asvApi;
    }

    public static void saveFCMToken(Context context, String token) {
        saveStringPreferences(context, PREFERENCE_FCM_API_TOKEN, token);
    }

    public static String getFCMToken() {
        return getStringPreferences(PREFERENCE_FCM_API_TOKEN);
    }

    public static void saveIconBadge(Context context, int badge) {
        saveIntPreferences(context, PREFERENCE_ICON_BADGE, badge);
    }

    public static int getIconBadge() {
        return getIntPreferences(PREFERENCE_ICON_BADGE);
    }

    public static void saveStringPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCES, PREFERENCE_MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringPreferences(String key) {
        return sharedPreferences.getString(key, "");
    }

    public static void saveIntPreferences(Context context, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCES, PREFERENCE_MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getIntPreferences(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public static void clearSharedPreferences(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCES, PREFERENCE_MODE_PRIVATE).edit();
        editor.clear().apply();
    }

    /*
    RestAdapter adapter;

    public RestAdapter getLoopBackAdapter() {
        if (adapter == null) {
            // Instantiate the shared RestAdapter. In most circumstances, you'll do this only once;
            // putting that reference in a singleton is recommended for the sake of simplicity.
            // However, some applications will need to talk to more than one server - create as many Adapters as you need.
         ///   adapter = new RestAdapter(getApplicationContext(), "http://10.0.2.2:3010/api/");
            adapter = new RestAdapter(getApplicationContext(), "http://127.0.0.1:3000/api");
        }
        return adapter;
    }
*/
}

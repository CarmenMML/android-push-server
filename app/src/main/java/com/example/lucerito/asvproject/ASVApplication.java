package com.example.lucerito.asvproject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.strongloop.android.loopback.RestAdapter;

public class ASVApplication extends Application {

    private static ASVApplication sInstance;
    private static ASVApi sASVApi;
   /* private static ExchangeManager sExchangeManager;
    private static WarriorModel mWarrior;*/
    private static SharedPreferences sharedPreferences;


    private static final String PREFERENCES = "preferences";
    private static final int PREFERENCE_MODE_PRIVATE = 0;
    private static final String PREFERENCE_WARRIOR_NAME = "warrior_name";
    private static final String PREFERENCE_WARRIOR_SURNAME = "warrior_surname";
    private static final String PREFERENCE_WARRIOR_DATE = "warrior_date";
    private static final String PREFERENCE_WARRIOR_CURRENCY = "warrior_currency";


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sASVApi = new ASVApi();
       /// sExchangeManager = new ExchangeManager();
      ///  mWarrior = new WarriorModel();
        sharedPreferences = getSharedPreferences(PREFERENCES, PREFERENCE_MODE_PRIVATE);
        //createDatabase();
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

   /* public static ExchangeManager getExchangeManager() {
        return sExchangeManager;
    }

    public static void setExchangeManager(ExchangeManager sExchangeManager) {
        DragonRidesApplication.sExchangeManager = sExchangeManager;
    }

    public static WarriorModel getWarrior() {
        return mWarrior;
    }

    public static void setWarrior(WarriorModel mWarrior) {
        DragonRidesApplication.mWarrior = mWarrior;
    }
*/



    /*
    public static void removeFloatPreferences(Context context, String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCES, PREFERENCE_MODE_PRIVATE).edit();
        editor.remove(key);
        editor.apply();
    }
    */

    public static void saveFloatPreferences(Context context, String key, float value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCES, PREFERENCE_MODE_PRIVATE).edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static float getFloatPreferences(String key) {
        return sharedPreferences.getFloat(key, 1);
    }

    public static void saveWarriorPreferences(Context context, String name, String surname, String date, String currency) {
        saveStringPreferences(context, PREFERENCE_WARRIOR_NAME, name);
        saveStringPreferences(context, PREFERENCE_WARRIOR_SURNAME, surname);
        saveStringPreferences(context, PREFERENCE_WARRIOR_DATE, date);
        saveStringPreferences(context, PREFERENCE_WARRIOR_CURRENCY, currency);
    }

    public static String getUserPreferencesName() {
        return getStringPreferences(PREFERENCE_WARRIOR_NAME);
    }

    public static String getUserPreferencesSurname() {
        return getStringPreferences(PREFERENCE_WARRIOR_SURNAME);
    }

    public static String getUserPreferencesDate() {
        return getStringPreferences(PREFERENCE_WARRIOR_DATE);
    }

    public static String getUserPreferencesCurrency() {
        return getStringPreferences(PREFERENCE_WARRIOR_CURRENCY);
    }

    public static void saveStringPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCES, PREFERENCE_MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringPreferences(String key) {
        return sharedPreferences.getString(key, "");
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

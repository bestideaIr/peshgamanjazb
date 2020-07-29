package ir.b3stidea.peshgamanjazb;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private static SharedPreferences mSharedPref;

    /////////////////////////////////////////////////
    // public static SharedPreferences preferences;
    public static String PREFERENCES_LOGINPAGE = "pref_loginpage";
    public static String KEY_MOBILE = "mobile";
    public static String KEY_PASSWORD = "password";
    public static String KEY_NAME = "name";
    public static String KEY_FAMILY = "family";
    public static String KEY_IMAGE = "image";
    public static String KEY_WALLET = "wallet";

    public static SharedPreferences.Editor editor;
    //////////////////////////////////////////////////

    private SharedPref() {

    }

    public static void init(Context context) {
        if (mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static String read(String key) {
        return mSharedPref.getString(key, "");
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static Integer read(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static void write(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).commit();
    }

    public static void clear() {
        SharedPreferences.Editor spreferencesEditor = mSharedPref.edit();
        spreferencesEditor.clear().commit();
    }
}
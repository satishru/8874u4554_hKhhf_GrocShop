package app.groceryapp.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import app.groceryapp.di.PreferenceInfo;

public class AppPreferencesHelper implements PreferencesHelper {

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    private void setPrefValue(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
    }

    private void setPrefValue(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
    }

    private void setPrefValue(String key, Long value) {
        mPrefs.edit().putLong(key, value).apply();
    }

    private void setPrefValue(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
    }

    private String getPrefValue(String key) {
        return mPrefs.getString(key, null);
    }

    private String getPrefValue(String key, String defaultValue) {
        return  mPrefs.getString(key, defaultValue);
    }

    @Override
    public void clearPreference() {
        mPrefs.edit().clear().apply();
    }

    @Override
    public String getAccessToken() {
        return getPrefValue(PREF_KEY_ACCESS_TOKEN);
    }

    @Override
    public void setAccessToken(String accessToken) {
        setPrefValue(PREF_KEY_ACCESS_TOKEN, accessToken);
    }

    @Override
    public Long getCurrentUserId() {
        return mPrefs.getLong(PREF_KEY_LOGGED_IN_USER_ID, 0);
    }

    @Override
    public void setCurrentUserId(Long userId) {
        long id = userId == null ? 0 : userId;
        setPrefValue(PREF_KEY_LOGGED_IN_USER_ID, id);
    }

    @Override
    public String getAliasKey() {
        return getPrefValue(PREF_KEY_ALIAS_KEY);
    }

    @Override
    public void setAliasKey(String alias) {
        setPrefValue(PREF_KEY_ALIAS_KEY, alias);
    }

    @Override
    public String getUserInfo() {
        return getPrefValue(REF_KEY_SAVED_USER_INFO);
    }

    @Override
    public void setUserInfo(String userInfo) {
        setPrefValue(REF_KEY_SAVED_USER_INFO, userInfo);
    }
}

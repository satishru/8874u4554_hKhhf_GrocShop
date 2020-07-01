package app.groceryapp.data.local.prefs;

public interface PreferencesHelper {

    String PREF_KEY_ACCESS_TOKEN      = "PREF_KEY_ACCESS_TOKEN";
    String PREF_KEY_LOGGED_IN_USER_ID = "PREF_KEY_LOGGED_IN_USER_ID";
    String PREF_KEY_ALIAS_KEY         = "PREF_KEY_ALIAS_KEY";
    String REF_KEY_SAVED_USER_INFO    = "REF_KEY_SAVED_USER_INFO";

    void clearPreference();

    String getAccessToken();
    void setAccessToken(String accessToken);

    Long getCurrentUserId();
    void setCurrentUserId(Long userId);

    String getAliasKey();
    void setAliasKey(String alias);

    String getUserInfo();
    void setUserInfo(String userInfo);
}

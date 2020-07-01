package app.groceryapp.data;

import android.content.Context;

import com.google.gson.Gson;

import java.util.List;

import app.groceryapp.data.local.db.DbHelper;
import app.groceryapp.data.local.prefs.PreferencesHelper;
import app.groceryapp.data.model.db.GroceryAppData;
import app.groceryapp.data.remote.ApiHelper;
import io.reactivex.Observable;

public abstract class PreAppDataManager implements DataManager {
    protected final ApiHelper mApiHelper;

    protected final Context mContext;

    protected final DbHelper mDbHelper;

    protected final Gson mGson;

    protected final PreferencesHelper mPreferencesHelper;

    public PreAppDataManager(Context context, DbHelper dbHelper,
                             PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    /**
     * DbHelper Call Backs
     *
     * @return PayeeData List
     */
    @Override
    public Observable<List<GroceryAppData>> getGroceryAppData() {
        return mDbHelper.getGroceryAppData();
    }

    @Override
    public Observable<Boolean> isGroceryAppDataEmpty() {
        return mDbHelper.isGroceryAppDataEmpty();
    }

    @Override
    public Observable<Boolean> deleteGroceryAppData() {
        return mDbHelper.deleteGroceryAppData();
    }

    @Override
    public Observable<Boolean> insertGroceryAppData(GroceryAppData data) {
        return mDbHelper.insertGroceryAppData(data);
    }
    /* DbHelper Call Backs Ends */

    /**
     * DataManager Call Backs
     */
    @Override
    public Observable<Boolean> seedGroceryAppData() {
        return mDbHelper.isGroceryAppDataEmpty();
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setUserId(userId);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            Long userId,
            String userName,
            String email) {
        setAccessToken(accessToken);
        setCurrentUserId(userId);
        updateApiHeader(userId, accessToken);
    }
    /* DataManager Call Backs Ends */

    @Override
    public void clearPreference() {
        mPreferencesHelper.clearPreference();
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public String getAliasKey() {
        return mPreferencesHelper.getAliasKey();
    }

    @Override
    public void setAliasKey(String alias) {
        mPreferencesHelper.setAliasKey(alias);
    }

    @Override
    public String getUserInfo() {
        return mPreferencesHelper.getUserInfo();
    }

    @Override
    public void setUserInfo(String userInfo) {
        mPreferencesHelper.setUserInfo(userInfo);
    }
}

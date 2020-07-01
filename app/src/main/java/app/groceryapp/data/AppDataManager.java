package app.groceryapp.data;

import android.content.Context;

import com.google.gson.Gson;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.groceryapp.data.local.db.DbHelper;
import app.groceryapp.data.local.prefs.PreferencesHelper;
import app.groceryapp.data.model.api.request.BaseRequest;
import app.groceryapp.data.model.api.response.category.CategoryResponse;
import app.groceryapp.data.remote.ApiHeader;
import app.groceryapp.data.remote.ApiHelper;
import io.reactivex.Single;

@Singleton
public class AppDataManager extends PreAppDataManager {

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper,
                          PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        super(context, dbHelper, preferencesHelper, apiHelper, gson);
    }

    /**
     * ApiHelper Call Backs
     */
    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Single<Object> getPayeeDataApiCall(BaseRequest request) {
        return mApiHelper.getPayeeDataApiCall(request);
    }

    @Override
    public Single<CategoryResponse> getCategory(Map<String, String> params) {
        return mApiHelper.getCategory(params);
    }
    /* ApiHelper Call Backs Ends */
}

package app.groceryapp.data.remote;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.groceryapp.data.model.api.request.BaseRequest;
import app.groceryapp.data.model.api.response.category.CategoryResponse;
import app.groceryapp.data.remote.retrofit.GroceryAppApi;
import io.reactivex.Single;
import timber.log.Timber;

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    GroceryAppApi groceryAppApi;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<Object> getPayeeDataApiCall(BaseRequest request) {
        if(groceryAppApi != null) {
            Timber.d("PAYBEE API");
        } else {
            Timber.d("PAYBEE API NULL");
        }
        return groceryAppApi.instantDonation(new HashMap<>());
        /*
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_DATA)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addPathParameter(request)
                .build()
                .getObjectSingle(PayeeResponse.class);
         */
    }

    @Override
    public Single<CategoryResponse> getCategory(Map<String, String> params) {
        return groceryAppApi.getCategory(params);
    }

    /*
    @Override
    public Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_FACEBOOK_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GOOGLE_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGOUT)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(LogoutResponse.class);
    }

    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<BlogResponse> getBlogApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BLOG)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(BlogResponse.class);
    }

    @Override
    public Single<OpenSourceResponse> getOpenSourceApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_OPEN_SOURCE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(OpenSourceResponse.class);
    }
    */
}

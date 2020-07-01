package app.groceryapp.data.remote.retrofit;

import java.util.Map;

import app.groceryapp.data.model.api.response.category.CategoryResponse;
import io.reactivex.Single;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface GroceryAppApi {

    @FormUrlEncoded
    @POST("/api/instantDonate")
    Single<Object> instantDonation(@FieldMap Map<String, String> params);

    @GET("api/v1/getCategory")
    Single<CategoryResponse> getCategory(@QueryMap Map<String, String> options);
}

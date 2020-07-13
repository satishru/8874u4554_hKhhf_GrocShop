package app.groceryapp.data.remote;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.groceryapp.data.model.api.response.category.CategoryResponse;
import app.groceryapp.data.model.api.response.product.ProductResponse;
import app.groceryapp.data.remote.retrofit.GroceryAppApi;
import io.reactivex.Single;

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
    public Single<CategoryResponse> getCategory(Map<String, String> params) {
        return groceryAppApi.getCategory(params);
    }

    @Override
    public Single<ProductResponse> getProducts(Map<String, String> params) {
        return groceryAppApi.getProducts(params);
    }
}

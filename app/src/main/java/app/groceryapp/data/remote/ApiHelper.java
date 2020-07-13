package app.groceryapp.data.remote;

import java.util.Map;

import app.groceryapp.data.model.api.response.category.CategoryResponse;
import app.groceryapp.data.model.api.response.product.ProductResponse;
import io.reactivex.Single;

public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<CategoryResponse> getCategory(Map<String, String> params);

    Single<ProductResponse> getProducts(Map<String, String> params);
}

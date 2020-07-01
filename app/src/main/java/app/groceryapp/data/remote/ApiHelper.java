package app.groceryapp.data.remote;

import java.util.Map;

import app.groceryapp.data.model.api.request.BaseRequest;
import app.groceryapp.data.model.api.response.category.CategoryResponse;
import io.reactivex.Single;

public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<Object> getPayeeDataApiCall(BaseRequest request);

    Single<CategoryResponse> getCategory(Map<String, String> params);
}

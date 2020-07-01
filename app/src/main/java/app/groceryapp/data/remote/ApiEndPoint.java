package app.groceryapp.data.remote;

import app.groceryapp.BuildConfig;

public final class ApiEndPoint {

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

    private static String makeUrl(String endPoint) {
        return BuildConfig.BASE_URL + endPoint;
    }

    public static final String ENDPOINT_GET_DATA  = makeUrl("bins/sd09o?pretty=1");
}

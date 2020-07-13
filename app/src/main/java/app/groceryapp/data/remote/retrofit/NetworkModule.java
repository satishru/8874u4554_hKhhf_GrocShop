package app.groceryapp.data.remote.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import app.groceryapp.BuildConfig;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModule {
    private final static String BASE_URL = "http://192.168.0.105/PROJECTS/GROCERY/Ecom/";//BuildConfig.BASE_URL;
    private static Retrofit retrofit = null;

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            okHttpClient.addNetworkInterceptor(httpLoggingInterceptor);
        }
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Timber.d(message)).setLevel(
                HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    GroceryAppApi providePayBeeApi(Retrofit retrofit) {
        return retrofit.create(GroceryAppApi.class);
    }
}

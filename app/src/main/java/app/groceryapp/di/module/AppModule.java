package app.groceryapp.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.inject.Singleton;

import androidx.room.Room;
import app.groceryapp.BuildConfig;
import app.groceryapp.data.AppDataManager;
import app.groceryapp.data.DataManager;
import app.groceryapp.data.local.db.AppDatabase;
import app.groceryapp.data.local.db.AppDbHelper;
import app.groceryapp.data.local.db.DbHelper;
import app.groceryapp.data.local.prefs.AppPreferencesHelper;
import app.groceryapp.data.local.prefs.PreferencesHelper;
import app.groceryapp.data.remote.ApiHeader;
import app.groceryapp.data.remote.ApiHelper;
import app.groceryapp.data.remote.AppApiHelper;
import app.groceryapp.di.ApiInfo;
import app.groceryapp.di.DatabaseInfo;
import app.groceryapp.di.PreferenceInfo;
import app.groceryapp.utils.constants.AppConstants;
import app.groceryapp.utils.rx.AppSchedulerProvider;
import app.groceryapp.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class,
                dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}

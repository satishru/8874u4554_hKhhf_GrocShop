package app.groceryapp.data.local.db;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.groceryapp.data.model.db.GroceryAppData;
import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<Boolean> isGroceryAppDataEmpty() {
        return mAppDatabase.groceryAppDataDao().getPayeeData()
                .flatMapObservable(data -> Observable.just(data.isEmpty()));
    }

    @Override
    public Observable<List<GroceryAppData>> getGroceryAppData() {
        return mAppDatabase.groceryAppDataDao().getPayeeData().toObservable();
    }

    @Override
    public Observable<Boolean> deleteGroceryAppData() {
        return Observable.fromCallable(() -> {
            mAppDatabase.groceryAppDataDao().deleteAll();
            return true;
        });
    }

    @Override
    public Observable<Boolean> insertGroceryAppData(GroceryAppData data) {
        return Observable.fromCallable(() -> {
            mAppDatabase.groceryAppDataDao().insert(data);
            return true;
        });
    }
}

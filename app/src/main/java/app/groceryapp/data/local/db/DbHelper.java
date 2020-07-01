package app.groceryapp.data.local.db;

import java.util.List;

import app.groceryapp.data.model.db.GroceryAppData;
import io.reactivex.Observable;

public interface DbHelper {

    Observable<Boolean> isGroceryAppDataEmpty();

    Observable<Boolean> insertGroceryAppData(GroceryAppData data);

    Observable<List<GroceryAppData>> getGroceryAppData();

    Observable<Boolean> deleteGroceryAppData();
}

package app.groceryapp.data;

import app.groceryapp.data.local.db.DbHelper;
import app.groceryapp.data.local.prefs.PreferencesHelper;
import app.groceryapp.data.remote.ApiHelper;
import io.reactivex.Observable;

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    Observable<Boolean> seedGroceryAppData();

    void updateApiHeader(Long userId, String accessToken);

    void updateUserInfo(
        String accessToken,
        Long userId,
        String userName,
        String email);
}

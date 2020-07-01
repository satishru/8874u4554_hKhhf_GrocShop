package app.groceryapp.data.local.db.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import app.groceryapp.data.model.db.GroceryAppData;
import app.groceryapp.utils.constants.AppConstants;
import io.reactivex.Single;

@Dao
public interface GroceryAppDataDao {

    @Query("DELETE FROM "+ AppConstants.DB_TABLE_DATA)
    void deleteAll();

    @Query("SELECT * FROM "+ AppConstants.DB_TABLE_DATA)
    Single<List<GroceryAppData>> getPayeeData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GroceryAppData data);
}

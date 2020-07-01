package app.groceryapp.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import app.groceryapp.data.local.db.dao.GroceryAppDataDao;
import app.groceryapp.data.model.db.GroceryAppData;

@Database(entities = {GroceryAppData.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GroceryAppDataDao groceryAppDataDao();
}
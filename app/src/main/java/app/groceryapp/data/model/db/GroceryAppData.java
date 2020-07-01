package app.groceryapp.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import app.groceryapp.utils.constants.AppConstants;

@Entity(tableName = AppConstants.DB_TABLE_DATA)
public class GroceryAppData {

    @PrimaryKey
    public Long id;

    @ColumnInfo(name = "json_data")
    public String jsonData;

    @ColumnInfo(name = "created_at")
    public String createdAt;

    @ColumnInfo(name = "updated_at")
    public String updatedAt;
}
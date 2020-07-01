package app.groceryapp.utils.constants;

public final class AppConstants {

    private AppConstants() {
        // This utility class is not publicly instantiable
    }

    public static final int LOCATION_UPDATE_TIME = 15; // Seconds
    public static final int LOCATION_UPDATE_DISTANCE = 1;  // Meter

    // Pref Constants
    public static final String PREF_NAME = "paybee_io_pref";

    // DB Constants
    public static final String DB_NAME = "paybee_io.db";
    public static final String DB_TABLE_DATA = "paybee_io";

    // Home Side Menu List Item
    public static String MENU_MANAGE_ACCOUNT = "Manage Account";
    public static String MENU_TRANSACTION = "Transactions";
    public static String MENU_RECURRING = "Recurring";
    public static String MENU_VOLUNTEER = "Volunteer";

    public static final String DATE_FORMAT_YYYY_MM_DD  = "yyyy-dd-MM";
    public static final String DATE_FORMAT_DD_MMM_YYYY = "dd MMM yyyy";
}
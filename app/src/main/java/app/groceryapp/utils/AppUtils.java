package app.groceryapp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

import app.groceryapp.BuildConfig;
import app.groceryapp.data.model.api.response.category.Category;
import app.groceryapp.data.model.api.response.category.SubCategory;
import app.groceryapp.enums.HomeMenuEnum;

public final class AppUtils {
    private AppUtils() {
        // This utility class is not publicly instantiable
    }

    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    public static List<HomeMenuEnum> getSideMenuList() {
        List<HomeMenuEnum> sideMenuList = new ArrayList<>();
        sideMenuList.add(HomeMenuEnum.MANAGE_ACCOUNT);
        sideMenuList.add(HomeMenuEnum.TRANSACTION);
        sideMenuList.add(HomeMenuEnum.RECURRING);
        // Check User Info and set Volunteer
        sideMenuList.add(HomeMenuEnum.VOLUNTEER);
        return sideMenuList;
    }

    public static String getSunCategoryNames(Category category) {
        StringBuilder sub_category_names = new StringBuilder();
        for (SubCategory subCategory : category.getSubCategoryList()) {
            if(sub_category_names.toString().equals("")) {
                sub_category_names = new StringBuilder(subCategory.getSubCategoryName());
            } else {
                sub_category_names.append(",").append(subCategory.getSubCategoryName());
            }
        }
        return sub_category_names.toString();
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}

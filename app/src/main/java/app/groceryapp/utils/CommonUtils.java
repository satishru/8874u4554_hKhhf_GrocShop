package app.groceryapp.utils;

import android.text.Html;
import android.util.Base64;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import app.groceryapp.BuildConfig;
import app.groceryapp.utils.constants.AppConstants;

public final class CommonUtils {

    private final static String CHAR_SET_NAME = "UTF-8";

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static String toHtml(String item) {
        if (isNullOrEmpty(item)) {
            return "";
        }
        return Html.fromHtml(item.trim()).toString().trim();
    }

    public static boolean isNullOrEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

    private static Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat(AppConstants.DATE_FORMAT_YYYY_MM_DD,
                    Locale.getDefault()).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String formatDate(Date date) {
        if (date != null) {
            return new SimpleDateFormat(AppConstants.DATE_FORMAT_DD_MMM_YYYY,
                    Locale.getDefault()).format(date);
        }
        return "";
    }

    private static Date getDateWithoutTime() {
        SimpleDateFormat formatter = new SimpleDateFormat(AppConstants.DATE_FORMAT_YYYY_MM_DD,
                Locale.getDefault());
        try {
            return parseDate(formatter.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isDateGreaterOrEqualToday(String eventEndDateStr) {
        Date todayDate = getDateWithoutTime();
        Date eventEndDate = parseDate(eventEndDateStr);
        if (eventEndDate != null && todayDate != null) {
            return eventEndDate.compareTo(todayDate) > -1;
        }
        return false;
    }

    public static String formatEventDate(String dateStr1, String dateStr2) {
        String formatted_date = dateStr1 + "-" + dateStr2;
        Date date1 = parseDate(dateStr1);
        Date date2 = parseDate(dateStr2);
        if (date1 != null && date2 != null) {
            if (date1.compareTo(date2) == 0) {
                formatted_date = formatDate(date1);
            } else {
                formatted_date = formatDate(date1) + " - " + formatDate(date2);
            }
        }
        return formatted_date;
    }

    public static String convertObjToJson(Object object) {
        if (object == null) {
            return "";
        }
        return new Gson().toJson(object);
    }

    public static <T> T convertJsonToObj(String json, Class<T> objClass) {
        try {
            return new Gson().fromJson(json, objClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String stringToBase64(final String json) {
        if (isNullOrEmpty(json)) {
            return "";
        }
        try {
            return Base64.encodeToString(json.getBytes(CHAR_SET_NAME), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String base64ToString(String base64) throws UnsupportedEncodingException {
        if (isNullOrEmpty(base64)) {
            return "";
        }
        return new String(Base64.decode(base64, Base64.DEFAULT), CHAR_SET_NAME);
    }

    /**
     * Converts double to string value with two digits after decimal
     *
     * @param val Value
     * @return Formatted value
     */
    private static String convertDecimalFormat(double val) {
        DecimalFormat formatter = new DecimalFormat("##,###0.00");
        String value = formatter.format(val);
        if (value.endsWith(".00")) {
            return value.replace(".00", "");
        }
        if (value.endsWith(".0")) {
            return value.replace(".0", "");
        }
        value = value.replace(",", "");
        return value;
    }

    /**
     * This method is to create mock list during development if list size is less or =2  to test
     *
     * @param list          Original List
     * @param increase_size Increase list size
     * @return mock list
     */
    public static <E> List<E> mockList(List<E> list, int increase_size) {
        try {
            if (BuildConfig.DEBUG) {
                if (list.size() > 0 && list.size() <= 2 && list.get(0) != null) {
                    List<E> mockList = new ArrayList<>(list);
                    E ele = list.get(0);
                    for (int i = 0; i < increase_size; i++) {
                        mockList.add(ele);
                    }
                    return mockList;
                }
                return list;
            }
            return list;
        } catch (Exception e) {
            return list;
        }
    }
}

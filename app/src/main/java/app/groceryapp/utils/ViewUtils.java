package app.groceryapp.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import androidx.core.content.ContextCompat;
import app.groceryapp.R;

public final class ViewUtils {

    public static final int TOAST_GRAVITY = Gravity.CENTER;

    private ViewUtils() {
        // This class is not publicly instantiable
    }

    public static void showToast(Context context, String message) {
        if (context != null) {
            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(TOAST_GRAVITY, 0, 0);
            toast.show();
        }
    }

    public static void changeIconDrawableToGray(Context context, Drawable drawable) {
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(
                    ContextCompat.getColor(context, R.color.colorAccent),
                    PorterDuff.Mode.SRC_ATOP);
        }
    }

    @NotNull
    public static ProgressDialog showLoader(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.layout_progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}

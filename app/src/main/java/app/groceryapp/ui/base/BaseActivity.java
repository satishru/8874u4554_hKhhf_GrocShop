package app.groceryapp.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import app.groceryapp.R;
import app.groceryapp.ViewModelProviderFactory;
import app.groceryapp.data.local.prefs.AppPreferencesHelper;
import app.groceryapp.utils.NetworkUtils;
import app.groceryapp.utils.ViewUtils;
import dagger.android.AndroidInjection;

public abstract class BaseActivity<V extends BaseViewModel<?>> extends AppCompatActivity implements BaseFragment.Callback, BaseNavigator {

    @Inject
    public ViewModelProviderFactory factory;

    @Inject
    public AppPreferencesHelper appPreferencesHelper;

    private ProgressDialog mProgressDialog;

    public abstract void setViewBinding();
    public abstract void setViewModel();
    public abstract void setNavigator();
    public abstract void handleErrorLayout(String errorTitle, String errorMessage);

    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {
    }

    @SuppressWarnings("unchecked")
    protected ViewModel prepareViewModel(@NonNull Class viewModelCall) {
        return new ViewModelProvider(this, factory).get(viewModelCall);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        setOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setViewBinding();
        setViewModel();
        setNavigator();
    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    public void setOrientation(int orientation) {
        setRequestedOrientation(orientation);
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
            checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    public void setToolBar(Toolbar toolBar, int title_id, boolean homeAsUpEnabled) {
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title_id);
            getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        }
    }

    public void setToolBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void openActivityOnTokenExpire() {
        //startActivity(LoginActivity.newIntent(this));
        //finish();
    }

    public void showLoading(boolean isShow) {
        hideLoading();
        if (isShow) {
            mProgressDialog = ViewUtils.showLoader(this);
        }
    }

    private void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(ViewUtils.TOAST_GRAVITY, 0, 0);
        toast.show();
    }

    public String getStringFromId(int string_id) {
        return getString(string_id);
    }

    public void loadFragment(Fragment fragment, int container, boolean addToBackStack, boolean animate) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (animate) {
                transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left,
                    R.anim.slide_from_left, R.anim.slide_right);
            }
            transaction.replace(container, fragment);
            if (addToBackStack) {
                transaction.addToBackStack(null);
            }
            transaction.commitAllowingStateLoss();
        }
    }

    public void reCreateActivityWithoutAnimation() {
        startActivity(getIntent());
        finish();
        overridePendingTransition(0, 0);
    }

    public void reCreateActivityWithAnimation() {
        startActivity(getIntent());
        exitActivityWithAnimation();
    }

    public void startActivityWithAnimation(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void exitActivityWithAnimation() {
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    public int getBackStackEntryCount() {
        return getSupportFragmentManager().getBackStackEntryCount();
    }

    public void popBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    public void openInChromeOrOtherBrowser(Context context, String url) {
        Uri uri = Uri.parse("googlechrome://navigate?url=" + url);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        if (i.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(i);
        } else {
            openInOtherBrowser(url);
        }
    }

    private void openInOtherBrowser(String url) {
        Intent browserIntent =
            Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER);
        browserIntent.setData(Uri.parse(url));
        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            if (browserIntent.resolveActivity(getPackageManager()) != null) {
                //startActivity(browserIntent);
                startActivity(Intent.createChooser(browserIntent, "Open with"));
            } else {
                showToast("No app's found to open this link");
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            showToast("No app's found to open this link");
        }
    }

    // BaseNavigator CallBacks
    @Override
    public void displayError(Throwable throwable) {
        if (throwable != null) {
            throwable.fillInStackTrace();
            showToast(throwable.getLocalizedMessage());
        }
    }

    @Override
    public boolean isNetWorkConnected() {
        return NetworkUtils.isNetworkConnected(this);
    }

    @Override
    public void displayErrorMessage(int errorCode, String errorMessage) {
        //TODO handle error code here
        showToast(errorMessage);
    }

    @Override
    public void showLoader(boolean isShowLoader) {
        showLoading(isShowLoader);
    }

    @Override
    public void showErrorLayout(String errorTitle, String errorMessage) {
        handleErrorLayout(errorTitle, errorMessage);
    }
}


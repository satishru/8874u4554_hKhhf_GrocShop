package app.groceryapp.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import app.groceryapp.ViewModelProviderFactory;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<V extends BaseViewModel<?>> extends Fragment implements
    BaseNavigator {

    @Inject
    public ViewModelProviderFactory factory;

    private BaseActivity<?> mActivity;

    public abstract void setViewModel();
    public abstract void setNavigator();

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity<?> activity = (BaseActivity<?>) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @SuppressWarnings("unchecked")
    protected ViewModel prepareViewModel(@NonNull Class viewModelCall) {
      return new ViewModelProvider(this, factory).get(viewModelCall);
    }

    @Override
    public void onDetach() {
        showLoading(false);
        mActivity = null;
        super.onDetach();
    }

    protected BaseActivity<?> getBaseActivity() {
        return mActivity;
    }

    public ActionBar getBaseSupportActionBar() {
        if (mActivity != null) {
            return mActivity.getSupportActionBar();
        }
        return null;
    }

    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public void openActivityOnTokenExpire() {
        if (mActivity != null) {
            mActivity.openActivityOnTokenExpire();
        }
    }

    protected void startActivityWithAnimation(Intent intent) {
        if (mActivity != null) {
            mActivity.startActivityWithAnimation(intent);
        }
    }

    public void exitActivityWithAnimation() {
        if (mActivity != null) {
            mActivity.exitActivityWithAnimation();
        }
    }

    private void performDependencyInjection() {
        //AndroidSupportInjection.inject(this);
    }

    private void showLoading(boolean isShow) {
        if (mActivity != null) {
            mActivity.showLoading(isShow);
        }
    }

    public void showToast(String message) {
        if (mActivity != null) {
            mActivity.showToast(message);
        }
    }

    public String getStringFromId(int string_id) {
        return getString(string_id);
    }

    @Override
    public void displayError(Throwable throwable) {
    }

    @Override
    public boolean isNetWorkConnected() {
        return mActivity != null && mActivity.isNetWorkConnected();
    }

    @Override
    public void displayErrorMessage(int errorCode, String errorMessage) {
    }

    @Override
    public void showLoader(boolean isShowLoader) {
        showLoading(isShowLoader);
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

    @Override
    public void showErrorLayout(String errorTitle, String errorMessage) {
    }
}

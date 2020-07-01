package app.groceryapp.ui.base;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.ViewModel;
import app.groceryapp.BuildConfig;
import app.groceryapp.data.DataManager;
import app.groceryapp.data.model.api.response.ApiError;
import app.groceryapp.utils.constants.ApiConstants;
import app.groceryapp.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<N> extends ViewModel {

    private final DataManager mDataManager;

    //private final ObservableBoolean mIsLoading = new ObservableBoolean();

    private final SchedulerProvider mSchedulerProvider;

    private CompositeDisposable mCompositeDisposable;

    private WeakReference<N> mNavigator;

    public BaseViewModel(DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    protected CompositeDisposable getDisposable() {
        return mCompositeDisposable;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    /*
    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    private void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }*/

    protected N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    protected void loading(boolean isLoading) {
        //setIsLoading(isLoading);
        //((BaseNavigator) getNavigator()).showLoader(isLoading);
    }

    protected void showLoader(boolean isLoading) {
        ((BaseNavigator) getNavigator()).showLoader(isLoading);
    }

    protected boolean isError(ApiError response) {
        boolean isError = false;
        if (response != null && response.getErrorMessage() != null && !response.getErrorMessage().equals("")) {
            isError = true;
        }
        return isError;
    }

    protected boolean isNetWorkConnected() {
        if(((BaseNavigator) getNavigator()).isNetWorkConnected()) {
            return true;
        }
        ((BaseNavigator) getNavigator()).showErrorLayout("No internet", "Please check your internet connection and try again");
        return false;
    }

    protected  Map<String, String> getBaseParams() {
        Map<String, String> params =  new HashMap<>();
        params.put(ApiConstants.API_KEY, BuildConfig.API_KEY);
        return params;
    }
}

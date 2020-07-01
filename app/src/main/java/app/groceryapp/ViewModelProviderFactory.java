package app.groceryapp;import org.jetbrains.annotations.NotNull;import javax.inject.Inject;import javax.inject.Singleton;import androidx.lifecycle.ViewModel;import androidx.lifecycle.ViewModelProvider;import app.groceryapp.data.DataManager;import app.groceryapp.ui.activity.main.MainActivityViewModel;import app.groceryapp.ui.activity.splash.SplashActivityViewModel;import app.groceryapp.ui.fragment.home.HomeFragmentViewModel;import app.groceryapp.utils.rx.SchedulerProvider;/** * Reference : https://github.com/MindorksOpenSource/android-mvvm-architecture */@Singletonpublic class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {  private final String TAG = ViewModelProviderFactory.class.getSimpleName();  private final DataManager dataManager;  private final SchedulerProvider schedulerProvider;  @Inject  public ViewModelProviderFactory(DataManager dataManager,                                  SchedulerProvider schedulerProvider) {    this.dataManager = dataManager;    this.schedulerProvider = schedulerProvider;  }  /**   * Add All ViewModels here   */  @NotNull  @SuppressWarnings("unchecked")  @Override  public <T extends ViewModel> T create(Class<T> modelClass) {    if (modelClass.isAssignableFrom(SplashActivityViewModel.class)) {      return (T) new SplashActivityViewModel(dataManager, schedulerProvider);    }    else if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {      return (T) new MainActivityViewModel(dataManager, schedulerProvider);    }    else if (modelClass.isAssignableFrom(HomeFragmentViewModel.class)) {      return (T) new HomeFragmentViewModel(dataManager, schedulerProvider);    }    throw new IllegalArgumentException(        TAG + " - Unknown ViewModel class : " + modelClass.getName());  }}
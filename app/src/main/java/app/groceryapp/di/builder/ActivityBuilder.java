package app.groceryapp.di.builder;

import app.groceryapp.ui.activity.main.MainActivity;
import app.groceryapp.ui.activity.product.ProductListActivity;
import app.groceryapp.ui.activity.splash.SplashActivity;
import app.groceryapp.ui.fragment.home.HomeFragmentProvider;
import app.groceryapp.ui.fragment.product.ProductListFragmentProvider;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = {
        HomeFragmentProvider.class
    })
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {
        ProductListFragmentProvider.class
    })
    abstract ProductListActivity bindProductListActivity();

    /*
    @ContributesAndroidInjector(modules = HomeFragmentProvider.class)
    abstract HomeActivity bindHomeActivity();
    */

    /*
    @ContributesAndroidInjector(modules = {
            TalukListFragmentProvider.class,
            TalukDetailFragmentProvider.class,
            TalukAboutFragmentProvider.class,
            TalukGalleryFragmentProvider.class,
            TalukVideosFragmentProvider.class,
            TalukEventFragmentProvider.class
    })
    abstract TalukActivity bindTalukActivity();
    */
}

package app.groceryapp.di.components;

import android.app.Application;
import javax.inject.Singleton;

import app.groceryapp.GroceryApp;
import app.groceryapp.data.remote.retrofit.NetworkModule;
import app.groceryapp.di.builder.ActivityBuilder;
import app.groceryapp.di.module.AppModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class,
        NetworkModule.class})
public interface AppComponent {

    void inject(GroceryApp application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}

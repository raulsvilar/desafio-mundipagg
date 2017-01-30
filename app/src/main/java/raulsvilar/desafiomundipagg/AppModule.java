package raulsvilar.desafiomundipagg;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import raulsvilar.desafiomundipagg.model.Merchant;
import raulsvilar.desafiomundipagg.model.User;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    public SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    public User provideUser() {
        return new User();
    }

    @Provides
    public Retrofit provideApiaryRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://4b680d5ffaad4009ac8da8f76d44bd05.cloudapp.net")
                .build();
    }

    @Provides
    public List<Merchant> provideMerchantsDataset() {
        return new ArrayList<>();
    }
}

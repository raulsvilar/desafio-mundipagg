package raulsvilar.desafiomundipagg;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import raulsvilar.desafiomundipagg.data.models.Buyer;
import raulsvilar.desafiomundipagg.data.models.Transaction;
import raulsvilar.desafiomundipagg.viewmodels.UserViewModel;
import raulsvilar.desafiomundipagg.views.adapters.MerchantAdapter;
import raulsvilar.desafiomundipagg.data.models.Merchant;
import raulsvilar.desafiomundipagg.data.models.User;
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
    @Named("transactionRetrofit")
    public Retrofit provideTransactionRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://sandbox.mundipaggone.com/")
                .build();
    }

    @Provides
    public Transaction provideTransaction() {
        return new Transaction();
    }

    @Provides
    public List<Merchant> provideMerchantsDataset() {
        return new ArrayList<>();
    }

    @Provides
    public Buyer provideBuyer() {
        return new Buyer();
    }

    @Provides
    public MerchantAdapter provideMerchantAdapter() {
        return new MerchantAdapter();
    }

    @Provides
    public UserViewModel provideUserViewModel() {
        return new UserViewModel();
    }
}

package raulsvilar.desafiomundipagg;

import dagger.Module;
import dagger.Provides;
import raulsvilar.desafiomundipagg.model.User;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class AppModule {

    @Provides
    public User provideUser() {
        return new User();
    }

    @Provides
    public Retrofit provideUserRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://4b680d5ffaad4009ac8da8f76d44bd05.cloudapp.net")
                .build();
    }
}

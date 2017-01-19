package raulsvilar.desafiomundipagg.service;

import raulsvilar.desafiomundipagg.model.User;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by raulsvilar on 19/01/17.
 */

public class DataService {
    public interface UserService {
        @POST("users/accesstokens")
        Call<User> loginWithCredentials(String email, String password);

        @POST("users/accesstokens")
        Call<User> loginWithRefreshToken(String email, String password);
    }

    public interface MerchantService {



    }
}

package raulsvilar.desafiomundipagg.service;

import raulsvilar.desafiomundipagg.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("users/accesstokens")
    Call<User> loginWithCredentials(@Body User user);

    @POST("users/accesstokens")
    Call<User> loginWithRefreshToken(String email, String password);

    @POST("sandbox/account")
    Call<User> createUser(@Body User user);
}

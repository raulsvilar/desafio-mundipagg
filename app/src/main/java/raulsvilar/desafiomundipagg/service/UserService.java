package raulsvilar.desafiomundipagg.service;

import java.util.Map;

import okhttp3.ResponseBody;
import raulsvilar.desafiomundipagg.data.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    @POST("users/accesstokens")
    Call<User> loginWithCredentials(@Body User user);

    @POST("users/refreshtokens")
    Call<User> loginWithRefreshToken(@Body Map<String, String> refreshToken);

    @POST("sandbox/account")
    Call<User> createUser(@Body User user);

    @DELETE("/users/refreshtokens")
    Call<ResponseBody> logoutUser(@Header("Authorization") String auth);
}

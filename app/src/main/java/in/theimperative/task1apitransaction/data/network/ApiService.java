package in.theimperative.task1apitransaction.data.network;

import java.util.List;

import in.theimperative.task1apitransaction.data.model.LoginRequest;
import in.theimperative.task1apitransaction.data.model.LoginResponse;
import in.theimperative.task1apitransaction.data.model.Transaction;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("transactions")
    Call<List<Transaction>> getTransactions(@Header("Authorization") String token);

}
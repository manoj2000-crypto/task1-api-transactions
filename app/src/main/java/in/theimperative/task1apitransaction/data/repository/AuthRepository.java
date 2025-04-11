package in.theimperative.task1apitransaction.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import in.theimperative.task1apitransaction.data.model.LoginRequest;
import in.theimperative.task1apitransaction.data.model.LoginResponse;
import in.theimperative.task1apitransaction.data.network.ApiClient;
import in.theimperative.task1apitransaction.data.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final ApiService apiService;

    public AuthRepository() {
        apiService = ApiClient.getApiService();
    }

    public LiveData<LoginResponse> login(String username, String password) {
        MutableLiveData<LoginResponse> result = new MutableLiveData<>();
        LoginRequest request = new LoginRequest(username, password);

        apiService.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                result.setValue(response.isSuccessful() ? response.body() : null);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                result.setValue(null); // or handle error better
            }
        });

        return result;
    }
}
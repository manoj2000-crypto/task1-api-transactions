package in.theimperative.task1apitransaction.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import in.theimperative.task1apitransaction.data.model.Transaction;
import in.theimperative.task1apitransaction.data.network.ApiClient;
import in.theimperative.task1apitransaction.data.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionRepository {

    private final ApiService apiService;

    public TransactionRepository() {
        apiService = ApiClient.getApiService();
    }

    public LiveData<List<Transaction>> getTransactions(String token, MutableLiveData<Boolean> unauthorized) {
        MutableLiveData<List<Transaction>> result = new MutableLiveData<>();

        apiService.getTransactions(token).enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if (response.code() == 401) {
                    unauthorized.postValue(true);
                    return;
                }

                if (response.isSuccessful()) {
                    result.setValue(response.body());
                } else {
                    result.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                Log.e("TransactionRepo", "API failure: " + t.getMessage());
                result.setValue(null);
            }
        });

        return result;
    }
}
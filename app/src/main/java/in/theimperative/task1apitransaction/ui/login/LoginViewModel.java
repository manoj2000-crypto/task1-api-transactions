package in.theimperative.task1apitransaction.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import in.theimperative.task1apitransaction.data.model.LoginResponse;
import in.theimperative.task1apitransaction.data.repository.AuthRepository;

public class LoginViewModel extends ViewModel {

    private final AuthRepository repository = new AuthRepository();
    public MutableLiveData<LoginResponse> loginResult = new MutableLiveData<>();

    public void login(String username, String password) {
        repository.login(username, password).observeForever(loginResult::setValue);
    }
}
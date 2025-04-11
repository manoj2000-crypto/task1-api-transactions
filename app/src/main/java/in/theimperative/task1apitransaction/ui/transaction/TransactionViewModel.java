package in.theimperative.task1apitransaction.ui.transaction;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import in.theimperative.task1apitransaction.data.model.Transaction;
import in.theimperative.task1apitransaction.data.repository.TransactionRepository;

public class TransactionViewModel extends ViewModel {

    private final TransactionRepository repository = new TransactionRepository();
    public MutableLiveData<List<Transaction>> transactions = new MutableLiveData<>();
    public MutableLiveData<Boolean> unauthorized = new MutableLiveData<>();

    public void loadTransactions(String token) {
        repository.getTransactions(token, unauthorized).observeForever(transactions::setValue);
    }
}
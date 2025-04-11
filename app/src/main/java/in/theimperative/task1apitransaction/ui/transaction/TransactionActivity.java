package in.theimperative.task1apitransaction.ui.transaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import in.theimperative.task1apitransaction.R;
import in.theimperative.task1apitransaction.ui.login.LoginActivity;
import in.theimperative.task1apitransaction.utils.TokenManager;

public class TransactionActivity extends AppCompatActivity {

    private TransactionViewModel viewModel;
    private TransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_activity);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.rvTransactions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransactionAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(TransactionViewModel.class);

        viewModel.transactions.observe(this, adapter::submitList);

        viewModel.unauthorized.observe(this, isUnauthorized -> {
            if (Boolean.TRUE.equals(isUnauthorized)) {
                new AlertDialog.Builder(this).setTitle("Session Expired").setMessage("Your session has expired. Please log in again.").setCancelable(false).setPositiveButton("OK", (dialog, which) -> {
                    TokenManager.clearToken(this);
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }).show();
            }
        });

        String token = TokenManager.getToken(this);
        if (token != null) {
            viewModel.loadTransactions(token);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.transaction_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            TokenManager.clearToken(this);
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
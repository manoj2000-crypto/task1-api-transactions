package in.theimperative.task1apitransaction.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import in.theimperative.task1apitransaction.R;
import in.theimperative.task1apitransaction.utils.TokenManager;
import in.theimperative.task1apitransaction.ui.biometric.BiometricActivity;

public class LoginActivity extends ComponentActivity {

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText etUsername = findViewById(R.id.etUsername);
        TextInputEditText etPassword = findViewById(R.id.etPassword);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            viewModel.login(username, password);
        });

        viewModel.loginResult.observe(this, result -> {
            if (result != null && result.getToken() != null) {
                TokenManager.saveToken(this, result.getToken());

                // Proceed to biometric screen
                startActivity(new Intent(this, BiometricActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Login failed. Invalid credentials.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
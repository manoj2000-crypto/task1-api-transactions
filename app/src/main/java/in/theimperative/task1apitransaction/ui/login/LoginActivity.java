package in.theimperative.task1apitransaction.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;

import in.theimperative.task1apitransaction.R;
import in.theimperative.task1apitransaction.utils.TokenManager;
import in.theimperative.task1apitransaction.ui.biometric.BiometricActivity;

public class LoginActivity extends ComponentActivity {

    private LoginViewModel viewModel;
    private static final String PREFS_NAME = "LoginPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        TextInputEditText etUsername = findViewById(R.id.etUsername);
        TextInputEditText etPassword = findViewById(R.id.etPassword);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);
        CircularProgressIndicator progress = findViewById(R.id.progressIndicator);
        TextView tvStatus = findViewById(R.id.tvStatus);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        etUsername.setText(prefs.getString("username", ""));
        etPassword.setText(prefs.getString("password", ""));

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            progress.setVisibility(CircularProgressIndicator.VISIBLE);
            btnLogin.setEnabled(false);
            tvStatus.setText("");

            viewModel.login(username, password);
        });

        viewModel.loginResult.observe(this, result -> {
            progress.setVisibility(CircularProgressIndicator.GONE);
            btnLogin.setEnabled(true);

            if (result != null && result.getToken() != null) {
                TokenManager.saveToken(this, result.getToken());

                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
                editor.putString("username", etUsername.getText().toString().trim());
                editor.putString("password", etPassword.getText().toString().trim());
                editor.apply();

                // Proceed to biometric screen
                tvStatus.setText(R.string.login_successful);
                startActivity(new Intent(this, BiometricActivity.class));
                finish();
            } else {
                tvStatus.setText(R.string.failed_to_login);
                Toast.makeText(this, "Login failed. Invalid credentials.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}